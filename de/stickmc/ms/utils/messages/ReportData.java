package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.ReportModule;
import org.bukkit.ChatColor;

public class ReportData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.messages.no_permission"));
    public static String report = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.messages.success"));
    public static String notonline = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.messages.not_online"));
    public static String notself = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.messages.not_self"));
    public static String accept = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.messages.accept"));
    public static String deny = ChatColor.translateAlternateColorCodes('&', ReportModule.getCfg().getString("config.messages.deny"));

    public static String reason1 = ReportModule.getCfg().getString("config.reason.1");
    public static String reason2 = ReportModule.getCfg().getString("config.reason.2");
    public static String reason3 = ReportModule.getCfg().getString("config.reason.3");
    public static String reason4 = ReportModule.getCfg().getString("config.reason.4");
    public static String reason5 = ReportModule.getCfg().getString("config.reason.5");
    public static String reason6 = ReportModule.getCfg().getString("config.reason.6");

}
