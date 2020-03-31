package de.stickmc.ms.manager;

import de.stickmc.ms.utils.messages.BanData;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MuteManager {

    private static File file = new File("plugins//ModularSpigot//data.yml");
    private static File folder = new File("plugins//ModularSpigot");

    public static void setup(){

        if(!folder.exists()){
            folder.mkdir();
        }

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetUnmuteBoolean(String Name) {

        try {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

            cfg.set("Muted." + Name + ".Mute", Boolean.valueOf(false));

            cfg.save(file);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void unmute(String Name) {
        try {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

            long current = System.currentTimeMillis();

            cfg.set("Muted." + Name + ".Mute", Boolean.valueOf(false));
            cfg.set("Muted." + Name + ".Ende", Long.valueOf(current - 1L));

            cfg.save(file);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void mute(String Name, long minuten, String Grund) {
        long current = System.currentTimeMillis();
        long end = current + minuten * 60000L;

        if (minuten == -1L) {
            end = -1L;
        }

        try {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

            cfg.set("Muted." + Name + ".Mute", Boolean.valueOf(true));
            cfg.set("Muted." + Name + ".Ende", Long.valueOf(end));
            cfg.set("Muted." + Name + ".Grund", Grund);

            cfg.save(file);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isMuted(String Name) {
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (cfg.getBoolean("Muted." + Name + ".Mute")) {
            return true;
        }
        return false;
    }

    public static Long getEnde(String Name) {
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        long ende = -1L;
        ende = cfg.getLong("Muted." + Name + ".Ende");

        return Long.valueOf(ende);
    }

    public static String getGrund(String Name) {
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        return (String)cfg.get("Muted." + Name + ".Grund");
    }

    public static String getVerbleibendeZeit(String Name) {
        long current = System.currentTimeMillis();
        long end = getEnde(Name).longValue();

        long millis = end - current;

        long sekunden = 0L;
        long minuten = 0L;
        long stunden = 0L;
        long tage = 0L;

        while (millis > 1000L) {
            millis -= 1000L;
            sekunden++;
        }
        while (sekunden > 60L) {
            sekunden -= 60L;
            minuten++;
        }
        while (minuten > 60L) {
            minuten -= 60L;
            stunden++;
        }
        while (stunden > 24L) {
            stunden -= 24L;
            tage++;
        }

        return "§e" + tage + " §7" + BanData.reasonDay + " §e" + stunden + " §7" + BanData.reasonHour + " §e" + minuten + " §7" + BanData.reasonMinute + " §e" + sekunden + " §7Sekunde(n)";
        /*return "§e" + tage + " §7Tag(e) §e" + stunden + " §7Stunde(n) §e" + minuten + " §7Minute(n) §e" + sekunden + " §7Sekunde(n)";*/
    }

}
