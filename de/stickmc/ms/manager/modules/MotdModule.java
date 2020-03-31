package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MotdModule {

    private static File file = new File("plugins//ModularSpigot//modules//MotdModule.yml");
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
        cfg.addDefault("config.prefix", "&cMOTD &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast &ckeine &7Rechte");
        cfg.addDefault("settings.motd.normal.1", "&9DeinServer.net &8[o] &7Dein Minigames Server");
        cfg.addDefault("settings.motd.normal.2", "&eSkyWars, BedWars &8[>>] &7Alles neu");

        cfg.addDefault("settings.motd.maintenance.1", "&9DeinServer.net &8[o] &7Dein Minigames Server");
        cfg.addDefault("settings.motd.maintenance.2", "&7Derzeit in &cWartungen");
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
