package me.shizleshizle.core.permissions;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;

public class PermAttachments {
	private static HashMap<User, PermissionAttachment> perms = new HashMap<>();

	public static void addPerms(User p) {
		PermissionAttachment pa = p.addAttachment(Main.p);

		if (Perm.hasPerm(p, PermGroup.LEAD_DEVELOPER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.OWNER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.DEVELOPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.LEAD_DEVELOPER));
			perms.addAll(p.getPermissions());
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
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.OWNER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.DEVELOPER));
			perms.addAll(p.getPermissions());
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
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.OWNER));
			perms.addAll(p.getPermissions());
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
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MANAGER));
			perms.addAll(p.getPermissions());
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
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.ADMIN));
			perms.addAll(p.getPermissions());
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
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MODERATOR));
			perms.addAll(p.getPermissions());
			pa.setPermission(new Permission("bukkit.command.tps"), true);
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.HELPER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HELPER));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.TRIAL_HELPER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_HELPER));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.HEAD_BUILDER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.HEAD_BUILDER));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.BUILDER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.BUILDER));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.TRIAL_BUILDER)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.TRIAL_BUILDER));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.YOUTUBE)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.YOUTUBE));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.VIP)) {
			ArrayList<String> perms = new ArrayList<>();
			perms.addAll(PermissionGroup.getPermissions(PermGroup.MEMBER));
			perms.addAll(PermissionGroup.getPermissions(PermGroup.VIP));
			perms.addAll(p.getPermissions());
			for (String pe : perms) {
				pa.setPermission(new Permission(pe), true);
			}
		}
		if (Perm.hasPerm(p, PermGroup.MEMBER)) {
			for (String pe : PermissionGroup.getPermissions(PermGroup.MEMBER)) {
				pa.setPermission(new Permission(pe), true);
			}
			for (String pm : p.getPermissions()) {
				pa.setPermission(new Permission(pm), true);
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
