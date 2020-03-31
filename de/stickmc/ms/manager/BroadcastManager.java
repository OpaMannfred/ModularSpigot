package de.stickmc.ms.manager;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.modules.AutoBroadcastModule;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Random;

public class BroadcastManager {

    public static final int DELAY = Integer.valueOf(AutoBroadcastModule.getCfg().getString("config.settings.time"))*20;

    public static void start(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', pickMessages()));
            }
        }, 0, DELAY);
    }

    public static String pickMessages(){
        List<String> messages = AutoBroadcastModule.getCfg().getStringList("config.messages");
        int random = new Random().nextInt(messages.size());
        return messages.get(random);
    }

}
