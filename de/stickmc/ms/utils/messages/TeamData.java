package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.TeamChatModule;
import org.bukkit.ChatColor;

public class TeamData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', TeamChatModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', TeamChatModule.getCfg().getString("config.messages.no_permission"));
    public static String format = ChatColor.translateAlternateColorCodes('&', TeamChatModule.getCfg().getString("config.messages.format"));

}
