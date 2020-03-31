package de.stickmc.ms.manager;

import de.stickmc.ms.Main;
import de.stickmc.ms.utils.messages.BanData;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanManager {

    public static void ban(String uuid, String playername, String reason, long seconds, String banner) {
        long current = System.currentTimeMillis();
        long end = current + seconds * 60000L;

        if(seconds == -1L){
            end = -1L;
        }

        (Main.getInstance()).mySQL.update("INSERT INTO Bans (PLAYERNAME, UUID, END, REASON, BANNER) VALUES ('" + playername + "','" + uuid + "','" + end + "','" + reason + "','" + banner + "')");
        if (Bukkit.getPlayer(playername) != null)
            Bukkit.getPlayer(playername).kickPlayer(BanData.disconnect.replace("%grund%", getReason(uuid)).replace("%zeit%", getReamainingTime(uuid)).replace("%br%", "\n"));
    }

    public static void unban(String uuid) {
        (Main.getInstance()).mySQL.update("DELETE FROM Bans WHERE UUID='" + uuid + "'");
    }

    public static boolean isBanned(String uuid) {
        ResultSet rs = (Main.getInstance()).mySQL.query("SELECT END FROM Bans WHERE UUID='" + uuid + "'");
        try {
            boolean bl = rs.next();
            return bl;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getReason(String uuid) {
        ResultSet rs = (Main.getInstance()).mySQL.query("SELECT * FROM Bans WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                String string = rs.getString("REASON");
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getBanner(String uuid) {
        ResultSet rs = (Main.getInstance()).mySQL.query("SELECT * FROM Bans WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                String string = rs.getString("BANNER");
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        ResultSet rs = (Main.getInstance()).mySQL.query("SELECT * FROM Bans WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                Long l = Long.valueOf(rs.getLong("END"));
                return l;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<String> getBannedPlayersFromPlayer(String name) {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = (Main.getInstance()).mySQL.query("SELECT * FROM Bans WHERE Banner='" + name + "'");
        try {
            while (true) {
                try {
                    if (rs.next()) {
                        list.add(rs.getString("BANNER"));
                        continue;
                    }
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        rs.close();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<String> getBannedPlayers() {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = (Main.getInstance()).mySQL.query("SELECT * FROM Bans");
        try {
            while (true) {
                try {
                    if (rs.next()) {
                        list.add(rs.getString("PLAYERNAME"));
                        continue;
                    }
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        rs.close();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getReamainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid).longValue();
        if (end == -1L)
            return "§4PERMANENT";
        long millis = end - current;
        long seconds = 0L;
        long minutes = 0L;
        long hours = 0L;
        long days = 0L;
        long weeks = 0L;
        while (millis > 1000L) {
            millis -= 1000L;
            seconds++;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            minutes++;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours++;
        }
        while (hours > 24L) {
            hours -= 24L;
            days++;
        }
        while (days > 7L) {
            days -= 7L;
            weeks++;
        }
        return "§7" + weeks + " Woche(n) " + days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n)";
    }

}
