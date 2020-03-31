package de.stickmc.ms.manager.modules;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ClanModule {

    private static File file = new File("plugins//ModularSpigot//modules//ClanModule.yml");
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
        cfg.addDefault("config.prefix", "&bClan &8➤ &7");

        cfg.addDefault("config.messages.noclan", "&7Du bist derzeit in keinem Clan");
        cfg.addDefault("config.messages.inclan", "&7Du bist bereits in einem Clan");
        cfg.addDefault("config.messages.playernoinclan", "&7Dieser Spieler ist nicht in deinem Clan");
        cfg.addDefault("config.messages.isinclan", "&7Dieser Spieler ist bereits in einem Clan");
        cfg.addDefault("config.messages.notonline", "&7Dieser Spieler ist derzeit nicht online");
        cfg.addDefault("config.messages.notleader", "&7Du musst Leader sein um diesen Command ausführen zu können");
        cfg.addDefault("config.messages.create", "&7Du hast einen neuen Clan erstellt unter der ID &c%id% &7und unter dem Clanname &c%name% &7und unter dem Clantag &c%tag%");
        cfg.addDefault("config.messages.leave", "&7Du hast den Clan &c%clan% &fverlassen");
        cfg.addDefault("config.messages.delete", "&7Du hast deinen Clan gelöscht");
        cfg.addDefault("config.messages.leaderleave", "&7Da du Leader des Clans bist musst du &c/clan delete &7nutzen");
        cfg.addDefault("config.messages.playerinvite", "&7Du hast diesem Spieler eine &cClananfrage &7gesendet");
        cfg.addDefault("config.messages.targetinvite", "&7Du hast eine Clananfrage vom Clan &c%clan% &7erhalten. &7Annehmen &c/clan accept %clan% &7oder Ablehnen &c/clan deny %clan%");
        cfg.addDefault("config.messages.playerinvitet", "&7Du hast diesem Spieler bereits eine &cClananfrage &7gesendet");
        cfg.addDefault("config.messages.claninvitet", "&7Du bist dem Clan erfolgreich beigetreten");
        cfg.addDefault("config.messages.request", "&7Du hast keine Anfrage von diesem Clan bekommen");
        cfg.addDefault("config.messages.kick", "&7Du hast den Spieler &c%player% &7aus dem Clan gekickt");
        cfg.addDefault("config.messages.reject", "&7Du hast die Clananfrage abgelehnt");
        cfg.addDefault("config.messages.clantagexists", "Clantag wird derzeit verwendet! Bitte nutze einen anderen!");
        cfg.addDefault("config.messages.clannameexists", "&7Dieser Clanname wird derzeit verwendet! Bitte nutze einen anderen!");
        cfg.addDefault("config.messages.clantaglenght", "&7Der Clantag muss mindestens 3 und maximal 5 Zeichen lang sein");
        cfg.addDefault("config.messages.clannamelenght", "&7Der Clanname muss mindestens 3 und maximal 12 Zeichen lang sein");
        cfg.addDefault("config.messages.isrank", "&7Dieser Spieler hat bereits diesen Rang");
        cfg.addDefault("config.messages.updaterank", "&7Du hast dem Spieler &c%player% &7den Rang &c%rank% &fgegeben");
        cfg.addDefault("config.messages.rankexists", "&7Dieser Rang exestiert nicht");
        cfg.addDefault("config.messages.listtitle", "&8---------- &cClanmitglieder &8----------");
        cfg.addDefault("config.messages.listfooter", "&8---------- &cClanmitglieder &8----------");
        cfg.addDefault("config.messages.leader", "&cClanleader&7:");
        cfg.addDefault("config.messages.moderator", "&cClanmoderatoren&7:");
        cfg.addDefault("config.messages.user", "&cClanmitglieder&7:");
        cfg.addDefault("config.messages.listplayers", "&8» &7");

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTransMsg(String msg){
        return ChatColor.translateAlternateColorCodes('&', cfg.getString("config.messages." + msg));
    }

    public static String getTransPrefix(){
        return ChatColor.translateAlternateColorCodes('&', cfg.getString("config.prefix"));
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static File getFile() {
        return file;
    }

}
