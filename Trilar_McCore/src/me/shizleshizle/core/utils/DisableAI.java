package me.shizleshizle.core.utils;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class DisableAI {

	public static void disableAI(Entity entity) {
		net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		if (entity instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entity;
			living.setAI(false);
		}
		/*net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle(); b0ab03c7d3346606169c505e062297cf1f3137ab
		NBTTagCompound tag = nmsEntity.getNBTTag();
		
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);*/
	}
}
