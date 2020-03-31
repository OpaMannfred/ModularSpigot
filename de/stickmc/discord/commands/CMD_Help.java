package de.stickmc.discord.commands;

import de.stickmc.ms.utils.MyData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CMD_Help extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(MyData.discordPrefix + "help")) {

            EmbedBuilder help = new EmbedBuilder();

            help.setTitle("● Hilfe");
            help.addField("Clear", "!clear <Zahl>", false);
            help.addField("Information", "!info", false);
            help.addField("Onlinespieler", "!online", false);
            help.setColor(0x0B1CF4);

            e.getChannel().sendTyping().queue();
            e.getChannel().sendMessage(help.build()).queue();;
            help.clear();

        }
    }

}
