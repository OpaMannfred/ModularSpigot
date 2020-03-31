package de.stickmc.api;

import de.stickmc.ms.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OnlineAPI {

    public static boolean playerExists(String uuid){
        try {
            ResultSet rs = Main.mySQL.query("SELECT * FROM OnlineTime WHERE UUID= '" + uuid + "'");
            if(rs.next()){
                return rs.getString("UUID") != null;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid){
        if(!(playerExists(uuid))){
            Main.mySQL.update("INSERT INTO OnlineTime(UUID, HOURS, MINUTES) VALUES ('" + uuid + "', '0', '0');");
        }
    }

    public static Integer getHours(String uuid){
        Integer i = 0;

        if (playerExists(uuid)) {
            try {
                ResultSet rs = Main.mySQL.query("SELECT * FROM OnlineTime WHERE UUID= '" + uuid + "'");

                if((!rs.next()) || (Integer.valueOf(rs.getInt("HOURS")) == null));

                i = rs.getInt("HOURS");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return i;
    }

    public static void setHours(String uuid, Integer coins){

        if (playerExists(uuid)) {
            Main.mySQL.update("UPDATE Coins SET HOURS= '" + coins + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setHours(uuid, coins);
        }

    }

    public static void addHours(String uuid, Integer coins){
        if (playerExists(uuid)) {
            setHours(uuid, Integer.valueOf(getHours(uuid).intValue() + coins.intValue()));
        }else{
            createPlayer(uuid);
            addHours(uuid, coins);
        }
    }

    public static void removeHours(String uuid, Integer coins){
        if (playerExists(uuid)) {
            setHours(uuid, Integer.valueOf(getHours(uuid).intValue() - coins.intValue()));
        }else{
            createPlayer(uuid);
            removeHours(uuid, coins);
        }
    }

    public static Integer getMinutes(String uuid){
        Integer i = 0;

        if (playerExists(uuid)) {
            try {
                ResultSet rs = Main.mySQL.query("SELECT * FROM OnlineTime WHERE UUID= '" + uuid + "'");

                if((!rs.next()) || (Integer.valueOf(rs.getInt("MINUTES")) == null));

                i = rs.getInt("MINUTES");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return i;
    }

    public static void setMinutes(String uuid, Integer coins){

        if (playerExists(uuid)) {
            Main.mySQL.update("UPDATE Coins SET MINUTES= '" + coins + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setMinutes(uuid, coins);
        }

    }

    public static void addMinutes(String uuid, Integer coins){
        if (playerExists(uuid)) {
            setMinutes(uuid, Integer.valueOf(getMinutes(uuid).intValue() + coins.intValue()));
        }else{
            createPlayer(uuid);
            addMinutes(uuid, coins);
        }
    }

    public static void removeMinutes(String uuid, Integer coins){
        if (playerExists(uuid)) {
            setMinutes(uuid, Integer.valueOf(getMinutes(uuid).intValue() - coins.intValue()));
        }else{
            createPlayer(uuid);
            removeMinutes(uuid, coins);
        }
    }

}
