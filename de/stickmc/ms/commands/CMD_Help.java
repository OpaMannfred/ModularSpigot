package de.stickmc.ms.commands;

import de.stickmc.ms.utils.messages.HelpData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if(args.length == 0){

            HelpData.sendMSG(p);

        }else{
            p.sendMessage(HelpData.prefix + HelpData.noArg);
        }

        return false;
    }
}
