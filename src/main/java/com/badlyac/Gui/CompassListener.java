package com.badlyac.Gui;

import com.badlyac.TeleportCompassMain;
import com.badlyac.Util.GuiCreator;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CompassListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {
                GuiCreator guiCreator = new GuiCreator(TeleportCompassMain.getInstance());
                guiCreator.openCompassGui(event.getPlayer());
            }
        }
    }
}
