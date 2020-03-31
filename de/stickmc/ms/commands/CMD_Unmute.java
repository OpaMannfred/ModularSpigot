package de.stickmc.ms.commands;

import de.stickmc.ms.manager.MuteManager;
import de.stickmc.ms.utils.messages.MuteData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Unmute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;

            if (p.hasPermission("ms.unmute")) {
                if(args.length == 0){
                    p.sendMessage(MuteData.prefix + "/unmute <Spieler>");
                }else if(args.length == 1){
                    if (MuteManager.isMuted(args[0])) {
                        MuteManager.unmute(args[0]);
                        p.sendMessage(MuteData.prefix + "Du hast den Spieler §e" + args[0] + " §7entmuted");

                    }else{
                        p.sendMessage(MuteData.prefix + "Dieser Spieler ist §cnicht §7gemuted");
                    }
                }
            }else{
                p.sendMessage(MuteData.prefix + MuteData.noperm);
            }
        }else{
            if(args.length == 0){
                sendConsole("§c/unmute <Spieler>");
            }else if(args.length == 1){
                if (MuteManager.isMuted(args[0])) {
                    MuteManager.unmute(args[0]);
                    sendConsole("Du hast den Spieler §e" + args[0] + " §7entmuted");
                }else{
                    sendConsole("Dieser Spieler ist §cnicht §7gemuted");
                }
            }
        }

        return false;
    }

    private static void sendConsole(String msg){
        Bukkit.getConsoleSender().sendMessage("§c§lCONSOLE §8| §7"+ msg);
    }

}
