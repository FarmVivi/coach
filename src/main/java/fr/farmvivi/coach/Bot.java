package fr.farmvivi.coach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.farmvivi.coach.command.CommandsManager;
import fr.farmvivi.coach.jda.JDAManager;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
    private static Bot instance;

    public static final String version = "1.0.0.0";
    public static final String name = "AnimeCity";
    public static final boolean production = false;

    public static final Logger logger = LoggerFactory.getLogger(name);

    private final Configuration configuration;
    private final CommandsManager commandsManager;

    public Bot(String[] args) {
        logger.info("Démarrage de " + name + " (V" + version + ") (Prod: " + production + ") en cours...");

        logger.info("System.getProperty('os.name') == '" + System.getProperty("os.name") + "'");
        logger.info("System.getProperty('os.version') == '" + System.getProperty("os.version") + "'");
        logger.info("System.getProperty('os.arch') == '" + System.getProperty("os.arch") + "'");
        logger.info("System.getProperty('java.version') == '" + System.getProperty("java.version") + "'");
        logger.info("System.getProperty('java.vendor') == '" + System.getProperty("java.vendor") + "'");
        logger.info("System.getProperty('sun.arch.data.model') == '" + System.getProperty("sun.arch.data.model") + "'");

        instance = this;

        configuration = new Configuration();
        JDAManager.getShardManager();
        commandsManager = new CommandsManager();
        JDAManager.getShardManager().addEventListener(commandsManager);
        setDefaultActivity();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutdown asked!");
            JDAManager.getShardManager().removeEventListener(commandsManager);
            JDAManager.getShardManager().shutdown();
            logger.info("Bye!");
        }));
    }

    public static Bot getInstance() {
        return instance;
    }

    public static void setInstance(Bot instance) {
        Bot.instance = instance;
    }

    public void setDefaultActivity() {
        if (production)
            JDAManager.getShardManager()
                    .setActivity(Activity.playing("V" + version + " | Prefix: " + configuration.cmdPrefix));
        else
            JDAManager.getShardManager()
                    .setActivity(Activity.playing("Dev - V" + version + " | Prefix: " + configuration.cmdPrefix));
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }
}
