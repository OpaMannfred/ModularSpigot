package de.stickmc.ms.commands;

import de.stickmc.ms.manager.ReportManager;
import de.stickmc.ms.utils.messages.ReportData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Report implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ReportData.prefix + "§cBenutze§8: §e/report <Name> <Grund>");
            p.sendMessage(ReportData.prefix + "Verfügbare Gründe§8: §c" + ReportData.reason1
                    + "§8, §c" + ReportData.reason2
                    + "§8, §c" + ReportData.reason3
                    + "§8, §c" + ReportData.reason4
                    + "§8, §c" + ReportData.reason5
                    + "§8, §c" + ReportData.reason6);
        }else if(args.length == 1){
            p.sendMessage(ReportData.prefix + "§cBenutze§8: §e/report <Name> <Grund>");
            p.sendMessage(ReportData.prefix + "Verfügbare Gründe§8: §c" + ReportData.reason1
                    + "§8, §c" + ReportData.reason2
                    + "§8, §c" + ReportData.reason3
                    + "§8, §c" + ReportData.reason4
                    + "§8, §c" + ReportData.reason5
                    + "§8, §c" + ReportData.reason6);
        }else if(args.length == 2){
            Player t = Bukkit.getPlayerExact(args[0]);
            if(t != null){
                if (!t.getName().equalsIgnoreCase(p.getName())) {
                    if(args[1].equalsIgnoreCase(ReportData.reason1)
                            || args[1].equalsIgnoreCase(ReportData.reason2)
                            || args[1].equalsIgnoreCase(ReportData.reason3)
                            || args[1].equalsIgnoreCase(ReportData.reason4)
                            || args[1].equalsIgnoreCase(ReportData.reason5)
                            || args[1].equalsIgnoreCase(ReportData.reason6)){
                        ReportManager.addUserReport(t.getName(), p.getName(), args[1]);
                        p.sendMessage(ReportData.prefix + ReportData.report.replace("%player%", t.getName()));
                    }else{
                        p.sendMessage(ReportData.prefix + "§cBenutze§8: §e/report <Name> <Grund>");
                        p.sendMessage(ReportData.prefix + "Verfügbare Gründe§8: §c" + ReportData.reason1
                                + "§8, §c" + ReportData.reason2
                                + "§8, §c" + ReportData.reason3
                                + "§8, §c" + ReportData.reason4
                                + "§8, §c" + ReportData.reason5
                                + "§8, §c" + ReportData.reason6);
                    }
                }else{
                    p.sendMessage(ReportData.prefix + ReportData.notself);
                }
            }else{
                p.sendMessage(ReportData.prefix + ReportData.notonline);
            }

        }

        return false;
    }
}
