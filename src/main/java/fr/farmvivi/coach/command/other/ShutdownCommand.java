package fr.farmvivi.coach.command.other;

import fr.farmvivi.coach.command.Command;
import fr.farmvivi.coach.command.CommandCategory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ShutdownCommand extends Command {
    public ShutdownCommand() {
        this.name = "shutdown";
        this.category = CommandCategory.OTHER;
        this.description = "Éteint le bot";
        this.guildOnly = false;
        this.adminOnly = true;
    }

    @Override
    protected boolean execute(MessageReceivedEvent event, String content) {
        if (!super.execute(event, content))
            return false;

        System.exit(0);

        return true;
    }
}
