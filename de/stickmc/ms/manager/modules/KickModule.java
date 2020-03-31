package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class KickModule {

    private static File file = new File("plugins//ModularSpigot//modules//KickModule.yml");
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
        cfg.addDefault("config.prefix", "&cKick &8» &7");
        cfg.addDefault("config.send_broadcast", true);
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.user_not_online", "Dieser Spieler ist &cnicht &7Online");
        cfg.addDefault("config.messages.success", "Du hast &e%player% &7erfolgreich gekickt");
        cfg.addDefault("config.messages.not_self", "Du kannst dich &cnicht &7selbst kicken");
        cfg.addDefault("config.layouts.reason.kick", "&cDu wurdest von &e%player% &cgekickt %br%%br%&7Grund&8: &4%grund%");
        cfg.addDefault("config.layouts.reason.broadcast", "Der Spieler &e%target% &7wurde von &e%player% &7gekickt. Grund&8: &e%grund%");

        cfg.addDefault("config.layouts.no_reason.kick", "&cDu wurdest von &e%player% &cgekickt");
        cfg.addDefault("config.layouts.no_reason.broadcast", "Der Spieler &e%target% &7wurde von &e%player% &7gekickt.");

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
