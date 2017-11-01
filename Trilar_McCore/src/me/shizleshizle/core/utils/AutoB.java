package me.shizleshizle.core.utils;

public class AutoB {
	private static boolean b = true;
	
	public static void enable() {
		b = true;
	}
	
	public static void disable() {
		b = false;
	}
	
	public static boolean isBroadcasting() {
		return b;
	}
	
	
}
