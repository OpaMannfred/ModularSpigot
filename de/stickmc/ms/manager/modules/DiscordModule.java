package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DiscordModule {

    private static File file = new File("plugins//ModularSpigot//modules//DiscordModule.yml");
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
        cfg.addDefault("config.token", "DEINTOKEN");
        cfg.addDefault("config.channelid", "CHANNELID");
        cfg.addDefault("config.prefix", "!");
        cfg.addDefault("config.game", "StickMC");
        List list = cfg.getStringList("config.rules");
        if(list.isEmpty()){
            list.add("Kein Spam");
            cfg.set("config.rules", list);
        }
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static File getFile() {
        return file;
    }

}
