package com.badlyac.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RemoveLocationCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public RemoveLocationCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /removeLocation <Location name>");
            return true;
        }

        String locationName = args[0];
        FileConfiguration config = plugin.getConfig();

        if (config.contains("locations." + locationName)) {
            config.set("locations." + locationName, null);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.GREEN + "Location " + locationName + " has been removed successfully!");
        } else {
            sender.sendMessage(ChatColor.RED + "Location " + locationName + " does not exist.");
        }

        return true;
    }
}
