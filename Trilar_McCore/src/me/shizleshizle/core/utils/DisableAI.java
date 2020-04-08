package me.shizleshizle.core.utils;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class DisableAI {

	public static void disableAI(Entity entity) {
		if (entity instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entity;
			living.setAI(false);
		}
		/*net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);*/
	}
}
