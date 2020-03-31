package de.stickmc.ms.commands;

import de.stickmc.ms.manager.LocationManager;
import de.stickmc.ms.utils.messages.HubData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Hub implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if(args.length == 0){
            if (LocationManager.locationIsExisting("Spawn")) {
                p.teleport(LocationManager.getLocation("Spawn"));
                p.sendMessage(HubData.prefix + HubData.teleport);
            }
        }else if(args.length == 1){
            if(p.hasPermission("ms.hub.set")){
                if (args[0].equalsIgnoreCase("set")) {
                    LocationManager.setLocation("Spawn", p.getLocation());
                    p.sendMessage(HubData.prefix + "Du hast den §eSpawn §7gesetzt");
                }
            }
        }
        return false;
    }
}
