package me.shizleshizle.core.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class DisableAI {

	public static void disableAI(Entity entity) {
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);
	}
}