package me.shizleshizle.core.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

public class CI {

	public static ItemStack createItem(Material mat, int amount, int data, String name, String... lore) {
		ItemStack i;
		if (data == -1) {
			i = new ItemStack(mat, amount);
		} else {
			i = new ItemStack(mat, amount);
			Damageable meta = (Damageable) i.getItemMeta();
			assert meta != null;
			meta.setDamage(data);
			i.setItemMeta((ItemMeta) meta);
		}
		ItemMeta im = i.getItemMeta();
		assert im != null;
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack getColorArmor(Material m, Color c) {
		ItemStack i = new ItemStack(m, 1);
		LeatherArmorMeta lm = (LeatherArmorMeta) i.getItemMeta();
		assert lm != null;
		lm.setColor(c);
		i.setItemMeta(lm);
		return i;
	}
}
