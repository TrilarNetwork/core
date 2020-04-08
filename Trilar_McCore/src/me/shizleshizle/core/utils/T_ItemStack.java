package me.shizleshizle.core.utils;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
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
		is = new ItemStack(mat, amnt, d);
	}

	public ItemStack toItemStack() {
		return is;
	}

	public void setName(String n) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(n);
		is.setItemMeta(im);
	}

	public void setLore(String... lore) {
		ItemMeta im = is.getItemMeta();
		im.setLore(Arrays.asList(lore));
		is.setItemMeta(im);
	}

	public void setLore(List<String> l) {
		ItemMeta im = is.getItemMeta();
		im.setLore(l);
		is.setItemMeta(im);
	}

	public void setType(Material mat) {
		is.setType(mat);
	}

	public Material getType() {
		return is.getType();
	}

	public void setDurability(byte d) {
		is.setDurability(d);
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

	public void addGlow() {
		net.minecraft.server.v1_15_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(is);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null) tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		is = CraftItemStack.asCraftMirror(nmsStack);
	}
}
