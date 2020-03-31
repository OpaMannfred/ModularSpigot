package de.stickmc.ms.utils;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.modules.DiscordModule;

public class MyData {

    public static String host = Main.getInstance().getConfig().getString("MySQL.Host");
    public static String user = Main.getInstance().getConfig().getString("MySQL.Username");
    public static String database = Main.getInstance().getConfig().getString("MySQL.Database");
    public static String password = Main.getInstance().getConfig().getString("MySQL.Password");
    public static String port = Main.getInstance().getConfig().getString("MySQL.Port");

    public static String discordToken = DiscordModule.getCfg().getString("config.token");
    public static String discordPrefix = DiscordModule.getCfg().getString("config.prefix");
    public static String discordChannel = DiscordModule.getCfg().getString("config.channelid");
    public static String game = DiscordModule.getCfg().getString("config.game");

    public static String removeColor(String msg){
        return msg.replace("&0", "")
                .replace("&1", "")
                .replace("&2", "")
                .replace("&3", "")
                .replace("&4", "")
                .replace("&5", "")
                .replace("&6", "")
                .replace("&7", "")
                .replace("&8", "")
                .replace("&9", "")
                .replace("&a", "")
                .replace("&b", "")
                .replace("&c", "")
                .replace("&d", "")
                .replace("&e", "")
                .replace("&f", "")
                .replace("&k", "")
                .replace("&m", "")
                .replace("&o", "")
                .replace("&r", "")
                .replace("&l", "")
                .replace("&n", "")
                .replace("&A", "")
                .replace("&B", "")
                .replace("&C", "")
                .replace("&D", "")
                .replace("&E", "")
                .replace("&F", "")
                .replace("&K", "")
                .replace("&M", "")
                .replace("&O", "")
                .replace("&R", "")
                .replace("&L", "")
                .replace("&N", "")
                .replace("[o]", "●")
                .replace("[>>]", "»")
                .replace("[O]", "●");
    }

}
