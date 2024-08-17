package create.convoy.simplefunctions;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import create.convoy.simplefunctions.commands.CallCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.CommandFunctionArgumentType;
import net.minecraft.util.Identifier;

public class Playercommands implements ModInitializer {

    public static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = (context, builder) -> {
		CommandFunctionManager commandFunctionManager = context.getSource().getServer().getCommandFunctionManager();
		CommandSource.suggestIdentifiers(commandFunctionManager.getFunctionTags(), builder, "#");
		return CommandSource.suggestIdentifiers(commandFunctionManager.getAllFunctions(), builder);
	};

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAcces, enviroment) -> {
            registerCommands(dispatcher, false);
        });
    }

    private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("call")
                .then(CommandManager.argument("function", CommandFunctionArgumentType.commandFunction()).suggests(SUGGESTION_PROVIDER)
                .executes(context -> {
                    Identifier function = CommandFunctionArgumentType.getIdentifiedFunctions(context, "function").getFirst();
                    ServerCommandSource source = context.getSource();

                    String command = "/function " + function.toString();
                    source.getServer().getCommandManager().executeWithPrefix(source, command);
                    return Command.SINGLE_SUCCESS;
                })
                .then(CommandManager.argument("args", StringArgumentType.greedyString())
                .executes(new CallCommand()))));
    }
}