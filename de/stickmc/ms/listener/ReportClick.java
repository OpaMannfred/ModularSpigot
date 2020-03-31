package de.stickmc.ms.listener;

import de.stickmc.ms.manager.ReportManager;
import de.stickmc.ms.utils.messages.ReportData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReportClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();
        try {
            if (e.getInventory().getName().equalsIgnoreCase("§cOffene Reports")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                    e.setCancelled(true);
                    ReportManager.removeUserReport(e.getCurrentItem().getItemMeta().getDisplayName());
                    p.teleport(Bukkit.getPlayerExact(e.getCurrentItem().getItemMeta().getDisplayName()));
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(ReportData.prefix + "Du hast den Report von §e" + e.getCurrentItem().getItemMeta().getDisplayName() + " §7angenommen");
                }
            }
        } catch (Exception exception) {}
    }

}
