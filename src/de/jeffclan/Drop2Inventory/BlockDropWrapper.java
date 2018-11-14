package de.jeffclan.Drop2Inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;

public class BlockDropWrapper {
	
	// STUFF THAT I HAVE LEFT OUT:
	// Double Slabs -> normal slabs (maybe they will disappear? I have to check)
	// Infested blocks -> normal blocks
	
	Random rand;
	
	HashMap<Material,Material> silkTouchMap;
	HashMap<Material, ItemStack[]> dropMap;
	
	BlockDropWrapper() {
		this.silkTouchMap=new HashMap<Material,Material>();
		this.dropMap=new HashMap<Material,ItemStack[]>();	
		this.rand=new Random();
	}
	
	boolean isPickaxe(ItemStack itemInMainHand) {
		return itemInMainHand.getType().name().toLowerCase().endsWith("_pickaxe");
	}
	
	boolean isShovel(ItemStack itemInMainHand) {
		return itemInMainHand.getType().name().toLowerCase().endsWith("_shovel");
	}
	
	boolean isFullyGrown(Block block) {
		Ageable ageable = (Ageable) block.getBlockData();
		if(ageable.getAge() == ageable.getMaximumAge()) {
			System.out.println("This crop is fully grown");
			return true;
		}
		System.out.println("This crop is NOT fully grown");
		return false;
	}
	
