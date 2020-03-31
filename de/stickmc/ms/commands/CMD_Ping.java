package de.stickmc.ms.commands;

import de.stickmc.ms.utils.messages.PingData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class CMD_Ping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.ping")) {
            try {
                int ping = PingData.getPing(p);
                p.sendMessage(PingData.prefix + PingData.ping.replace("%ping%", String.valueOf(ping)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            p.sendMessage(PingData.prefix + PingData.noperm);
        }

        return false;
    }
}
