package com.badlyac.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GuiCreator {
    private JavaPlugin plugin;
    private Material material;

    public GuiCreator(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void openCompassGui(Player player) {
        Inventory gui = Bukkit.createInventory(player, 54, "Location Selector");

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection locations = config.getConfigurationSection("locations");
        if (locations != null) {
            for (String key : locations.getKeys(false)) {
                String materialName = locations.getString(key + ".Material");
                if (materialName != null) {
                    material = Material.matchMaterial(materialName);
                }
                int x = locations.getInt(key + ".x");
                int y = locations.getInt(key + ".y");
                int z = locations.getInt(key + ".z");

                ItemStack item = new ItemStack(material != null ? material : Material.BARRIER);
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(key);
                    List<String> lore = new ArrayList<>();
                    lore.add("X: " + x + ", Y: " + y + ", Z: " + z);
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                }

                gui.addItem(item);
            }
        }

        player.openInventory(gui);
    }
}
