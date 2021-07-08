package me.shizleshizle.core.permissions;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PermUser {
    private static HashMap<UUID, PermissionAttachment> permz = new HashMap<>();

    public static void setup() {
        PreparedStatement ps = null;
        try {
            ps = Main.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS user_permissions (player varchar(32), permission varchar(128))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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