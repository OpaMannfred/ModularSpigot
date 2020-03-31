package de.stickmc.ms.commands;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.ReportManager;
import de.stickmc.ms.utils.messages.ReportData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class CMD_Reports implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.report.team")) {
            Inventory inv = Bukkit.createInventory(null, 9*6, "§cOffene Reports");

            if(!Main.mySQL.isConnected()){
                Main.mySQL.connect();
            }

            for(String all : ReportManager.getReports()){
                if (ReportManager.dataContainsUserReport(all)) {
                    ArrayList<String> lore = new ArrayList<String>();
                    ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
                    skullMeta.setOwner(all);
                    skullMeta.setDisplayName(all);
                    lore.clear();
                    lore.add("");
                    lore.add("§7Grund §8» §e" + ReportManager.getReportReason(all));
                    lore.add("§7Reporter §8» §e" + ReportManager.getReporter(all));
                    lore.add("");
                    lore.add("§8(§aKlicke zum teleportieren§8)");
                    lore.add("");
                    skullMeta.setLore(lore);
                    itemStack.setItemMeta(skullMeta);
                    inv.addItem(new ItemStack[] { itemStack } );
                }
            }

            if(inv.getItem(0) == null){
                ItemStack itemStack = new ItemStack(Material.BARRIER);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§cEs gibt keine offenen Reports.");
                itemStack.setItemMeta(itemMeta);
                inv.setItem(22, itemStack);
            }
            p.openInventory(inv);
        }else{
            p.sendMessage(ReportData.prefix + ReportData.noperm);
        }

        return false;
    }
}
