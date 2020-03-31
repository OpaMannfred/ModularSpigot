package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.BroadcastModule;
import org.bukkit.ChatColor;

public class BroadcastData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', BroadcastModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', BroadcastModule.getCfg().getString("config.messages.no_permissions"));

}
