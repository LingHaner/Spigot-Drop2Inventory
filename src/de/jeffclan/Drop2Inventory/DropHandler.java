package de.jeffclan.Drop2Inventory;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DropHandler {
	
	Drop2InventoryPlugin plugin;
	
	DropHandler(Drop2InventoryPlugin plugin) {
		this.plugin=plugin;
	}
	
	void drop2inventory(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		Block block = event.getBlock();
		boolean hasSilkTouch = false;
		
		ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

		if (itemInMainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
			hasSilkTouch = true;
		}

		ArrayList<ItemStack> drops;

		if (hasSilkTouch) {
			drops = new ArrayList<ItemStack>();
			for (ItemStack item : plugin.blockDropWrapper.getSilkTouchDrop(block, itemInMainHand)) {
				drops.add(item);
			}
		} else {
			drops = new ArrayList<ItemStack>();
			for (ItemStack item : plugin.blockDropWrapper.getBlockDrop(block, itemInMainHand)) {
				drops.add(item);
			}
		}

		for (ItemStack item : drops) {
			HashMap<Integer, ItemStack> leftOver = player.getInventory().addItem(item);
			for (ItemStack leftoverItem : leftOver.values()) {
				player.getWorld().dropItem(player.getLocation(), leftoverItem);
			}
		}

		if (event.getPlayer().getInventory().getItemInMainHand() != null) {
			plugin.tryToTakeDurability(event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer());
		}

		// Experience
		int experience = event.getExpToDrop();
		event.getPlayer().giveExp(experience);

		event.setCancelled(true);
		block.setType(Material.AIR);
	}

}