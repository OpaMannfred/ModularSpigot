package de.stickmc.ms;

import de.stickmc.discord.DiscordBot;
import de.stickmc.ms.commands.*;
import de.stickmc.ms.listener.*;
import de.stickmc.ms.manager.*;
import de.stickmc.ms.manager.modules.OnlineTimeModule;
import de.stickmc.ms.utils.MyData;
import de.stickmc.ms.utils.messages.BlockData;
import de.stickmc.ms.utils.messages.Data;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;

    public static boolean globalMute = false;

    public static String ERROR = "§4§lFEHLER §8» §c";

    public static MySQL mySQL;

    public void onEnable(){
        instance = this;
        loadConfig();

        mySQL = new MySQL(Data.host, Data.port,Data.db, Data.user, Data.pw);
        if(mySQL.isConnected()){
            mySQL.update("CREATE TABLE IF NOT EXISTS Report (name varchar(255), reporter varchar(255), reason varchar(255));");
            mySQL.update("CREATE TABLE IF NOT EXISTS Bans (PLAYERNAME varchar(64), UUID varchar(64), END long, REASON varchar(64), BANNER varchar(64))");
            mySQL.update("CREATE TABLE IF NOT EXISTS ClanManager (PLAYERID int, CLANID int, UUID VARCHAR(64), NAME VARCHAR(64), CLANNAME VARCHAR(64), CLANTAG VARCHAR(64), RANK VARCHAR(64))");
            mySQL.update("CREATE TABLE IF NOT EXISTS OnlineTime (UUID varchar(64), HOURS int, MINUTES int);");
            mySQL.update("CREATE TABLE IF NOT EXISTS Coins(UUID varchar(64), COINS int);");
        }

        OnlineTimeModule.start();

        ModuleLoader.loadModules();
        MuteManager.setup();
        getCommand("ban").setExecutor(new CMD_Ban());
        getCommand("unban").setExecutor(new CMD_Unban());
        getCommand("tempban").setExecutor(new CMD_Tempban());
        getCommand("broadcast").setExecutor(new CMD_Broadcast());
        getCommand("chat").setExecutor(new CMD_Chat());
        getCommand("getip").setExecutor(new CMD_GetIp());
        getCommand("kick").setExecutor(new CMD_Kick());
        getCommand("maintenance").setExecutor(new CMD_Maintenance());
        getCommand("mute").setExecutor(new CMD_Mute());
        getCommand("unmute").setExecutor(new CMD_Unmute());
        getCommand("ms").setExecutor(new CMD_ModularSpigot());
        getCommand("check").setExecutor(new CMD_Check());
        getCommand("ping").setExecutor(new CMD_Ping());
        getCommand("report").setExecutor(new CMD_Report());
        getCommand("reports").setExecutor(new CMD_Reports());
        getCommand("notify").setExecutor(new CMD_Notify());
        getCommand("online").setExecutor(new CMD_Online());
        getCommand("onlinetime").setExecutor(new CMD_OnlineTime());
        getCommand("help").setExecutor(new CMD_Help());
        getCommand("hub").setExecutor(new CMD_Hub());
        getCommand("denyreport").setExecutor(new CMD_Denyreport());
        getCommand("claimreport").setExecutor(new CMD_Claimreport());
        getCommand("clan").setExecutor(new CMD_Clan());
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PingListener(), this);
        Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
        Bukkit.getPluginManager().registerEvents(new ReportClick(), this);
        Bukkit.getPluginManager().registerEvents(new CMDListener(), this);
        Bukkit.getPluginManager().registerEvents(new HubListener(), this);



        String prefix = "[INFO] ";

        sendMSG(prefix + "-------------------------------------------------------");
        sendMSG(prefix + "");
        sendMSG(prefix + "ModularSpigot by FlichtigesEtwas");
        sendMSG(prefix + "Version: 2.4.1");
        sendMSG(prefix + "");
        sendMSG(prefix + "-------------------------------------------------------");

        sendMSG(" ");
        sendMSG(prefix + "BanModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "AutoBroadcastModule wurde aktiviert");
        BroadcastManager.start();
        sendMSG(" ");
        sendMSG(prefix + "BroadcastModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "ChatModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "GetIpModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "KickModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "MaintenanceModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "MotdModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "MuteModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "NotifyModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "PingModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "ReportModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "OnlineModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "OnlineTimeModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "TeamChatModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "HubModule wurde aktiviert");
        sendMSG(" ");
        sendMSG(prefix + "CMDBlockModule wurde aktiviert");

        if(BlockData.allowed){
            File file = new File(Bukkit.getServer().getWorldContainer(), "spigot.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if(cfg.getInt("commands.tab-complete") != -1) {
                cfg.set("commands.tab-complete", -1);
            }
            try {
                cfg.save(file);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        sendMSG(" ");
        sendMSG(prefix + "HelpModule wurde aktiviert");
        if (MyData.discordToken.contains("DEINTOKEN")) {
            sendMSG(" ");
            sendMSG(prefix + "DiscordModule ist deaktiviert");
            sendMSG(" ");
            sendMSG(prefix + "Discord Bot konnte nicht gestartet werden");

        }else{
            sendMSG(" ");
            sendMSG(prefix + "DiscordModule wurde aktiviert");
            sendMSG(" ");
            sendMSG(prefix + "Discord Bot wurde gestartet");
            DiscordBot.startBot();
        }
        sendMSG(" ");

    }

    public void onDisable() {
        mySQL.close();
        DiscordBot.stopBot();
        for(Player all : Bukkit.getOnlinePlayers()){
            all.kickPlayer("§cDer Server startet neu!");
        }
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("config.prefix", "&cSpigot &8» &7");

        getConfig().addDefault("MySQL.Host", "localhost");
        getConfig().addDefault("MySQL.Username", "root");
        getConfig().addDefault("MySQL.Database", "modularspigot");
        getConfig().addDefault("MySQL.Password", "yourpassword");
        getConfig().addDefault("MySQL.Port", "3306");

        saveConfig();
    }

    private void sendMSG(String msg){
        System.out.println(msg);
    }

    public static Main getInstance() {
        return instance;
    }
}
