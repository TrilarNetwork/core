package me.shizleshizle.core.permissions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;

public class PermUser {
	private static HashMap<UUID, PermissionAttachment> permz = new HashMap<>();

	public static void setup() {
		try {
			Main.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS user_permissions (player varchar(32), permission varchar(128))").executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void loginUser(User p) {
		PermissionAttachment pa = p.addAttachment(Main.p);
		ArrayList<String> perms = p.getPermissions();
		for (String pe : perms) {
			pa.setPermission(new Permission(pe), true);
		}
		permz.put(p.getUUID(), pa);
	}

	public static void logoutUser(User p) {
		if (permz.containsKey(p.getUUID())) {
			p.removeAttachment(permz.get(p.getUUID()));
			permz.remove(p.getUUID());
		}
	}
}