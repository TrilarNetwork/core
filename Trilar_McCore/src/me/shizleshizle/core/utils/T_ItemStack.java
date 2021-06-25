package me.shizleshizle.core.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class T_ItemStack {
	public ItemStack is;

	public T_ItemStack(ItemStack i) {
		is = i;
	}

	public T_ItemStack(Material mat) {
		is = new ItemStack(mat);
	}

	public T_ItemStack(Material mat, int amnt) {
		is = new ItemStack(mat, amnt);
	}

	public T_ItemStack(Material mat, int amnt, short d) {
		is = new ItemStack(mat, amnt);
		Damageable meta = (Damageable) is.getItemMeta();
		assert meta != null;
		meta.setDamage(d);
		is.setItemMeta((ItemMeta) meta);
	}

	public ItemStack toItemStack() {
		return is;
	}

	public void setName(String n) {
		ItemMeta im = is.getItemMeta();
		assert im != null;
		im.setDisplayName(n);
		is.setItemMeta(im);
	}

	public void setLore(String... lore) {
		ItemMeta im = is.getItemMeta();
		assert im != null;
		im.setLore(Arrays.asList(lore));
		is.setItemMeta(im);
	}

	public void setLore(List<String> l) {
		ItemMeta im = is.getItemMeta();
		assert im != null;
		im.setLore(l);
		is.setItemMeta(im);
	}

	public void setType(Material mat) {
		is.setType(mat);
	}

	public Material getType() {
		return is.getType();
	}

	public String getName() {
		if (is.getItemMeta() != null && is.getItemMeta().hasDisplayName()) {
			return is.getItemMeta().getDisplayName();
		}
		return null;
	}

	public boolean hasName() {
		return (is.getItemMeta() != null && is.getItemMeta().hasDisplayName());
	}

	public boolean hasLore() {
		return (is.getItemMeta() != null && is.getItemMeta().hasLore());
	}

	// CHECK HOW TO DO IN 1.17
	/*public void addGlow() {
		net.minecraft.server.v1_15_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null) tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		assert tag != null;
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		is = CraftItemStack.asCraftMirror(nmsStack);
	}*/
}
