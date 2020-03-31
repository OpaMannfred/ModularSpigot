package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.BanModule;
import org.bukkit.ChatColor;

public class BanData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.no_permission"));
    public static String already_banned = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.already_banned"));
    public static String success = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.success"));
    public static String successUnban = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.success_unban"));
    public static String not_self_ban = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.not_self_ban"));
    public static String cannot_ban = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.cannot_ban"));
    public static String disconnect = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.disconnect_screen"));
    public static String notBanned = ChatColor.translateAlternateColorCodes('&', BanModule.getBanCFG().getString("config.messages.not_banned"));

    public static String reasonDay = BanModule.getBanCFG().getString("config.reasons.days");
    public static String reasonHour = BanModule.getBanCFG().getString("config.reasons.hours");
    public static String reasonMinute = BanModule.getBanCFG().getString("config.reasons.minutes");

}