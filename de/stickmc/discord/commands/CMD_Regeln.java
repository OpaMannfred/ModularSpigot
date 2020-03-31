package de.stickmc.discord.commands;

import de.stickmc.ms.manager.modules.DiscordModule;
import de.stickmc.ms.utils.MyData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CMD_Regeln extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(MyData.discordPrefix + "regeln")){
            EmbedBuilder online = new EmbedBuilder();
            online.setTitle("SERVER REGELN");
            for(String lines : DiscordModule.getCfg().getStringList("config.rules")){
                online.addField("**»**", lines, false);
            }
            e.getChannel().sendTyping().queue();
            e.getChannel().sendMessage(online.build()).queue();;
            online.clear();
        }
    }

}
