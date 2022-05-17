package me.fhoz.slimefunnotchapple.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.fhoz.slimefunnotchapple.SlimefunNotchApple;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class CraftingListener implements Listener {
    public CraftingListener(@Nonnull SlimefunNotchApple plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void eventTest(@NotNull PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        if (!isWorkBench(inventory)) {
            return;
        }
        if (!verifyMaterials(inventory)) {
            return;
        }
        if (verifySlimefunGoldBlocks(inventory)) {
            inventory.setResult(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        }
    }

    private static boolean isWorkBench(@NotNull Inventory inventory) {
        return inventory.getType().equals(InventoryType.WORKBENCH);
    }

    private static boolean verifySlimefunGoldBlocks(@NotNull Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item == null) {
                continue;
            }
            if (!item.getType().equals(Material.GOLD_BLOCK)) {
                continue;
            }
            if (SlimefunItem.getByItem(item) == null) {
                return false;
            }
            if (!SlimefunItem.getByItem(item).equals(SlimefunItems.GOLD_24K_BLOCK.getItem())) {
                return false;
            }
        }
        return true;
    }

    private static boolean verifyMaterials(@NotNull Inventory inventory) {
        int goldBlocks = 0;
        int apples = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item == null) {
                continue;
            }
            if (item.getType().equals(Material.GOLD_BLOCK)) {
                goldBlocks++;
            } else if (item.getType().equals(Material.APPLE)) {
                apples++;
            }
        }
        return goldBlocks == 8 && apples == 1;
    }
}
