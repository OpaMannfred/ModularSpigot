package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.MuteModule;
import org.bukkit.ChatColor;

public class MuteData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.messages.no_permission"));
    public static String success = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.messages.success"));
    public static String notSelf = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.messages.not_self"));
    public static String notWrite = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.messages.mute_write"));

    public static String reason1 = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.reasons.1"));
    public static String reason2 = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.reasons.2"));
    public static String reason3 = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.reasons.3"));
    public static String reason4 = ChatColor.translateAlternateColorCodes('&', MuteModule.getCfg().getString("config.reasons.4"));

}
