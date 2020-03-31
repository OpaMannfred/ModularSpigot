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

public class CMD_Unban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.unban")) {

            String name = args[0];

            if(args.length == 1){
                if (BanManager.isBanned(UUIDFetcher.getUUID(name).toString())) {

                    BanManager.unban(UUIDFetcher.getUUID(name).toString());
                    p.sendMessage(BanData.prefix + BanData.successUnban.replace("%player%", name));

                }else{
                    p.sendMessage(BanData.prefix + BanData.notBanned);
                }
            }else{
                p.sendMessage(BanData.prefix + "§cBenutze§8: §e/unban <Spieler>");
            }


        }else{
            p.sendMessage(BanData.prefix + BanData.noperm);
        }

        return false;
    }

}
