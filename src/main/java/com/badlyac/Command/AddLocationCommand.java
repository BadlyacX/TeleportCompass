package com.badlyac.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class AddLocationCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final List<String> validMaterials = Arrays.asList("STONE", "STICK", "END_STONE", "GRASS", "GUNPOWDER", "SLIME_BLOCK", "ENDER_PEARL");

    public AddLocationCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 5) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /addLocation <Location name> <icon_item: STONE / STICK / END_STONE / GRASS / GUNPOWDER / SLIME_BLOCK / ENDER_PEARL> <Location X> <Location Y> <Location Z>");
            return true;
        }

        String locationName = args[0];
        String materialName = args[1].toUpperCase();

        if (!validMaterials.contains(materialName)) {
            sender.sendMessage(ChatColor.RED + "Invalid icon item. Please use one of the following: STONE, STICK, END_STONE, GRASS, GUNPOWDER, SLIME_BLOCK, ENDER_PEARL");
            return true;
        }

        int locX, locY, locZ;
        try {
            locX = Integer.parseInt(args[2]);
            locY = Integer.parseInt(args[3]);
            locZ = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Please enter valid integer coordinates.");
            return true;
        }

        FileConfiguration config = plugin.getConfig();
        config.set("locations." + locationName + ".Material", materialName);
        config.set("locations." + locationName + ".x", locX);
        config.set("locations." + locationName + ".y", locY);
        config.set("locations." + locationName + ".z", locZ);
        plugin.saveConfig();

        sender.sendMessage(ChatColor.GREEN + "Location " + locationName + " has been saved successfully!");
        return true;
    }
}
