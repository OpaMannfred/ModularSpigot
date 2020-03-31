package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.GetIpModule;
import org.bukkit.ChatColor;

public class GetIpData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', GetIpModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', GetIpModule.getCfg().getString("config.messages.no_permission"));
    public static String getip = ChatColor.translateAlternateColorCodes('&', GetIpModule.getCfg().getString("config.messages.getip"));

}
