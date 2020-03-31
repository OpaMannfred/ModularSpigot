package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NotifyModule {

    private static File file = new File("plugins//ModularSpigot//modules//NotifyModule.yml");
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
        cfg.addDefault("config.prefix", "&cNotify &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.enable", "Du hast deine &cBenachrichtigungen &7aktiviert");
        cfg.addDefault("config.messages.disable", "Du hast deine &cBenachrichtigungen &7deaktiviert");

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> getPlayers(){
        return cfg.getStringList("players");
    }

    public static File getFile() {
        return file;
    }

    public static void addPlayer(String name){
        List list = cfg.getStringList("players");
        if (!list.contains(name)) {
            list.add(name);
        }
        cfg.set("players", list);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removePlayer(String name){
        List list = cfg.getStringList("players");
        if (list.contains(name)) {
            list.remove(name);
        }
        cfg.set("players", list);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

}
