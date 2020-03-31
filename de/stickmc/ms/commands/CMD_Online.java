package de.stickmc.ms.commands;

import de.stickmc.ms.utils.messages.OnlineData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Online implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        p.sendMessage(OnlineData.prefix + OnlineData.msg.replace("%online%", Bukkit.getOnlinePlayers().size() + " Spieler"));

        return false;
    }
}
