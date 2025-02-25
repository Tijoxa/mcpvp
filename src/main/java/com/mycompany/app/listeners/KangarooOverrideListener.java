package com.mycompany.app.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.mycompany.app.ConfigUtils;

public class KangarooOverrideListener implements Listener {

    private final HashMap<UUID, Integer> jumpsUsed = new HashMap<>();

    @EventHandler
    public void onFireworkJump(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        ItemStack item = player.getItemInHand();

        // Check if the player is holding a firework
        if (item == null || item.getType() != Material.FIREWORK) {
            return;
        }

        event.setCancelled(true); // Cancel the event to prevent interference

        // Read Jump and Lounge values from config
        double verticalJump = ConfigUtils.kangarooJump();
        double horizontalJump = ConfigUtils.kangarooLounge();

        if (player.isSneaking()) {
            int jumps = jumpsUsed.getOrDefault(playerId, 0);
            if (jumps >= 2) {
                return;
            }
            jumpsUsed.put(playerId, jumps + 2);

            Vector direction = player.getLocation().getDirection().multiply(horizontalJump);
            direction.setY(verticalJump / 2);
            player.setVelocity(direction);
        } else {
            int jumps = jumpsUsed.getOrDefault(playerId, 0);
            if (jumps >= 2) {
                return;
            }
            jumpsUsed.put(playerId, jumps + 1);

            Vector direction = player.getLocation().getDirection().multiply(horizontalJump / 6);
            direction.setY(verticalJump);
            player.setVelocity(direction);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            // Check if the player has a firework in their hotbar
            boolean hasFirework = false;
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getType() == Material.FIREWORK) {
                    hasFirework = true;
                    break;
                }
            }

            if (hasFirework) {
                double defaultFallDamage = event.getDamage(); // Normal fall damage
                double finalFallDamage = Math.min(defaultFallDamage, ConfigUtils.kangarooFallDamage());

                event.setDamage(finalFallDamage);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerLand(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (player.isOnGround()) {
            if (jumpsUsed.containsKey(playerId)) {
                jumpsUsed.put(playerId, 0);
            }
        }
    }
}
