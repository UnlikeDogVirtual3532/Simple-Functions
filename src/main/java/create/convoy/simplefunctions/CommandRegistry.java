package create.convoy.simplefunctions;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import create.convoy.simplefunctions.commands.CallCommand;
import create.convoy.simplefunctions.commands.ShortCallCommand;
import net.minecraft.command.argument.CommandFunctionArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("call")
                .then(CommandManager.argument("function", CommandFunctionArgumentType.commandFunction()).suggests(Playercommands.SUGGESTION_PROVIDER)
                .executes(new ShortCallCommand())
                .then(CommandManager.argument("args", StringArgumentType.greedyString())
                .executes(new CallCommand()))));
    }
}
