package me.pavl.ultraviolet.listeners;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PrefManager;
import me.pavl.ultraviolet.permissions.Permissions;
import me.pavl.ultraviolet.utils.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerJoinListener implements Listener
{
  private Main plugin;
  
  public PlayerJoinListener(Main plugin)
  {
    this.plugin = plugin;
    
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player sender = event.getPlayer();
    event.setJoinMessage(null);
    // set up prefix
    UUID senderUUID = sender.getUniqueId();
    Permissions.setup(sender);
    PrefManager.addUser(senderUUID);
    plugin.getConfig().set(senderUUID + ".lastSeen", Utils.getCurrentDate());
    plugin.getConfig().set(senderUUID + ".lastIP", sender.getAddress().getHostName());
    plugin.saveConfig();
  }
  // forcefield mechanism
  @EventHandler
  public void onMove(PlayerMoveEvent e) {
      for (Player s : Bukkit.getServer().getOnlinePlayers()) {
    	  // player walks up to boss
    	  if ((s != e.getPlayer()) && !(e.getPlayer().hasPermission("uv.rank.admin"))) {
    		  int bossForcefieldValue = Integer.valueOf(PrefManager.getForcefield(s.getUniqueId()));
    		  if (bossForcefieldValue != -1) {
            	  double distance = e.getPlayer().getLocation().distance(s.getLocation());
            	  if (distance < bossForcefieldValue) {
            		 e.getPlayer().getLocation().setY(1009);
            		 e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(-1).normalize());
            		 e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CHICKEN_EGG_POP, 5, 1);
            		 distance = e.getPlayer().getLocation().distance(s.getLocation());
            		 break;
    		  }
    	  }
    	  }
    	  // boss walks up to player
    	  if ((s != e.getPlayer()) && !(s.hasPermission("uv.rank.admin"))) {
    		  int bouncerForcefieldValue = Integer.valueOf(PrefManager.getForcefield(e.getPlayer().getUniqueId()));
    		  if (bouncerForcefieldValue != -1) {
            	  double distance = e.getPlayer().getLocation().distance(s.getLocation());
            	  if (distance < bouncerForcefieldValue) {
            		 s.getLocation().setY(1009);
            		 s.setVelocity(e.getPlayer().getLocation().getDirection().multiply(-1).normalize());
            		 s.playSound(s.getLocation(), Sound.CHICKEN_EGG_POP, 5, 1);
            		 distance = e.getPlayer().getLocation().distance(s.getLocation());
            		 break;
    		  }
    	  }
    	  }
      }
  }
}
