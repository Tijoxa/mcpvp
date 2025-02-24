package com.mycompany.app.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.mycompany.app.ConfigUtils;

public class FastSoupListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!(event.getAction().equals(org.bukkit.event.block.Action.RIGHT_CLICK_AIR) ||
                event.getAction().equals(org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK))) {
            return;
        }

        if (player.getInventory().getItemInHand().getType() != Material.MUSHROOM_SOUP) {
            return;
        }

        if (player.getHealth() < player.getMaxHealth() && ConfigUtils.isFastHealth()) {
            double amount = ConfigUtils.getFastHealthAmount();
            player.setHealth(Math.min(player.getHealth() + amount, player.getMaxHealth()));

            event.getItem().setType(Material.BOWL);
            event.setCancelled(true);
            return;
        }

        if (player.getFoodLevel() < 20 && ConfigUtils.isFastHunger()) {
            int amount = ConfigUtils.getFastHungerAmount();
            player.setFoodLevel(Math.min(player.getFoodLevel() + amount, 20));
            player.setSaturation(player.getSaturation() + 7.2f);

            event.getItem().setType(Material.BOWL);
            player.getWorld().playSound(player.getLocation(),
                    ConfigUtils.getFastHungerSound(), 1, 1);
            event.setCancelled(true);
        }
    }
}
