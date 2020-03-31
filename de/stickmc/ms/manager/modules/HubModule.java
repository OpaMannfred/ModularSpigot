package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HubModule {

    private static File file = new File("plugins//ModularSpigot//modules//HubModule.yml");
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
        cfg.addDefault("config.prefix", "&cHub &8» &7");
        cfg.addDefault("config.messages.teleport", "Du wirst zum Spawn teleportiert");

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
