package com.badlyac.Util;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GuiClickListener implements Listener {
    private final JavaPlugin plugin;

    public GuiClickListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Location Selector")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();

            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String locationName = item.getItemMeta().getDisplayName();
                FileConfiguration config = plugin.getConfig();
                World world = Bukkit.getWorld(config.getString("locations." + locationName + ".worldType"));
                int x = config.getInt("locations." + locationName + ".x");
                int y = config.getInt("locations." + locationName + ".y");
                int z = config.getInt("locations." + locationName + ".z");

                if (world == null) {
                    player.sendMessage(ChatColor.RED + "The specified world does not exist.");
                    return;
                }

                player.teleport(new Location(world, x, y, z));
                player.sendMessage(ChatColor.GREEN + "Teleported to " + locationName + " in world " + world.getName() + "!");
            }
        }
    }
}
