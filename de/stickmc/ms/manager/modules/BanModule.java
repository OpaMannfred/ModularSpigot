package de.stickmc.ms.manager.modules;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BanModule {

    private static File folder = new File("plugins//ModularSpigot");
    private static File moduleFolder = new File("plugins//ModularSpigot//modules");
    private static File banModule = new File("plugins//ModularSpigot//modules//BanModule.yml");
    private static YamlConfiguration banCFG = YamlConfiguration.loadConfiguration(banModule);

    public static void loadModule(){
        if(!folder.exists()){
            folder.mkdir();
        }
        if(!moduleFolder.exists()){
            moduleFolder.mkdir();
        }

        if(!banModule.exists()){
            try {
                banModule.createNewFile();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        banCFG.options().copyDefaults(true);

        banCFG.addDefault("config.prefix", "&cBan &8» &7");
        banCFG.addDefault("config.settings.permissions", "ms.ban");
        banCFG.addDefault("config.messages.no_permission", "Dazu hast du &ckeine &7Rechte");
        banCFG.addDefault("config.messages.already_banned", "Der Spieler ist bereits gebannt");
        banCFG.addDefault("config.messages.success", "Du hast &e%player% &7erfolgreich gebannt");
        banCFG.addDefault("config.messages.success_unban", "Du hast den Spieler &e%player% &7entbannt");
        banCFG.addDefault("config.messages.not_self_ban", "Du kannst dich &cnicht &7selbst bannen");
        banCFG.addDefault("config.messages.cannot_ban", "&cDu darfst diesen Spieler nicht bannen &7(&c%player%&7)");
        banCFG.addDefault("config.messages.not_banned", "Dieser Spieler ist &cnicht &7gebannt");
        banCFG.addDefault("config.messages.disconnect_screen", "&cDu wurdest von &bNetzwerk &cgebannt&8! %br%%br% &7Grund&8: &e%grund% %br%%br% &7Verbleibende Zeit&8: %zeit% %br%%br% &cDu kannst im &eForum &coder im &eTeamSpeak &ceinen Entbannungsantrag stellen!");

        banCFG.addDefault("config.reasons.days", "Tag(e)");
        banCFG.addDefault("config.reasons.hours", "Stunde(n)");
        banCFG.addDefault("config.reasons.minutes", "Sekunde(n)");

        try {
            banCFG.save(banModule);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static YamlConfiguration getBanCFG() {
        return banCFG;
    }

    public static File getBanModule() {
        return banModule;
    }

}
