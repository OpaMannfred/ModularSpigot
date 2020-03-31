package de.stickmc.ms.commands;

import de.stickmc.ms.manager.MuteManager;
import de.stickmc.ms.utils.messages.MuteData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Mute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.mute")) {

            if(args.length == 0){
               p.sendMessage(MuteData.prefix + "§c/mute [reasons] <Spieler> [Zahl]");
            }else if(args.length == 1){
                if (args[0].equalsIgnoreCase("reasons")) {
                    p.sendMessage(MuteData.prefix + "1: " + MuteData.reason1 + " §8» §e30 Tag(e)");
                    p.sendMessage(MuteData.prefix + "2: " + MuteData.reason2 + " §8» §e14 Tag(e)");
                    p.sendMessage(MuteData.prefix + "3: " + MuteData.reason3 + " §8» §e7 Tag(e)");
                    p.sendMessage(MuteData.prefix + "4: " + MuteData.reason4 + " §8» §e3 Tag(e)");
                }
            }else if(args.length == 2){
                Player t = Bukkit.getPlayer(args[0]);

                if(t != null){
                    if(args[1].equalsIgnoreCase("1")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason1));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason1);
                        t.sendMessage(MuteData.prefix + MuteData.notWrite.replace("%grund%", MuteManager.getGrund(t.getName()).replace("%zeit%", MuteManager.getVerbleibendeZeit(p.getName()))));
                    }else if(args[1].equalsIgnoreCase("2")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason2));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason2);
                        t.sendMessage(MuteData.prefix + MuteData.notWrite.replace("%grund%", MuteManager.getGrund(t.getName())).replace("%zeit%", MuteManager.getVerbleibendeZeit(p.getName())));
                    }else if(args[1].equalsIgnoreCase("3")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason3));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason3);
                        t.sendMessage(MuteData.prefix + MuteData.notWrite.replace("%grund%", MuteManager.getGrund(t.getName())).replace("%zeit%", MuteManager.getVerbleibendeZeit(p.getName())));
                    }else if(args[1].equalsIgnoreCase("4")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason4));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason4);
                        t.sendMessage(MuteData.prefix + MuteData.notWrite.replace("%grund%", MuteManager.getGrund(t.getName())).replace("%zeit%", MuteManager.getVerbleibendeZeit(p.getName())));
                    }

                }else{
                    if(args[1].equalsIgnoreCase("1")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason1));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason1);
                    }else if(args[1].equalsIgnoreCase("2")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason2));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason2);
                    }else if(args[1].equalsIgnoreCase("3")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason3));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason3);
                    }else if(args[1].equalsIgnoreCase("4")){
                        p.sendMessage(MuteData.prefix + MuteData.success.replace("%player%", t.getName()).replace("%grund%", MuteData.reason4));
                        MuteManager.mute(t.getName(), 43200, MuteData.reason4);
                    }
                }

            }

        }else{
            p.sendMessage(MuteData.prefix + MuteData.noperm);
        }

        return false;
    }
}
