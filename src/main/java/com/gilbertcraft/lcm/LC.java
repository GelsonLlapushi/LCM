package com.gilbertcraft.lcm;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LC extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Plugin Hyperion attivato!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Hyperion disattivato!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hyperion")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack hyperionSword = createHyperionSword();
                player.getInventory().addItem(hyperionSword);
                return true;
            } else {
                sender.sendMessage("Questo comando può essere eseguito solo da un giocatore.");
                return false;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) {
            if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals("Hyperion")) {
                if (event.getAction().name().contains("LEFT_CLICK")) {
                    player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation().add(0, 1, 0), 50);
                }
            }
        }
    }

    private ItemStack createHyperionSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = sword.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Hyperion");
        sword.setItemMeta(meta);
        return sword;
    }
}