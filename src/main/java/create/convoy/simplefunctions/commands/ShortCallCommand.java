package create.convoy.simplefunctions.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.CommandFunctionArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public class ShortCallCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Identifier function = CommandFunctionArgumentType.getIdentifiedFunctions(context, "function").getFirst();
        ServerCommandSource source = context.getSource();

        String command = "/function " + function.toString();
        source.getServer().getCommandManager().executeWithPrefix(source, command);
        return 0;
    }
}