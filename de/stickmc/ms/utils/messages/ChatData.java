package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.ChatModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ChatData {

    public static ArrayList<Player> chatMuted = new ArrayList<Player>();

    public static String prefix = ChatColor.translateAlternateColorCodes('&', ChatModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', ChatModule.getCfg().getString("config.messages.no_permission"));
    public static String msg = ChatColor.translateAlternateColorCodes('&', ChatModule.getCfg().getString("config.messages.blockmsg"));

}
