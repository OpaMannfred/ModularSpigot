package de.stickmc.ms.manager;

import de.stickmc.ms.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStats {

    public static boolean uuidExists(String ID) {
        try {

            ResultSet rs = Main.mySQL.query("SELECT * FROM Teamspeak WHERE ID= '" + ID + "'");

            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean nameExists(String name){
        try {

            ResultSet rs = Main.mySQL.query("SELECT * FROM Teamspeak WHERE NAME= '" + name + "'");

            if (rs.next()) {
                return rs.getString("NAME") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean playerExists(String uuid){
        try {

            ResultSet rs = Main.mySQL.query("SELECT * FROM Teamspeak WHERE UUID= '" + uuid + "'");

            if (rs.next()) {
                return rs.getString("NAME") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String name, String uuid, String id){
        if(!(playerExists(uuid))){
            Main.mySQL.update("INSERT INTO Teamspeak (NAME, UUID, ID, TYPE) VALUES ('" + name +"', '" + uuid + "', '" + id + "', '0');");
        }
    }

    public static void deletePlayer(String name){
        Main.mySQL.update("DELETE FROM Teamspeak WHERE NAME= '" + name + "'");
    }

    public static void deleteUUID(String uuid){
        Main.mySQL.update("DELETE FROM Teamspeak WHERE UUID= '" + uuid + "'");
    }

    public static void updateType(String name, int id){
        Main.mySQL.update("UPDATE Teamspeak SET TYPE= '" + id + "' WHERE NAME= '" + name + "';");
    }

    public static Integer getType(String uuid){
        int i = 0;
        try {

            ResultSet rs = Main.mySQL.query("SELECT * FROM Teamspeak WHERE UUID= '" + uuid + "'");
            if((!rs.next()) || (Integer.valueOf(rs.getInt("TYPE")) == null));
            i = rs.getInt("TYPE");
            return i;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

}
