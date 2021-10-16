package fr.farmvivi.coach.command;

import java.util.ArrayList;
import java.util.List;

import fr.farmvivi.coach.Bot;
import fr.farmvivi.coach.command.other.HelpCommand;
import fr.farmvivi.coach.command.other.ShutdownCommand;
import fr.farmvivi.coach.command.other.VersionCommand;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandsManager extends ListenerAdapter {
    private final List<Command> commands = new ArrayList<>();

    public CommandsManager() {
        commands.add(new HelpCommand());
        commands.add(new VersionCommand());
        commands.add(new ShutdownCommand());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.TEXT) || event.isFromType(ChannelType.PRIVATE)) {
            final String message = event.getMessage().getContentDisplay();
            final String CMD_PREFIX = Bot.getInstance().getConfiguration().cmdPrefix;

            if (!message.startsWith(CMD_PREFIX))
                return;

            final String cmd = message.substring(CMD_PREFIX.length()).split(" ")[0];

            for (final Command command : commands) {
                final List<String> cmds = new ArrayList<>();
                cmds.add(command.name);
                if (command.aliases.length != 0)
                    for (final String tempCmd : command.aliases)
                        cmds.add(tempCmd);
                if (cmds.contains(cmd.toLowerCase())) {
                    if (command.args.length() != 0) {
                        final int commandLength = CMD_PREFIX.length() + cmd.length() + 1;
                        if (message.length() > commandLength) {
                            command.execute(event, message.substring(commandLength));
                            return;
                        }
                    }
                    command.execute(event, "");
                    return;
                }
            }
        }
    }

    public List<Command> getCommands() {
        return commands;
    }
}
