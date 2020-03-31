package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.NotifyModule;
import org.bukkit.ChatColor;

public class NotifyData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', NotifyModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', NotifyModule.getCfg().getString("config.messages.no_permission"));
    public static String enable = ChatColor.translateAlternateColorCodes('&', NotifyModule.getCfg().getString("config.messages.enable"));
    public static String disable = ChatColor.translateAlternateColorCodes('&', NotifyModule.getCfg().getString("config.messages.disable"));

}
