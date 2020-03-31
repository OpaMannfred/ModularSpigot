package de.stickmc.ms.commands;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.modules.MaintenanceModule;
import de.stickmc.ms.utils.messages.MaintenanceData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Maintenance implements CommandExecutor {

    private static int tpi = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;



        if (p.hasPermission("ms.maintenance")) {

            if(args.length == 0){
                p.sendMessage(MaintenanceData.prefix + "§c/maintenance <on/off/list>");
                p.sendMessage(MaintenanceData.prefix + "§c/maintenance <add/remove> <Spieler>");
            }else if(args.length == 1){
                if (args[0].equalsIgnoreCase("on")) {
                    MaintenanceModule.enableMaintenance();
                    p.sendMessage(MaintenanceData.prefix + MaintenanceData.maintenanceOn);
                }else if (args[0].equalsIgnoreCase("off")) {
                    MaintenanceModule.disableMaintenance();
                    p.sendMessage(MaintenanceData.prefix + MaintenanceData.maintenanceOff);
                } else if (args[0].equalsIgnoreCase("list")) {
                    p.sendMessage(MaintenanceData.prefix + "Dies sind alle Spieler auf der Whitelist§8:");
                    for(Object all : MaintenanceModule.getMembers()){
                        tpi++;
                        p.sendMessage("§7" + tpi + "." + " §8» §c" +  String.valueOf(all));
                    }

                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            tpi = 0;
                        }
                    }, 2);
                }
            }else if(args.length == 2){
                if (args[0].equalsIgnoreCase("add")) {
                    String name = args[1];
                    if (!MaintenanceModule.getMembers().contains(name)) {
                        MaintenanceModule.addMember(name);
                        p.sendMessage(MaintenanceData.prefix + MaintenanceData.addMember.replace("%player%", name));
                    }else {
                        p.sendMessage(MaintenanceData.prefix + "Der Spieler ist schon auf der Whitelist");
                    }
                }else if (args[0].equalsIgnoreCase("remove")) {
                    String name = args[1];
                    if (MaintenanceModule.getMembers().contains(name)) {
                        MaintenanceModule.removeMember(name);
                        p.sendMessage(MaintenanceData.prefix + MaintenanceData.removeMember.replace("%player%", name));
                    }else{
                        p.sendMessage(MaintenanceData.prefix + "Der Spieler ist §cnicht §7auf der Whitelist");
                    }
                }
            }

        }else{
            p.sendMessage(MaintenanceData.prefix + MaintenanceData.noperm);
        }

        return false;
    }
}
