package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.OnlineTimeModule;
import org.bukkit.ChatColor;

public class TimeData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', OnlineTimeModule.getCfg().getString("config.prefix"));
    public static String msg = ChatColor.translateAlternateColorCodes('&', OnlineTimeModule.getCfg().getString("config.messages.online"));

}
