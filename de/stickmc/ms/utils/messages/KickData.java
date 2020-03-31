package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.KickModule;
import org.bukkit.ChatColor;

public class KickData {

    public static boolean broadcastEnabled = KickModule.getCfg().getBoolean("config.broadcast");

    public static String prefix = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.messages.no_permission"));
    public static String userNotOnline = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.messages.user_not_online"));
    public static String success = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.messages.success"));
    public static String notSelf = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.messages.no_permission"));

    public static String reasonKick = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.layouts.reason.kick"));
    public static String reasonBroadcast = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.layouts.reason.broadcast"));

    public static String noreasonKick = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.layouts.no_reason.kick"));
    public static String noreasonBroadcast = ChatColor.translateAlternateColorCodes('&', KickModule.getCfg().getString("config.layouts.no_reason.broadcast"));

}
