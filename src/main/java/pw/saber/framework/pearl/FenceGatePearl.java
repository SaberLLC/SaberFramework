package pw.saber.framework.pearl;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Gate;
import org.bukkit.material.MaterialData;

public class FenceGatePearl implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (player.getItemInHand().getType() == Material.ENDER_PEARL &&
                event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.FENCE_GATE) {
            BlockState state = block.getState();
            MaterialData data = state.getData();
            if (data instanceof Gate) {
                Gate gate = (Gate) data;
                boolean gateIsOpen = gate.isOpen();
                event.setCancelled(true);
                if (gateIsOpen) {
                    player.launchProjectile(EnderPearl.class);
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                }
            }
        }
    }
}
