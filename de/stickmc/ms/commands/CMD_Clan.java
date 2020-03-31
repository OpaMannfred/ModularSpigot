package de.stickmc.ms.commands;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.ClanManager;
import de.stickmc.ms.utils.messages.ClanData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CMD_Clan implements CommandExecutor {
    private HashMap<String, String> playersInClan = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;
        UUID uuid = p.getUniqueId();

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("leave")){
                if (ClanManager.isPlayerInClan(uuid)) {
                    if(!ClanManager.isClanLeader(uuid).equals("leader")){
                        int clanID = ClanManager.getClanID(uuid).intValue();
                        String clanName = ClanManager.getClanName(clanID);
                        p.sendMessage(ClanData.prefix + ClanData.leave.replace("%clan%", clanName));
                        ClanManager.removePlayer(uuid);
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.leaderleave);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.noclan);
                }
            } else if (args[0].equalsIgnoreCase("delete")) {
                if(ClanManager.isPlayerInClan(uuid)){
                    if(ClanManager.isClanLeader(uuid).equals("leader")){
                        int clanID = ClanManager.getClanID(uuid).intValue();
                        int playerIDs = ClanManager.getPlayerID();
                        p.sendMessage(ClanData.prefix + ClanData.delete);
                        for(int i = 1; i <= playerIDs; i++){
                            String pname = ClanManager.getPlayerID(i);
                            if(ClanManager.getClanID(pname).equals(Integer.valueOf(clanID)))
                                ClanManager.removePlayer(pname);
                        }
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.notleader);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.noclan);
                }
            }else{
                sendHelp(p);
            }
        }else if(args.length == 2){

            if (args[0].equalsIgnoreCase("invite")) {
                Player t = Bukkit.getPlayerExact(args[1]);
                if(ClanManager.isPlayerInClan(uuid)){
                    if(t != null){
                        if(ClanManager.isClanLeader(uuid).equals("leader")){
                            if(!ClanManager.isPlayerInClan(t.getUniqueId())){
                                if(!ClanManager.containsKey(uuid)){
                                    int clanID = ClanManager.getClanID(uuid).intValue();
                                    String clanName = ClanManager.getClanName(clanID);
                                    p.sendMessage(ClanData.prefix + ClanData.playerinvite);
                                    t.sendMessage(ClanData.prefix + ClanData.targetinvite.replace("%clan%", clanName));
                                    ClanManager.addPlayer(t.getUniqueId(), p.getUniqueId());
                                }else{
                                    p.sendMessage(ClanData.prefix + ClanData.playerinvitet);
                                }
                            }else{
                                p.sendMessage(ClanData.prefix + ClanData.isinclan);
                            }
                        }else{
                            p.sendMessage(ClanData.prefix + ClanData.notleader);
                        }
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.notonline);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.noclan);
                }
            } else if (args[0].equalsIgnoreCase("accept")) {
                if(ClanManager.containsKey(uuid)){
                    String name = args[1];
                    int clanID = ClanManager.getClanID(ClanManager.getClan(uuid)).intValue();
                    String clanName = ClanManager.getClanName(clanID);
                    if(name.equalsIgnoreCase(clanName)){
                        String clanTag = ClanManager.getClanTag(clanID);
                        int playerID = ClanManager.getPlayerID();
                        playerID++;
                        ClanManager.addPlayer(playerID, clanID, uuid, p.getName(), clanName, clanTag, "user");
                        ClanManager.removePlayerUUID(uuid);
                        p.sendMessage(ClanData.prefix + ClanData.claninvitet);
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.request);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.request);
                }
            } else if (args[0].equalsIgnoreCase("deny")) {
                if(ClanManager.containsKey(uuid)){
                    String name = args[1];
                    int clanID = ClanManager.getClanID(ClanManager.getClan(uuid)).intValue();
                    String clanName = ClanManager.getClanName(clanID);

                    if(name.equalsIgnoreCase(clanName)){
                        ClanManager.removePlayer(uuid);
                        ClanManager.removePlayerUUID(uuid);
                        p.sendMessage(ClanData.prefix + ClanData.reject);
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.request);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.request);
                }
            } else if (args[0].equalsIgnoreCase("kick")) {
                if(ClanManager.isPlayerInClan(uuid)){
                    if(ClanManager.isClanLeader(uuid).equals("leader")){
                        String playerName = args[1];
                        if(ClanManager.isPlayerInClan(playerName)){
                            int playerClanID = ClanManager.getClanID(uuid).intValue();
                            int targetClanID = ClanManager.getClanID(playerName).intValue();
                            if(playerClanID == targetClanID){
                                ClanManager.removePlayer(playerName);
                                p.sendMessage(ClanData.prefix + ClanData.kick.replace("%player%", playerName));
                            }else{
                                p.sendMessage(ClanData.prefix + ClanData.playernoinclan);
                            }
                        }else{
                            p.sendMessage(ClanData.prefix + ClanData.playernoinclan);
                        }
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.notleader);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.noclan);
                }
            }else{
                sendHelp(p);
            }

        } else if(args.length == 3){
            if(args[0].equals("create")){
                String clanName = args[1];
                String clanTag = args[2];
                if(clanName.length() > 2 && clanName.length() < 12){
                    if(clanTag.length() > 2 && clanTag.length() < 5){
                        if(!ClanManager.isPlayerInClan(uuid)){
                            if(!ClanManager.isClanNameExtists(clanName)){
                                if(!ClanManager.isClanTagExtists(clanTag)){
                                    int clanID = ClanManager.getClanIDs().intValue();
                                    int playerID = ClanManager.getPlayerID();
                                    clanID++;
                                    playerID++;
                                    ClanManager.addPlayer(playerID, clanID, uuid, p.getName(), clanName, clanTag, "leader");
                                    String toClanID = Integer.toString(clanID);
                                    p.sendMessage(ClanData.prefix + ClanData.create.replace("%id%", toClanID).replace("%name%", clanName).replace("%tag%", clanTag));
                                }else{
                                    p.sendMessage(ClanData.prefix + ClanData.clantagexists);
                                }
                            }else{
                                p.sendMessage(ClanData.prefix + ClanData.clannameexists);
                            }
                        }else{
                            p.sendMessage(ClanData.prefix + ClanData.inclan);
                        }
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.clantaglenght);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.clannamelenght);
                }
            }else if(args[0].equals("promote")){
                if (ClanManager.isPlayerInClan(uuid)) {
                    if(ClanManager.isClanLeader(uuid).equals("leader")){
                        Player t = Bukkit.getPlayerExact(args[1]);
                        if(t != null){
                            String rank = args[2];
                            if(rank.equals("leader")
                            || rank.equals("moderator")
                            || rank.equals("user")){
                                if(!ClanManager.isClanLeader(t.getUniqueId()).equals(rank.toLowerCase())){
                                    ClanManager.updateClanRank(t.getUniqueId(), rank.toLowerCase());
                                    p.sendMessage(ClanData.prefix + ClanData.updaterank.replace("%player%", t.getName()).replace("%rank%", rank));
                                }else{
                                    p.sendMessage(ClanData.prefix + ClanData.isrank);
                                }
                            }else{
                                p.sendMessage(ClanData.prefix + ClanData.rankexists);
                            }
                        }else{
                            p.sendMessage(ClanData.prefix + ClanData.notonline);
                        }
                    }else{
                        p.sendMessage(ClanData.prefix + ClanData.notleader);
                    }
                }else{
                    p.sendMessage(ClanData.prefix + ClanData.noclan);
                }
            }else{
                sendHelp(p);
            }
        }else{
            sendHelp(p);
        }

        return false;
    }

    private static void sendHelp(Player p){
        p.sendMessage(ClanData.prefix + "§bHilfestellung §8- §7Seite 1");
        p.sendMessage("§3/clan create <Name> <Tag> §8» §7Erstelle einen neuen Clan");
        p.sendMessage("§3/clan invite <Spielername> §8» §7Lade Spieler in den Clan ein");
        p.sendMessage("§3/clan accept <ClanName> §8» §7Nehme die Anfrage an");
        p.sendMessage("§3/clan deny <ClanName> §8» §7Lehne die Anfrage ab");
        p.sendMessage("§3/clan kick <Spielername> §8» §7Kicke einen Spieler aus deinem Clan");
        p.sendMessage("§3/clan leave §8» §7Verlasse den Clan");
        p.sendMessage("§3/clan delete §8» §7Lösche den Clan");
        p.sendMessage("§3/clan promote <Spielername> <User/Moderator> §8» §7Promotet einen Spieler");
    }

}
