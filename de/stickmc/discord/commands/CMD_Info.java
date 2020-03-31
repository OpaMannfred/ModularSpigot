package de.stickmc.discord.commands;

import de.stickmc.ms.manager.MotdManager;
import de.stickmc.ms.manager.modules.MaintenanceModule;
import de.stickmc.ms.utils.MyData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class CMD_Info extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(MyData.discordPrefix + "info")){
            EmbedBuilder online = new EmbedBuilder();
            online.addField("**NETWORK STATUS**", "**—————————————————————————————**", false);
            if(MaintenanceModule.isEnabled()){
                online.addField(":newspaper2: » MOTD", MyData.removeColor(MotdManager.getWartung1()) + "\n" + MyData.removeColor(MotdManager.getWartung2()), false);
            }else{
                online.addField(":newspaper2: » MOTD", MyData.removeColor(MotdManager.getNormal1()) + "\n" + MyData.removeColor(MotdManager.getNormal2()), false);
            }
            online.addField(":family: » Usercount", Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers(), false);
            online.addField("**—————————————————————————————**", "Online", false);

            e.getChannel().sendTyping().queue();
            e.getChannel().sendMessage(online.build()).queue();;
            online.clear();
        }
    }

}
