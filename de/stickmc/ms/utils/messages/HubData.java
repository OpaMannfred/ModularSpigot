package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.HubModule;
import org.bukkit.ChatColor;

public class HubData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', HubModule.getCfg().getString("config.prefix"));
    public static String teleport = ChatColor.translateAlternateColorCodes('&', HubModule.getCfg().getString("config.messages.teleport"));

}
