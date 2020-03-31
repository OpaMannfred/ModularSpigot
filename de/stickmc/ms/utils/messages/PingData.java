package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.PingModule;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PingData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', PingModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', PingModule.getCfg().getString("config.messages.no_permission"));
    public static String ping = ChatColor.translateAlternateColorCodes('&', PingModule.getCfg().getString("config.messages.ping"));

    public static int getPing(Player player) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
        Field fieldPing = nmsPlayer.getClass().getDeclaredField("ping");
        fieldPing.setAccessible(true);
        return fieldPing.getInt(nmsPlayer);
    }

}
