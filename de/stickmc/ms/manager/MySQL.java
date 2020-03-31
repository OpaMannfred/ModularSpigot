package de.stickmc.ms.manager;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MySQL {
    private String HOST = "";
    private String PORT = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";

    public static Connection con;

    public MySQL(String host, String port, String database, String user, String password) {
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;

        connect();
    }

    public void connect() {
        String Prefix = "§8[§a§lModularSpigot§8]§7:";
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
            Bukkit.getConsoleSender().sendMessage(Prefix + "§aDie Verbindung mit MySQL wurde hergestellt!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Prefix + "§cDie Verbindung mit MySQL ist fehlgeschlagen! §4Fehler: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public boolean isConnected(){
        return con != null;
    }

    public void close() {
        String Prefix = "§8[§a§lModularSpigot§8]§7:";
        try {
            if (con != null) {
                con.close();
                Bukkit.getConsoleSender().sendMessage(Prefix + "§aDie Verbindung mit MySQL wurde beendet!");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Prefix + "§cDie Verbindung mit MySQL konnte nicht beendet werden! §4Fehler: " + e.getMessage());
        }
    }

    public void update(String qre) {
        if (con != null) {
            try {
                Statement st = (Statement)con.createStatement();
                st.executeUpdate(qre);
                st.close();
            } catch (SQLException e) {
                connect();
                System.err.print(e);
            }
        }
    }

    public ResultSet query(String qre) {
        if (con != null) {
            ResultSet rs = null;
            try
            {
                Statement st = (Statement)con.createStatement();
                rs = st.executeQuery(qre);
            } catch (SQLException e) {
                connect();
                System.err.print(e);
            }
            return rs;
        }
        return null;
    }

    public void updateWithBoolean(final String qry, final boolean value) {
        if (isConnected()) {
            (new FutureTask(new Runnable() {
                PreparedStatement ps;

                public void run() {
                    try {
                        this.ps = MySQL.this.con.prepareStatement(qry);
                        this.ps.setBoolean(1, value);
                        this.ps.executeUpdate();
                        this.ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, Integer.valueOf(1))).run();
        } else {
            connect();
        }
    }

    public ResultSet getResult(final String qry) {
        if (isConnected()) {
            try {
                FutureTask<ResultSet> task = new FutureTask<>(new Callable<ResultSet>() {
                    PreparedStatement ps;

                    public ResultSet call() throws Exception {
                        this.ps = MySQL.this.con.prepareStatement(qry);
                        return this.ps.executeQuery();
                    }
                });
                task.run();
                return task.get();
            } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            connect();
        }
        return null;
    }

}
