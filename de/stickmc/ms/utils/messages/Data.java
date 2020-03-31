package de.stickmc.ms.utils.messages;

import de.stickmc.ms.Main;

public class Data {

    public static String host = Main.getInstance().getConfig().getString("MySQL.Host");
    public static String port = Main.getInstance().getConfig().getString("MySQL.Port");
    public static String user = Main.getInstance().getConfig().getString("MySQL.Username");
    public static String db = Main.getInstance().getConfig().getString("MySQL.Database");
    public static String pw = Main.getInstance().getConfig().getString("MySQL.Password");

}
