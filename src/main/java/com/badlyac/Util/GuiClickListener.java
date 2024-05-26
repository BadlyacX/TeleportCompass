package com.badlyac.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Location Selector")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();

            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String locationName = item.getItemMeta().getDisplayName();
                FileConfiguration config = Bukkit.getPluginManager().getPlugin("TeleportCompass").getConfig();
                int x = config.getInt("locations." + locationName + ".x");
                int y = config.getInt("locations." + locationName + ".y");
                int z = config.getInt("locations." + locationName + ".z");

                player.teleport(new Location(player.getWorld(), x, y, z));
                player.sendMessage(ChatColor.GREEN + "Teleported to " + locationName + "!");
            }
        }
    }
}
