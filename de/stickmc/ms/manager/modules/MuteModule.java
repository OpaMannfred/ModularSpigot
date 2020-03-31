package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MuteModule {

    private static File file = new File("plugins//ModularSpigot//modules//MuteModule.yml");
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
        cfg.addDefault("config.prefix", "&cMute &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.success", "&aDu hast den Spieler &7%player% &aerfolgreich gestummt. Grund&8: &7%grund%");
        cfg.addDefault("config.messages.not_self", "Du kannst dich &cnicht &7selbst muten");
        cfg.addDefault("config.messages.mute_write", "Du wurdest für &e%grund% &7gemuted. Dauer&8: &e%zeit%");
        cfg.addDefault("config.reasons.1", "Beleidigung");
        cfg.addDefault("config.reasons.2", "Spam");
        cfg.addDefault("config.reasons.3", "Werbung");
        cfg.addDefault("config.reasons.4", "Dein eigener Grund");
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
