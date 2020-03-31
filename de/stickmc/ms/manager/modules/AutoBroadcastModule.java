package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AutoBroadcastModule {

    private static File file = new File("plugins//ModularSpigot//modules//AutoBroadcastModule.yml");
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
        cfg.addDefault("config.settings.time", 30);
        List<String> list = cfg.getStringList("config.messages");
        if(list.isEmpty()){
            list.add("&e✦ &8» &7Unseren Teamspeak erreichst du über folgende IP: &dts.stickmc.de");
            list.add("&e✦ &8» &7Unseren Discord erreichst du über folgende IP: §ddc.stickmc.de");
            list.add("&e✦ &8» &7Eine weitere &dTestnachricht..");
            cfg.set("config.messages", list);
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
