package de.stickmc.api;

import de.stickmc.ms.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {

    public static boolean playerExists(String uuid){
        try {
            ResultSet rs = Main.mySQL.query("SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
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
            Main.mySQL.update("INSERT INTO Coins(UUID, COINS) VALUES ('" + uuid + "', '0');");
        }
    }

    public static Integer getCoins(String uuid){
        Integer i = 0;

        if (playerExists(uuid)) {
            try {
                ResultSet rs = Main.mySQL.query("SELECT * FROM Coins WHERE UUID= '" + uuid + "'");

                if((!rs.next()) || (Integer.valueOf(rs.getInt("COINS")) == null));

                i = rs.getInt("COINS");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return i;
    }

    public static void setCoins(String uuid, Integer coins){

        if (playerExists(uuid)) {
            Main.mySQL.update("UPDATE Coins SET COINS= '" + coins + "' WHERE UUID= '" + uuid + "';");
        }else{
            createPlayer(uuid);
            setCoins(uuid, coins);
        }

    }

    public static void addCoins(String uuid, Integer coins){
        if (playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));
        }else{
            createPlayer(uuid);
            addCoins(uuid, coins);
        }
    }

    public static void removeCoins(String uuid, Integer coins){
        if (playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));
        }else{
            createPlayer(uuid);
            removeCoins(uuid, coins);
        }
    }

    public static boolean hasEnoughCoins(String uuid, int amount){
        return getCoins(uuid) >= amount;
    }

}
