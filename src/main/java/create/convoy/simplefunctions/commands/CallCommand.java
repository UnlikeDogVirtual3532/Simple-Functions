package create.convoy.simplefunctions.commands;

import java.util.Collection;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.CommandFunctionArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;

public class CallCommand implements Command<ServerCommandSource> {

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Identifier function = CommandFunctionArgumentType.getFunctionOrTag(context, "function").getFirst();

        String args = StringArgumentType.getString(context, "args");
        ServerCommandSource source = context.getSource();

        String procArgs = buildArgs(args);

        exec(source, function, procArgs);
        return 0;
    }

    private String buildArgs(String args) {
        String[] argsList = args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        StringBuilder jsonArgs = new StringBuilder("{");

        for (int i = 0; i < argsList.length; i += 2) {
            argsList[i] = argsList[i].replaceAll("^\"|\"$", "");
            argsList[i + 1] = argsList[i + 1].replaceAll("^\"|\"$", "");
            if (i > 0) jsonArgs.append(", ");
            jsonArgs.append("\"").append(argsList[i]).append("\":\"").append(argsList[i + 1]).append("\"");
        }

        jsonArgs.append("}");
        return jsonArgs.toString();
    }

    private void exec(ServerCommandSource source, Identifier function, String args) {
        String command = "/function " + function.toString() + " " + args;
        source.getServer().getCommandManager().executeWithPrefix(source, command);
    }
}