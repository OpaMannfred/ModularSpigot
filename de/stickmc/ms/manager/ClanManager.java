package de.stickmc.ms.manager;

import de.stickmc.ms.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ClanManager {
    private static HashMap<UUID, UUID> invites = new HashMap<>();

    public static void addPlayer(UUID target, UUID player) {
        if (!containsKey(target))
            invites.put(target, player);
    }

    public static void removePlayerUUID(UUID uuid) {
        if (containsKey(uuid))
            invites.remove(uuid);
    }

    public static UUID getClan(UUID uuid) {
        return invites.get(uuid);
    }

    public static boolean containsKey(UUID uuid) {
        if (invites.containsKey(uuid))
            return true;
        return false;
    }

    public static boolean isPlayerInClan(UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT UUID FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isPlayerInClan(String playername) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT NAME FROM ClanManager WHERE NAME = ?");
            ps.setString(1, playername.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isClanNameExtists(String clanname) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT CLANNAME FROM ClanManager WHERE CLANNAME = ?");
            ps.setString(1, clanname.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isClanTagExtists(String clantag) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT CLANTAG FROM ClanManager WHERE CLANTAG = ?");
            ps.setString(1, clantag.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addPlayer(int playerID, int clanID, UUID uuid, String playername, String clanname, String clantag, String rank) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "INSERT INTO ClanManager(PLAYERID,CLANID,UUID,NAME,CLANNAME,CLANTAG,RANK) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, playerID);
            ps.setInt(2, clanID);
            ps.setString(3, uuid.toString());
            ps.setString(4, playername.toString());
            ps.setString(5, clanname.toString());
            ps.setString(6, clantag.toString());
            ps.setString(7, rank.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removePlayer(UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "DELETE FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removePlayer(String playername) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "DELETE FROM ClanManager WHERE NAME = ?");
            ps.setString(1, playername.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getClanID(UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT CLANID FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Integer.valueOf(rs.getInt("CLANID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    public static Integer getClanID(String playername) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT CLANID FROM ClanManager WHERE NAME = ?");
            ps.setString(1, playername.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Integer.valueOf(rs.getInt("CLANID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    public static String getClanName(int clanID) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT CLANNAME FROM ClanManager WHERE CLANID = ?");
            ps.setInt(1, clanID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("CLANNAME");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public static String getClanTag(int clanID) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT CLANTAG FROM ClanManager WHERE CLANID = ?");
            ps.setInt(1, clanID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("CLANTAG");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public static String isClanLeader(UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT RANK FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("RANK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String isClanLeader(String playername) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT RANK FROM ClanManager WHERE NAME = ?");
            ps.setString(1, playername.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("RANK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Integer getClanIDs() {
        int clanID = 0;
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT * FROM ClanManager ORDER BY CLANID DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Integer.valueOf(rs.getInt("CLANID"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(clanID);
    }

    public static String getPlayerID(int playerID) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT NAME FROM ClanManager WHERE PLAYERID = ?");
            ps.setInt(1, playerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("NAME");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public static int getPlayerID() {
        int clanID = 0;
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT * FROM ClanManager ORDER BY PLAYERID DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt("PLAYERID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clanID;
    }

    public static void updateClanRank(UUID uuid, String rank) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "UPDATE ClanManager SET RANK = ? WHERE UUID = ?");
            ps.setString(1, rank.toString());
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerName(String playername, UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "UPDATE ClanManager SET NAME = ? WHERE UUID = ?");
            ps.setString(1, playername.toString());
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPlayerUUID(UUID uuid) {
        try {
            PreparedStatement ps = Main.mySQL.getConnection().prepareStatement(
                    "SELECT UUID FROM ClanManager WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("UUID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "null";
    }

}
