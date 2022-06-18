package lemonjoey.allayobtainer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Events implements Listener {
    private static final Component topLine = Component.text("[AllaySummon]").color(TextColor.color(9,2,169)).asComponent();
    private static final String creationTopLine = "[AllayShop]";

    /*
    Format
    ============
    §1[AllaySummon]
    1 Netherite Block
    &aEveryone

    ============
     */

    @EventHandler
    public void onSignPlace(@NotNull SignChangeEvent event) {
        // TODO: 1/21/2022 Check back later and see if not deprecated method works
        String[] lines = event.getLines();
        if (lines[0].equalsIgnoreCase(creationTopLine)) {
            if (event.getPlayer().hasPermission("allayobtainer.createsign")) {
                event.line(0, topLine);
                event.line(1, Component.text("1 Netherite Block"));
                event.line(2, Component.text("Everyone").color(TextColor.color(43520)));
            } else {
                event.line(0, Component.empty());
                event.line(1, Component.empty());
                event.line(2, Component.empty());
                event.line(3, Component.empty());
            }
        }

    }

    @EventHandler
    public void onSignClick(@NotNull PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (event.getClickedBlock().getState() instanceof Sign sign) {
            if (sign.line(0).equals(topLine)) {
                Player player = event.getPlayer();
                if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_BLOCK)) {
                    player.getInventory().removeItem(new ItemStack(Material.NETHERITE_BLOCK, 1));
                    Location loc = event.getClickedBlock().getLocation();
                    World w = event.getClickedBlock().getWorld();
                    w.spawnEntity(loc, EntityType.ALLAY);
                }
                else player.sendMessage(Component.text("You must be holding a Netherite Block"));
            }

        }
    }
}
