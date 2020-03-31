package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ChatModule {

    private static File file = new File("plugins//ModularSpigot//modules//ChatModule.yml");
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
        cfg.addDefault("config.prefix", "&cChat &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.blockmsg", "Du darfst das &cnicht &7schreiben.");
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
