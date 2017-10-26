package me.shizleshizle.core.utils;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CI {

	public static ItemStack createItem(Material mat, int amount, int data, String name, String... lore) {
		ItemStack i;
		if (data == -1) {
			i = new ItemStack(mat, amount);
		} else {
			i = new ItemStack(mat, amount, (byte) data);
		}
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		i.setItemMeta(im);
		return i;
	}
}
