package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MaintenanceModule {

    private static File file = new File("plugins//ModularSpigot//modules//MaintenanceModule.yml");
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
        cfg.addDefault("config.prefix", "&cMaintenance &8» &7");
        cfg.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        cfg.addDefault("config.messages.add_member", "Du hast &e%player% &7hinzugefügt");
        cfg.addDefault("config.messages.remove_member", "Du hast &e%player% &7entfernt");
        cfg.addDefault("config.messages.maintenance_on", "Du hast die Wartungen &aangeschaltet");
        cfg.addDefault("config.messages.maintenance_off", "Du hast die Wartungen &causgeschaltet");
        cfg.addDefault("config.maintenance", false);
        cfg.addDefault("config.layouts.maintenance", "&cDerzeit sind Wartungen aktiv %br%%br% &7Versuche es später erneut");
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addMember(String name){
        List list = cfg.getStringList("Members");
        if (!list.contains(name)) {
            list.add(name);
            cfg.set("Members", list);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeMember(String name){
        List list = cfg.getStringList("Members");
        if (list.contains(name)) {
            list.remove(name);
            cfg.set("Members", list);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List getMembers(){
        return cfg.getStringList("Members");
    }

    public static void enableMaintenance(){
        cfg.set("config.maintenance", true);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void disableMaintenance(){
        cfg.set("config.maintenance", false);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEnabled(){
        return cfg.getBoolean("config.maintenance");
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static File getFile() {
        return file;
    }
}