	public ItemStack[] getSilkTouchDrop(Block blockDestroyed,ItemStack itemInMainHand) {
		
		ArrayList<ItemStack> tmp = new ArrayList<ItemStack>();
		
		Material mat = blockDestroyed.getType();
		
		// https://minecraft.gamepedia.com/Enchanting#Silk_Touch
		
		if(mat == Material.BEETROOTS) {
			if(isFullyGrown(blockDestroyed)) {
				// fully grown
				tmp.add(new ItemStack(Material.BEETROOT,1));
				int seedcount = rand.nextInt(4);
				if(seedcount>0) {
					tmp.add(new ItemStack(Material.BEETROOT_SEEDS,seedcount));
				}
			} else {
				// not fully grown
				tmp.add(new ItemStack(Material.BEETROOT_SEEDS,1));
			}
		} else if(mat == Material.CAKE) {
			return new ItemStack[0];
		} else if(mat == Material.CARROTS) {
			if(isFullyGrown(blockDestroyed)) {
				tmp.add(new ItemStack(Material.CARROT,rand.nextInt(4) + 1));
			} else {
				tmp.add(new ItemStack(Material.CARROT,1));
			}
		} else if(mat == Material.COAL_ORE) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(Material.COAL_ORE,1));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.COCOA) {
			tmp.add(new ItemStack(Material.COCOA_BEANS, rand.nextInt(3)+1));
		} else if(mat.name().toLowerCase().endsWith("_coral_block")) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(mat,1));
			} else {
				for(ItemStack item : getBlockDrop(blockDestroyed,itemInMainHand)) {
					tmp.add(item);
				}
			}
		} else if(mat==Material.DIAMOND_ORE) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(Material.DIAMOND_ORE,1));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.EMERALD_ORE) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(Material.EMERALD_ORE,1));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.FARMLAND) {
			tmp.add(new ItemStack(Material.DIRT,1));
		} else if(mat==Material.FIRE) {
			return new ItemStack[0];
		} else if(mat==Material.FROSTED_ICE) {
			return new ItemStack[0];
		} else if(mat==Material.GRAVEL) {
			tmp.add(new ItemStack(Material.GRAVEL,1));
		} else if(mat==Material.LAPIS_ORE) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(Material.LAPIS_ORE,1));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.MELON_STEM) {
			if(!isFullyGrown(blockDestroyed)) {
				return new ItemStack[0];
			}
			int seedcount = rand.nextInt(5);
			if(seedcount>0) {
				tmp.add(new ItemStack(Material.MELON_SEEDS,seedcount));
			}
		} else if(mat == Material.SPAWNER) {
			return new ItemStack[0];
		} else if(mat==Material.NETHER_QUARTZ_ORE) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(Material.QUARTZ,1));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.NETHER_WART) {
			if(isFullyGrown(blockDestroyed)) {
				tmp.add(new ItemStack(Material.NETHER_WART,rand.nextInt(4)+1));
			} else {
				tmp.add(new ItemStack(Material.NETHER_WART,1));
			}
		} else if(mat==Material.POTATOES) {
			if(isFullyGrown(blockDestroyed)) {
				tmp.add(new ItemStack(Material.POTATO,rand.nextInt(4)+1));
			} else {
				tmp.add(new ItemStack(Material.POTATO,1));
			}
		} else if(mat==Material.PUMPKIN_STEM) {
			if(!isFullyGrown(blockDestroyed)) {
				return new ItemStack[0];
			}
			int seedcount = rand.nextInt(5);
			if(seedcount>0) {
				tmp.add(new ItemStack(Material.PUMPKIN_SEEDS,seedcount));
			}
		} else if(mat==Material.REDSTONE_ORE) {
			if(isPickaxe(itemInMainHand) ) {
				tmp.add(new ItemStack(Material.REDSTONE,rand.nextInt(1)+4));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.SNOW) {
			if(isShovel(itemInMainHand)) {
				tmp.add(new ItemStack(Material.SNOW,1));
			} else {
				tmp.add(new ItemStack(Material.SNOWBALL,1));
			}
		} else if(mat==Material.SNOW_BLOCK) {
			if(isShovel(itemInMainHand)) {
				tmp.add(new ItemStack(Material.SNOW_BLOCK,1));
			} else {
				tmp.add(new ItemStack(Material.SNOWBALL,4));
			}
		} else if(mat==Material.STONE) {
			if(isPickaxe(itemInMainHand)) {
				tmp.add(new ItemStack(Material.STONE,1));
			} else {
				return new ItemStack[0];
			}
		} else if(mat==Material.WHEAT) {
			if(isFullyGrown(blockDestroyed)) {
				tmp.add(new ItemStack(Material.WHEAT,1));
				int seedcount = rand.nextInt(4);
				if(seedcount!=0) {
					tmp.add(new ItemStack(Material.WHEAT_SEEDS,seedcount));
				}
			} else {
				tmp.add(new ItemStack(Material.WHEAT_SEEDS,1));
			}
		}
		
		if(tmp.size()>0) {
			return tmp.toArray(new ItemStack[tmp.size()]);
		}
		
		
		// the rest
		
		ItemStack[] result = new ItemStack[1];
		if(silkTouchMap.containsKey(mat)) {
			result[0] = new ItemStack(silkTouchMap.get(mat),1);
		} else {
			result[0] = new ItemStack(mat,1);
			System.out.println("Material "+mat.name()+" not found in Silk Touch Map, return itself");
		}
		return result;
	}
	
	public ItemStack[] getBlockDrop(Block blockDestroyed,ItemStack itemInMainHand) {
		
		Material mat = blockDestroyed.getType();
		
		ArrayList<ItemStack> tmp = new ArrayList<ItemStack>();
		
		if(mat == Material.BEETROOTS) {
			Ageable ageable = (Ageable) blockDestroyed.getBlockData();
			if(ageable.getAge() == ageable.getMaximumAge()) {
				// fully grown
				tmp.add(new ItemStack(Material.BEETROOT,1));
				int seedcount = rand.nextInt(4);
				if(seedcount>0) {
					tmp.add(new ItemStack(Material.BEETROOT_SEEDS,seedcount));
				}
			} else {
				// not fully grown
				tmp.add(new ItemStack(Material.BEETROOT_SEEDS,1));
			}
		} else if(mat == Material.CARROTS) {
			if(isFullyGrown(blockDestroyed)) {
			tmp.add(new ItemStack(Material.CARROT,rand.nextInt(4) + 1));
			} else {
				tmp.add(new ItemStack(Material.CARROT,1));
			}
		} else if(mat==Material.COCOA) {
			tmp.add(new ItemStack(Material.COCOA_BEANS, rand.nextInt(3)+1));
		} else if(mat==Material.BLUE_ICE) {
			return new ItemStack[0];
		} else if(mat==Material.GRAVEL) {
			if(rand.nextInt(10)==0) {
				tmp.add(new ItemStack(Material.FLINT,1));
			} else {
				tmp.add(new ItemStack(Material.GRAVEL,1));
			}
		} else if(mat==Material.WHEAT) {
			if(isFullyGrown(blockDestroyed)) {
				tmp.add(new ItemStack(Material.WHEAT,1));
				int seedcount = rand.nextInt(4);
				if(seedcount!=0) {
					tmp.add(new ItemStack(Material.WHEAT_SEEDS,seedcount));
				}
			} else {
				tmp.add(new ItemStack(Material.WHEAT_SEEDS,1));
			}
		}
		
		if(tmp.size()>0) {
			return tmp.toArray(new ItemStack[tmp.size()]);
		}
		
		
		if(dropMap.containsKey(blockDestroyed.getType())) {
			return dropMap.get(blockDestroyed.getType());
		} else {
		Collection<ItemStack> drops = blockDestroyed.getDrops(itemInMainHand);		
			return drops.toArray(new ItemStack[drops.size()]);
		}
	}

}