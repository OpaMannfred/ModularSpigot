package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ReportModule {

    private static File file = new File("plugins//ModularSpigot//modules//ReportModule.yml");
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
        cfg.addDefault("config.prefix", "&cReport &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.not_online", "Dieser Spieler ist &cnicht &7Online");
        cfg.addDefault("config.messages.not_self", "Du darfst dich &cnicht &7selbst reporten");
        cfg.addDefault("config.messages.success", "&aDu hast &7%player% &aerfolgreich reportet");
        cfg.addDefault("config.messages.accept", "Du hast den Report von &e%player% &7angenommen");
        cfg.addDefault("config.messages.deny", "Du hast den Report von &e%player% &7abgelehnt");
        cfg.addDefault("config.reason.1", "Hacking");
        cfg.addDefault("config.reason.2", "Teaming");
        cfg.addDefault("config.reason.3", "Beleidigung");
        cfg.addDefault("config.reason.4", "Werbung");
        cfg.addDefault("config.reason.5", "Dein Grund");
        cfg.addDefault("config.reason.6", "Dein Grund");

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
