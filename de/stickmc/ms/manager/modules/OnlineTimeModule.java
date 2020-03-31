package de.stickmc.ms.manager.modules;

import de.stickmc.api.OnlineAPI;
import de.stickmc.ms.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class OnlineTimeModule {

    private static File file = new File("plugins//ModularSpigot//modules//OnlineTimeModule.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void loadModule(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cfg.options().copyDefaults(true);
        cfg.addDefault("config.prefix", "&cOnlineTime &8» &7");
        cfg.addDefault("config.messages.online", "Du spielst schon &e%hours% Stunde(n) &7und &e%minutes% Minute(n) &7auf dem Server");

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {

                for(Player all : Bukkit.getOnlinePlayers()){
                    int hours = OnlineAPI.getHours(all.getUniqueId().toString());
                    int minutes = OnlineAPI.getMinutes(all.getUniqueId().toString());

                    minutes++;

                    OnlineAPI.setMinutes(all.getUniqueId().toString(), minutes);

                    if(minutes == 60){
                        OnlineAPI.setMinutes(all.getUniqueId().toString(), 0);
                        hours++;
                        OnlineAPI.setHours(all.getUniqueId().toString(), hours);
                    }

                }

            }
        }, 20*60, 20*60);
    }

    public static File getFile() {
        return file;
    }
}
