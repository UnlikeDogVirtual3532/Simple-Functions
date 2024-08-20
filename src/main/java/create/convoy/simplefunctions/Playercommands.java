package create.convoy.simplefunctions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

import com.mojang.brigadier.suggestion.SuggestionProvider;

import create.convoy.simplefunctions.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.command.CommandSource;

public class Playercommands implements ModInitializer {
    public static final String MOD_NAME = "Simple Functions";
    public static final String MOD_ID = "simplefunctions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static String configFileLocation = "config/simplefunctions/";
    public static String configFileName = "Simplefunctions.yaml";
    public static String defaultConfigFilePath = "assets/simplefunctions/default_config.yaml";
    public static Map<String, Object> configMap;

    public static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = (context, builder) -> {
		CommandFunctionManager commandFunctionManager = context.getSource().getServer().getCommandFunctionManager();
		CommandSource.suggestIdentifiers(commandFunctionManager.getFunctionTags(), builder, "#");
		return CommandSource.suggestIdentifiers(commandFunctionManager.getAllFunctions(), builder);
	};

    @Override
    public void onInitialize() {
        ConfigManager configManager = new ConfigManager(configFileLocation, configFileName, defaultConfigFilePath);

        try {
            configMap = configManager.getConfig();
        } catch (Exception e) {
            configMap = configManager.getDefaultConfig();
        }

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAcces, enviroment) -> {
            CommandRegistry.register(dispatcher);
        });
    }
}