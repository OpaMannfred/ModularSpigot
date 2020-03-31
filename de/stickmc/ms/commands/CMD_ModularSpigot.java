package de.stickmc.ms.commands;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.modules.*;
import de.stickmc.ms.utils.UpdateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CMD_ModularSpigot implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("config.prefix"));

        if (p.hasPermission("ms.execute")) {
            if(args.length == 0){
                p.sendMessage(prefix + "§eModularSpigot §7by FlichtigesEtwas");
                p.sendMessage(prefix + "Alle Commands");
                p.sendMessage(prefix + "/ms reload");
                p.sendMessage(prefix + "/ms license");
                p.sendMessage(prefix + "/ms update");
                p.sendMessage(prefix + "/ms modules list");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Main.getInstance().reloadConfig();
                    p.sendMessage(prefix + "Du hast die §eConfig §7neu geladen");
                } else if (args[0].equalsIgnoreCase("license")) {
                    byte[] IP = null;
                    try {
                        IP = InetAddress.getLocalHost().getAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(prefix + "§8§m---------------[ §9§lLizenz §8]§8§m---------------");
                    p.sendMessage(prefix + "");
                    p.sendMessage(prefix + "§7Gültige Lizenz§8: §aJa");
                    p.sendMessage(prefix + "Registrierte IP§8: §e" + Main.getInstance().getServer().getIp());
                    p.sendMessage(prefix + "Deine Lizenz§8: §e" + Main.getInstance().getConfig().getString("config.license"));
                    p.sendMessage(prefix + "");
                    p.sendMessage(prefix + "§8§m---------------[ §9§lLizenz §8]§8§m---------------");
                } else if (args[0].equalsIgnoreCase("update")) {
                    File file = new File("plugins/ModularSpigot.jar");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    p.sendMessage(prefix + "Das Update wird installiert");
                    UpdateManager.downloadFile(file, "https://stickmc.de/files/ModularSpigot.jar");
                    p.sendMessage(prefix + "Das Update wurde installiert. Der Server reloadet jetzt");
                    Bukkit.reload();
                    p.sendMessage(prefix + "Die neuste vesion von ModularSpigot wurde installiert.");
                }
            }else if(args.length == 2){
                if (args[0].equalsIgnoreCase("modules")) {
                    if (args[1].equalsIgnoreCase("list")) {
                        p.sendMessage(prefix + "§8§m---------------[ §9§lModule §8]§8§m---------------");
                        p.sendMessage(prefix + "");
                        p.sendMessage(prefix + "§aAutoBroadcastModule");
                        p.sendMessage(prefix + "§aBanModule");
                        p.sendMessage(prefix + "§aBroadcastModule");
                        p.sendMessage(prefix + "§aChatModule");
                        p.sendMessage(prefix + "§aGetIpModule");
                        p.sendMessage(prefix + "§aKickModule");
                        p.sendMessage(prefix + "§aMaintenanceModule");
                        p.sendMessage(prefix + "§aMotdModule");
                        p.sendMessage(prefix + "§aMuteModule");
                        p.sendMessage(prefix + "§aNotifyModule");
                        p.sendMessage(prefix + "§aPingModule");
                        p.sendMessage(prefix + "§aReportModule");
                        p.sendMessage(prefix + "§aOnlineModule");
                        p.sendMessage(prefix + "§aOnlineTimeModule");
                        p.sendMessage(prefix + "§aTeamChatModule");
                        p.sendMessage(prefix + "§aHubModule");
                        p.sendMessage(prefix + "§aCMDBlockModule");
                        p.sendMessage(prefix + "§aHelpModule");
                        p.sendMessage(prefix + "§aDiscordModule");
                        p.sendMessage(prefix + "");
                        p.sendMessage(prefix + "§8§m---------------[ §9§lModule §8]§8§m---------------");
                    }
                }
            }

        }else{
            p.sendMessage(prefix + "§eModularSpigot §7by §9FlichtigesEtwas");
        }

        return false;
    }
}
