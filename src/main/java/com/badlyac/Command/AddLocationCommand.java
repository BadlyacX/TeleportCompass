package com.badlyac.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class AddLocationCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public AddLocationCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 5) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /addLocation <Location name> <icon_item: (ex: GRASS_BLOCK)> <Location X> <Location Y> <Location Z>");
            return true;
        }

        String locationName = args[0];
        String materialName = args[1].toUpperCase();
        
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
