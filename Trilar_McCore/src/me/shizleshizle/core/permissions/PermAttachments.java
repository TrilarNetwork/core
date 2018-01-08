package me.shizleshizle.core.permissions;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;

public class PermAttachments {
	private static HashMap<User, PermissionAttachment> perms = new HashMap<>();

	public static void addPerms(User p) {
		PermissionAttachment pa = p.addAttachment(Main.p);

		if (Perm.hasPerm(p, PermGroup.LEAD_DEVELOPER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.OWNER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.DEVELOPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.LEAD_DEVELOPER));
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			pa.setPermission(new Permission("minecraft.command.save-all"), true);
			pa.setPermission(new Permission("bukkit.command.plugins"), true);
			pa.setPermission(new Permission("bukkit.command.timings"), true);
			pa.setPermission(new Permission("bukkit.command.version"), true);
			pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
			pa.setPermission(new Permission("worldedit.*"), true);
			pa.setPermission(new Permission("worldguard.*"), true);
			pa.setPermission(new Permission("*"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.DEVELOPER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.OWNER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.DEVELOPER));
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			pa.setPermission(new Permission("minecraft.command.save-all"), true);
			pa.setPermission(new Permission("bukkit.command.plugins"), true);
			pa.setPermission(new Permission("bukkit.command.timings"), true);
			pa.setPermission(new Permission("bukkit.command.version"), true);
			pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
			pa.setPermission(new Permission("worldedit.*"), true);
			pa.setPermission(new Permission("worldguard.*"), true);
			pa.setPermission(new Permission("*"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.OWNER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.OWNER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.DEVELOPER));
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			pa.setPermission(new Permission("minecraft.command.save-all"), true);
			pa.setPermission(new Permission("bukkit.command.plugins"), true);
			pa.setPermission(new Permission("bukkit.command.timings"), true);
			pa.setPermission(new Permission("bukkit.command.version"), true);
			pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
			pa.setPermission(new Permission("worldedit.*"), true);
			pa.setPermission(new Permission("worldguard.*"), true);
			pa.setPermission(new Permission("*"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.MANAGER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			pa.setPermission(new Permission("minecraft.command.save-all"), true);
			pa.setPermission(new Permission("bukkit.command.plugins"), true);
			pa.setPermission(new Permission("bukkit.command.timings"), true);
			pa.setPermission(new Permission("bukkit.command.version"), true);
			pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
			pa.setPermission(new Permission("worldedit.*"), true);
			pa.setPermission(new Permission("worldguard.*"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.ADMIN)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			pa.setPermission(new Permission("minecraft.command.save-all"), true);
			pa.setPermission(new Permission("bukkit.command.plugins"), true);
			pa.setPermission(new Permission("bukkit.command.timings"), true);
			pa.setPermission(new Permission("bukkit.command.version"), true);
			pa.setPermission(new Permission("minecraft.command.setworldspawn"), true);
			pa.setPermission(new Permission("worldedit.*"), true);
			pa.setPermission(new Permission("worldguard.*"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.HELPER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.BUILDER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP4));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.YOUTUBE)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP4));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.VIP4)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP4));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP3));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.VIP3)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP3));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP2));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.VIP2)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP2));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP1));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.VIP1)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP1));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.VIP)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.MEMBER)) {
			for (String pe : PermissionGroup.getPermissions(PermGroup.MEMBER)) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		perms.put(p, pa);
	}

	public static void removePerms(User p) {
		if (perms.containsKey(p)) {
			p.removeAttachment(perms.get(p));
			perms.remove(p);
		}
	}
}
