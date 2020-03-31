package de.stickmc.discord.commands;

import de.stickmc.ms.utils.MyData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class CMD_Online extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(MyData.discordPrefix + "online")){
            EmbedBuilder online = new EmbedBuilder();
            online.setTitle("❗ Information");
            online.addField("Online","➜ " + Bukkit.getOnlinePlayers().size(), false);
            online.setColor(0x0B1CF4);
            e.getChannel().sendTyping().queue();
            e.getChannel().sendMessage(online.build()).queue();;
            online.clear();
        }
    }

}
