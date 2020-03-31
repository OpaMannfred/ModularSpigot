package de.stickmc.ms.manager;

import de.stickmc.ms.Main;
import de.stickmc.ms.utils.messages.ReportData;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {

    public static boolean dataContainsUserReport(String PlayerName) {
        try {
            PreparedStatement State = MySQL.con.prepareStatement("SELECT * FROM Report WHERE name=?");
            State.setString(1, PlayerName);
            ResultSet Result = State.executeQuery();
            boolean Contains = Result.next();
            State.close();
            Result.close();
            return Contains;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void addUserReport(String Name, String Reporter, String Grund) {
        for(Player all : Bukkit.getOnlinePlayers()){
            if (all.hasPermission("ms.report.team")) {
                all.sendMessage(ReportData.prefix + "§a" + Name + " §7wurde von §c" + Reporter + " §7für §5" + Grund + " §7reportet.");
                final TextComponent tc = new TextComponent();
                tc.setText(ReportData.prefix + "§8» §aAnnehmen");
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/claimreport " + Name));
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aKlicke um den Report anzunehmen").create()));
                all.spigot().sendMessage((BaseComponent) tc);
                final TextComponent tc2 = new TextComponent();
                tc2.setText(ReportData.prefix + "§8» §cAblehnen");
                tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/denyreport " + Name));
                tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§4Klicke um den Report abzulehnen").create()));
                all.spigot().sendMessage(tc2);
            }
        }
        try {
            PreparedStatement State = MySQL.con.prepareStatement("INSERT INTO Report values(?, ?, ?)");
            State.setString(1, Name);
            State.setString(2, Reporter);
            State.setString(3, Grund);
            State.execute();
            State.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getReports() {
        try {
            PreparedStatement State = MySQL.con.prepareStatement("SELECT * FROM Report");
            ResultSet Result = State.executeQuery();
            List<String> list = new ArrayList<String>();
            while (Result.next())
            list.add(Result.getString("name"));
            Result.close();
            State.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeUserReport(String name) {
        Main.getInstance().mySQL.update("DELETE FROM Report WHERE name='" + name + "'");
    }

    public static String getReportReason(String PlayerName) {
        try {
            PreparedStatement State = MySQL.con.prepareStatement("SELECT * FROM Report WHERE name=?;");
            State.setString(1, PlayerName);
            ResultSet Result = State.executeQuery();
            Result.next();
            String s = Result.getString("reason");
            Result.close();
            State.close();
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getReporter(String PlayerName) {
        try {
            PreparedStatement State = MySQL.con.prepareStatement("SELECT * FROM Report WHERE name=?;");
            State.setString(1, PlayerName);
            ResultSet Result = State.executeQuery();
            Result.next();
            String s = Result.getString("reporter");
            Result.close();
            State.close();
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

}
