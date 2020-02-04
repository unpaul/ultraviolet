package me.pavl.ultraviolet.permissions;

import java.util.HashMap;
import java.util.UUID;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class Permissions
{
  public Permissions() {}
  
  static Main plugin = (Main)Main.getPlugin(Main.class);
  public static HashMap<UUID, PermissionAttachment> clientPermissions = new HashMap<>();
  
  public static void setup(Player player) {
    PermissionAttachment permission = player.addAttachment(plugin);
    clientPermissions.put(player.getUniqueId(), permission);
    setter(player.getUniqueId());
  }
  
  public static void setter(java.util.UUID uuid) {
    PermissionAttachment attachment = (PermissionAttachment)clientPermissions.get(uuid);
    for (String perm : plugin.getConfig().getStringList("Groups.default.permissions")) {
      attachment.setPermission(perm, true);
    }
    for (String rank : plugin.getConfig().getConfigurationSection("Groups").getKeys(false)) {
      if (PermissionManager.checkUserInGroup(uuid, rank)) {
        for (String perm : plugin.getConfig().getStringList("Groups." + rank + ".permissions")) {
          attachment.setPermission(perm, true);
        }
      }
    }
  }
}
