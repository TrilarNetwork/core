package me.shizleshizle.core.utils;

public class DisableAI {

	// CHECK FOR 1.17 VERSION ON HOW TO DISABLE AI
	/*public static void disableAI(Entity entity) {
		net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		if (entity instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entity;
			living.setAI(false);
		}
		net.minecraft.server.v1_15_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle(); b0ab03c7d3346606169c505e062297cf1f3137ab
		NBTTagCompound tag = nmsEntity.getNBTTag();
		
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);
	}*/
}
