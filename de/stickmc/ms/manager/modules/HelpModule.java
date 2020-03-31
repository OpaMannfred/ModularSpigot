package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelpModule {

    private static File file = new File("plugins//ModularSpigot//modules//HelpModule.yml");
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
        cfg.addDefault("config.prefix", "&cHelp &8» &7");
        cfg.addDefault("config.settings.unkown_command.enabled", true);
        cfg.addDefault("config.settings.unkown_command.message", "Dieser Befehl konnte &cnicht &7gefunden werden&c!");
        cfg.addDefault("config.messages.usage", "Bitte benutze &c/help &7ohne Argumente");
        List list = cfg.getStringList("config.messages.help");
        if(list.isEmpty()) {
            list.add("&8&m------------[ &c&lHilfe &8]&8&m------------");
            list.add("Twitter &8» &f@StickMC");
            list.add("Webseite &8» &fstickmc.de");
            list.add("Teamspeak &8» &fStickMC.de");
            list.add(" ");
            list.add("&e/spawn &8» &7Teleportiere dich zum Spawn");
            list.add("&3/friend &8» &7Hilfeseite vom FreundeSystem");
            list.add("&5/party &8» &7Hilfeseite vom PartySystem");
            list.add("&9/clan &8» &7Hilfeseite vom ClanSystem");
            list.add("&8&m------------[ &c&lHilfe &8]&8&m------------");
            cfg.set("config.messages.help", list);
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
