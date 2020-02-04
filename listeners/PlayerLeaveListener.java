package me.pavl.ultraviolet.listeners;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.permissions.Permissions;
import me.pavl.ultraviolet.utils.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
  private Main plugin;
  
  public PlayerLeaveListener(Main plugin) {
    this.plugin = plugin;
    
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    Player sender = event.getPlayer();
    UUID senderUUID = sender.getUniqueId();
    Permissions.clientPermissions.remove(senderUUID);
    plugin.getConfig().set(senderUUID + ".lastSeen", Utils.getCurrentDate());
    plugin.saveConfig();
  }
}
