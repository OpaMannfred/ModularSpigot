package de.stickmc.discord.commands;

import de.stickmc.ms.utils.MyData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class CMD_Clear extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(MyData.discordPrefix + "clear")) {
            if(args.length < 2){
                if (e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Falsche Benutzung");
                    builder.addField("Benutze:", "!clear [1 - 100]", false);
                    builder.setColor(0xff3923);
                    e.getChannel().sendMessage(builder.build());
                    builder.clear();
                }else{
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Keine Rechte");
                    builder.addField("Rechte?", "Dazu hast du keine Rechte", false);
                    builder.setColor(0xff3923);
                    e.getChannel().sendMessage(builder.build());
                    builder.clear();
                }
            }else{
                if (e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                    List<Message> messages = e.getChannel().getHistory().retrievePast(Integer.valueOf(args[1])).complete();
                    e.getChannel().deleteMessages(messages).queue();
                }else{
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Keine Rechte");
                    builder.addField("Rechte?", "Dazu hast du keine Rechte", false);
                    builder.setColor(0xff3923);
                    e.getChannel().sendMessage(builder.build());
                    builder.clear();
                }
            }
        }
    }

}
