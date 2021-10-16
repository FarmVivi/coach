package fr.farmvivi.coach.command.other;

import fr.farmvivi.coach.Bot;
import fr.farmvivi.coach.command.Command;
import fr.farmvivi.coach.command.CommandCategory;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class VersionCommand extends Command {
    public VersionCommand() {
        this.name = "version";
        this.category = CommandCategory.OTHER;
        this.description = "Affiche la version du bot";
        this.guildOnly = false;
    }

    @Override
    protected boolean execute(MessageReceivedEvent event, String content) {
        if (!super.execute(event, content))
            return false;

        event.getChannel().sendMessage(Bot.name + " (**V" + Bot.version + "**) (Prod: " + Bot.production
                + ") :\n-> JDA (**V" + JDAInfo.VERSION + "**)").queue();

        return true;
    }
}
