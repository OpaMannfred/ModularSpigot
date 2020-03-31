package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GetIpModule {

    private static File file = new File("plugins//ModularSpigot//modules//GetIpModule.yml");
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
        cfg.addDefault("config.prefix", "&cGetIp &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.getip", "Die IP von dem Spieler ist &c%ip%");

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
