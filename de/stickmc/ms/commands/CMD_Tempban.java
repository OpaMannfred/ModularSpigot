package de.stickmc.ms.commands;

import de.stickmc.ms.manager.BanManager;
import de.stickmc.ms.manager.UUIDFetcher;
import de.stickmc.ms.utils.messages.BanData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Tempban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.tempban")) {

            if (args.length == 0) {
                p.sendMessage(BanData.prefix + "§c/tempban <Spieler> <Zeit(in Minuten)> <Grund>");
            }else if(args.length == 1){
                p.sendMessage(BanData.prefix + "§c/tempban <Spieler> <Zeit(in Minuten)> <Grund>");
            }else if(args.length == 2) {
                p.sendMessage(BanData.prefix + "§c/tempban <Spieler> <Zeit(in Minuten)> <Grund>");
            } else if(args.length == 3){
                String grund = args[2];
                long zeit = Long.valueOf(args[1]);
                String name = args[0];

                p.sendMessage(BanData.prefix + BanData.success.replace("%player%", name));
                BanManager.ban(UUIDFetcher.getUUID(name).toString(),name, grund , zeit, p.getName());
            }
        }else{
            p.sendMessage(BanData.prefix + BanData.noperm);
        }

        return false;
    }
}
