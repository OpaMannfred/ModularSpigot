package de.stickmc.ms.utils.messages;

import de.stickmc.ms.manager.modules.MaintenanceModule;
import org.bukkit.ChatColor;

public class MaintenanceData {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.prefix"));
    public static String noperm = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.messages.no_permission"));
    public static String addMember = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.messages.add_member"));
    public static String removeMember = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.messages.remove_member"));
    public static String maintenanceOn = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.messages.maintenance_on"));
    public static String maintenanceOff = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.messages.maintenance_off"));
    public static String kickLayout = ChatColor.translateAlternateColorCodes('&', MaintenanceModule.getCfg().getString("config.layouts.maintenance").replace("%br%", "\n"));

}
