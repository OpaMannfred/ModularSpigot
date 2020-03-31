package de.stickmc.ms.commands;

import de.stickmc.ms.manager.BanManager;
import de.stickmc.ms.manager.UUIDFetcher;
import de.stickmc.ms.utils.messages.BanData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CMD_Check implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.check")) {

            if(args.length == 1){
                String name = args[0];
                UUID uuid = UUIDFetcher.getUUID(name);
                if(BanManager.isBanned(UUIDFetcher.getUUID(name).toString())){
                    p.sendMessage(BanData.prefix + "§8§m----------------------------------------------");
                    p.sendMessage(BanData.prefix + "");
                    p.sendMessage(BanData.prefix + "Gebannt§8: §a✔");
                    p.sendMessage(BanData.prefix + "Grund§8: §e" + BanManager.getReason(UUIDFetcher.getUUID(name).toString()));
                    p.sendMessage(BanData.prefix + "Verbleibende Zeit§8: §e" + BanManager.getReamainingTime(UUIDFetcher.getUUID(name).toString()));
                    p.sendMessage(BanData.prefix + "Von§8: §e" + BanManager.getBanner(UUIDFetcher.getUUID(name).toString()));
                    p.sendMessage(BanData.prefix + "");
                    p.sendMessage(BanData.prefix + "§8§m----------------------------------------------");
                }else{
                    p.sendMessage(BanData.prefix + "§8§m----------------------------------------------");
                    p.sendMessage(BanData.prefix + "");
                    p.sendMessage(BanData.prefix + "Gebannt§8: §4✖");
                    p.sendMessage(BanData.prefix + "");
                    p.sendMessage(BanData.prefix + "§8§m----------------------------------------------");
                }
            }else{
                p.sendMessage(BanData.prefix + "§cBenutze§8: §e/check <Spieler>");
            }


        }else{
            p.sendMessage(BanData.prefix + BanData.noperm);
        }

        return false;
    }
}
