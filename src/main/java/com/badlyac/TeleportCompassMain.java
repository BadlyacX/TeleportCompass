package com.badlyac;

import com.badlyac.Command.AddLocationCommand;
import com.badlyac.Command.RemoveLocationCommand;
import com.badlyac.Gui.CompassListener;
import com.badlyac.Util.GuiClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TeleportCompassMain extends JavaPlugin {
    public static TeleportCompassMain instance;
    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("addLocation").setExecutor(new AddLocationCommand(this));
        this.getCommand("removeLocation").setExecutor(new RemoveLocationCommand(this));
        getServer().getPluginManager().registerEvents(new GuiClickListener(instance), this);
        getServer().getPluginManager().registerEvents(new CompassListener(),this);
        this.saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        this.saveConfig();
    }
    public static TeleportCompassMain getInstance() {
        return instance;
    }
}
