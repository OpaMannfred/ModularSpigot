package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.CMDBlockModule;
import org.bukkit.ChatColor;

public class BlockData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', CMDBlockModule.getCfg().getString("config.prefix"));
    public static String block = ChatColor.translateAlternateColorCodes('&', CMDBlockModule.getCfg().getString("config.messages.block"));
    public static boolean allowed = CMDBlockModule.getCfg().getBoolean("config.settings.tab_complete");

}
