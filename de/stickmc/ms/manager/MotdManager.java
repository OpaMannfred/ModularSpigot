package de.stickmc.ms.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MotdManager {

    private static File file = new File("plugins//ModularSpigot//modules//MotdModule.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static String getNormal1(){
        return getCfg().getString("settings.motd.normal.1");
    }

    public static String getNormal2(){
        return getCfg().getString("settings.motd.normal.2");
    }

    public static String getWartung1(){
        return getCfg().getString("settings.motd.maintenance.1");
    }

    public static String getWartung2(){
        return getCfg().getString("settings.motd.maintenance.2");
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }
}
