package me.shizleshizle.core.utils;

import java.util.Random;

public class Numbers {
	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			try {
				Double.parseDouble(str);
				return true;
			} catch (NumberFormatException e2) {
				return false;
			}
		}
	}

	public static int getInt(String str) {
		int i;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			i = 0;
		}
		return i;
	}

	public static double getDouble(String str) {
		double d;
		try {
			d = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			d = 0;
		}
		return d;
	}

	public static int getRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
}
