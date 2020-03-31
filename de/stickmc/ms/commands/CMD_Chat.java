package de.stickmc.ms.commands;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.modules.ChatModule;
import de.stickmc.ms.utils.messages.ChatData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class CMD_Chat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.chat")) {

            if(args.length == 0){
                p.sendMessage(ChatData.prefix + "§c/chat globalmute");
                p.sendMessage(ChatData.prefix + "§c/chat clear");
                p.sendMessage(ChatData.prefix + "§c/chat clear <Spieler>");
                p.sendMessage(ChatData.prefix + "§c/chat filter <(list)/add/remove> <Wort>");
            }else if(args.length == 1){
                if (args[0].equalsIgnoreCase("globalmute")) {
                    if(!Main.globalMute){
                        Main.globalMute = true;
                        p.sendMessage(ChatData.prefix + "Du hast den §cGlobalMute §aaktiviert");
                    }else{
                        Main.globalMute = false;
                        p.sendMessage(ChatData.prefix + "Du hast den §cGlobalMute §cdeaktiviert");
                    }
                } else if (args[0].equalsIgnoreCase("clear")) {
                    for(int i = 0; i < 1000; i++){
                        Bukkit.broadcastMessage(" ");
                    }
                    Bukkit.broadcastMessage(ChatData.prefix + "Der Chat wurde von §c" + p.getName() + " §7gecleart");
                }
            }else if(args.length == 2){
                Player t = Bukkit.getPlayerExact(args[1]);
                if (args[0].equalsIgnoreCase("clear")) {
                    if(t != null){
                        for(int i = 0; i < 1000; i++){
                            t.sendMessage(" ");
                        }
                        t.sendMessage(ChatData.prefix + "Der Chat wurde von §c" + p.getName() + " §7für dich gecleart");
                    }
                } else if (args[0].equalsIgnoreCase("filter")) {
                    if (args[1].equalsIgnoreCase("list")) {
                        if(ChatModule.getCfg().getStringList("config.words").isEmpty()){
                            p.sendMessage(ChatData.prefix + "Derzeit gibt es §ckeine §7Wörter in der Liste");
                        }else{
                            p.sendMessage(ChatData.prefix + "Alle verbotenen Wörter (§a" + ChatModule.getCfg().getStringList("config.words").size() + "§7):");
                            for(String words : ChatModule.getCfg().getStringList("config.words")){
                                p.sendMessage("§a- §7" + words.toUpperCase());
                            }
                        }
                    }
                }
            }else if(args.length == 3){
                if (args[0].equalsIgnoreCase("filter")) {
                    if (args[1].equalsIgnoreCase("add")) {
                        String word = args[2];
                        if(ChatModule.getCfg().getStringList("config.words").contains(word)){
                            p.sendMessage(ChatData.prefix + "§cDieses Wort wurde bereits hinzugefügt");
                        }else{
                            List list = ChatModule.getCfg().getStringList("config.words");
                            list.add(word.toUpperCase());
                            ChatModule.getCfg().set("config.words", list);
                            try {
                                ChatModule.getCfg().save(ChatModule.getFile());
                                p.sendMessage(ChatData.prefix + "Du hast §aerfolgreich §7ein Wort §ahinzugefügt");
                            } catch (IOException e) {
                                e.printStackTrace();
                                p.sendMessage(Main.ERROR + "Es ist ein Fehler aufgetreten");
                            }
                        }
                    }else if (args[1].equalsIgnoreCase("remove")) {
                        String word = args[2];
                        if(!ChatModule.getCfg().getStringList("config.words").contains(word)){
                            p.sendMessage(ChatData.prefix + "§cDieses Wort existiert nicht");
                        }else{
                            List list = ChatModule.getCfg().getStringList("config.words");
                            list.remove(word.toUpperCase());
                            ChatModule.getCfg().set("config.words", list);
                            try {
                                ChatModule.getCfg().save(ChatModule.getFile());
                                p.sendMessage(ChatData.prefix + "Du hast §aerfolgreich §7ein Wort §centfernt");
                            } catch (IOException e) {
                                e.printStackTrace();
                                p.sendMessage(Main.ERROR + "Es ist ein Fehler aufgetreten");
                            }
                        }
                    }
                }
            }

        }else{
            p.sendMessage(ChatData.prefix + ChatData.noperm);
        }

        return false;
    }

}
