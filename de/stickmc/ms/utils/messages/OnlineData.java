package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.OnlineModule;
import org.bukkit.ChatColor;

public class OnlineData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', OnlineModule.getCfg().getString("config.prefix"));
    public static String msg = ChatColor.translateAlternateColorCodes('&', OnlineModule.getCfg().getString("config.messages.online"));

}
