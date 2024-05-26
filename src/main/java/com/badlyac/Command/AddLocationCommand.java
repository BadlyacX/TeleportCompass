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
        if (args.length != 6) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /addLocation <Location name> <icon_item: (ex: GRASS_BLOCK)> <NETHER / THE_END / OVERWORLD> <Location X> <Location Y> <Location Z>");
            return true;
        }

        String locationName = args[0];
        String materialName = args[1].toUpperCase();
        String worldTypeInput = args[2].toUpperCase();

        String worldType = convertToWorldType(worldTypeInput);
        if (worldType == null) {
            sender.sendMessage(ChatColor.RED + "Invalid world type. Use NETHER, THE_END, or OVERWORLD.");
            return true;
        }

        int locX, locY, locZ;
        try {
            locX = Integer.parseInt(args[3]);
            locY = Integer.parseInt(args[4]);
            locZ = Integer.parseInt(args[5]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Please enter valid integer coordinates.");
            return true;
        }

        FileConfiguration config = plugin.getConfig();
        config.set("locations." + locationName + ".Material", materialName);
        config.set("locations." + locationName + ".worldType", worldType);
        config.set("locations." + locationName + ".x", locX);
        config.set("locations." + locationName + ".y", locY);
        config.set("locations." + locationName + ".z", locZ);
        plugin.saveConfig();

        sender.sendMessage(ChatColor.GREEN + "Location " + locationName + " has been saved successfully!");
        return true;
    }

    private String convertToWorldType(String worldTypeInput) {
        switch (worldTypeInput) {
            case "NETHER":
                return "world_nether";
            case "THE_END":
                return "world_the_end";
            case "OVERWORLD":
                return "world";
            default:
                return null;
        }
    }
}
