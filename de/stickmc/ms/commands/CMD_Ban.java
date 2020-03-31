package de.stickmc.ms.commands;

import de.stickmc.ms.manager.BanManager;
import de.stickmc.ms.manager.UUIDFetcher;
import de.stickmc.ms.manager.modules.NotifyModule;
import de.stickmc.ms.utils.messages.BanData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Ban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.ban")) {

            if(args.length == 2){
                String name = args[0];

                if (!name.equalsIgnoreCase(p.getName())) {
                    if (!Bukkit.getPlayer(name).hasPermission("ms.ban.bypass")) {
                        if (args[1].equalsIgnoreCase("1")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Clientmods", -1L, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("2")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Teaming", 20160, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("3")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Unerlaubter Name", 10080, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("4")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Unerlaubter Skin", 10080, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("5")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Rassismus", 30240, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("6")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Provokation", 4320, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("7")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Extreme Beleidigung", 43200, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("8")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Werbung", 10080, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("9")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Accountliste", 518400, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("10")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Reportausnutzung", 10080, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("11")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Bugusing", 30240, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else if (args[1].equalsIgnoreCase("12")) {
                            BanManager.ban(UUIDFetcher.getUUID(name).toString(), name, "Drohung", 20160, p.getName());
                            p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name).replace("%grund%", BanManager.getReason(UUIDFetcher.getUUID(name).toString())));
                        }else{
                            p.sendMessage(BanData.prefix + "§cBenutze§8: §e/ban <Spieler> <ID>");
                            p.sendMessage(BanData.prefix + "§8§m-------------------------------------");
                            p.sendMessage(BanData.prefix + "§cClientmods §8➜ §7Permanent §8➜ §71");
                            p.sendMessage(BanData.prefix + "§cTeaming §8➜ §714 Tage §8➜ §72");
                            p.sendMessage(BanData.prefix + "§cUnerlaubter Name §8➜ §77 Tage §8➜ §73");
                            p.sendMessage(BanData.prefix + "§cUnerlaubter Skin §8➜ §77 Tage §8➜ §74");
                            p.sendMessage(BanData.prefix + "§cRassismus §8➜ §721 Tage §8➜ §75");
                            p.sendMessage(BanData.prefix + "§cProvokation §8➜ §73 Tage §8➜ §76");
                            p.sendMessage(BanData.prefix + "§cExtreme Beleidigung §8➜ §730 Tage §8➜ §77");
                            p.sendMessage(BanData.prefix + "§cWerbung §8➜ §77 Tage §8➜ §78");
                            p.sendMessage(BanData.prefix + "§cAccountliste §8➜ §7360 Tage §8➜ §79");
                            p.sendMessage(BanData.prefix + "§cReportausnutzung §8➜ §77 Tage §8➜ §710");
                            p.sendMessage(BanData.prefix + "§cBugusing §8➜ §721 Tage §8➜ §711");
                            p.sendMessage(BanData.prefix + "§cDrohung §8➜ §714 Tage §8➜ §712");
                            p.sendMessage(BanData.prefix + "§8§m-------------------------------------");
                        }
                    }else{
                        p.sendMessage(BanData.prefix + BanData.cannot_ban.replace("%player%", name));
                    }
                }else{
                    p.sendMessage(BanData.prefix + BanData.not_self_ban);
                }


            }else{
                p.sendMessage(BanData.prefix + "§cBenutze§8: §e/ban <Spieler> <ID>");
                p.sendMessage(BanData.prefix + "§8§m-------------------------------------");
                p.sendMessage(BanData.prefix + "§cClientmods §8➜ §7Permanent §8➜ §71");
                p.sendMessage(BanData.prefix + "§cTeaming §8➜ §714 Tage §8➜ §72");
                p.sendMessage(BanData.prefix + "§cUnerlaubter Name §8➜ §77 Tage §8➜ §73");
                p.sendMessage(BanData.prefix + "§cUnerlaubter Skin §8➜ §77 Tage §8➜ §74");
                p.sendMessage(BanData.prefix + "§cRassismus §8➜ §721 Tage §8➜ §75");
                p.sendMessage(BanData.prefix + "§cProvokation §8➜ §73 Tage §8➜ §76");
                p.sendMessage(BanData.prefix + "§cExtreme Beleidigung §8➜ §730 Tage §8➜ §77");
                p.sendMessage(BanData.prefix + "§cWerbung §8➜ §77 Tage §8➜ §78");
                p.sendMessage(BanData.prefix + "§cAccountliste §8➜ §7360 Tage §8➜ §79");
                p.sendMessage(BanData.prefix + "§cReportausnutzung §8➜ §77 Tage §8➜ §710");
                p.sendMessage(BanData.prefix + "§cBugusing §8➜ §721 Tage §8➜ §711");
                p.sendMessage(BanData.prefix + "§cDrohung §8➜ §714 Tage §8➜ §712");
                p.sendMessage(BanData.prefix + "§8§m-------------------------------------");
            }

        }else{
            p.sendMessage(BanData.prefix + BanData.noperm);
        }

        return false;
    }

}
