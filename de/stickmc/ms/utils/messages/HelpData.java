package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.HelpModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', HelpModule.getCfg().getString("config.prefix"));
    public static String noArg = ChatColor.translateAlternateColorCodes('&', HelpModule.getCfg().getString("config.messages.usage"));
    public static String unkownMSG = ChatColor.translateAlternateColorCodes('&', HelpModule.getCfg().getString("config.settings.unkown_command.message"));
    public static boolean enabled = HelpModule.getCfg().getBoolean("config.settings.unkown_command.enabled");

    public static void sendMSG(Player p){
        for(String s : HelpModule.getCfg().getStringList("config.messages.help")){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

}
