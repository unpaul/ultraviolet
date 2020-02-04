package me.pavl.ultraviolet.listeners;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;

public class PlayerLoginListener implements Listener
{
	  @SuppressWarnings("unused")
	private Main plugin;
	  
	  public PlayerLoginListener(Main plugin)
	  {
	    this.plugin = plugin;
	    
	    Bukkit.getPluginManager().registerEvents(this, plugin);
	  }
	  
	  @EventHandler
	  public void onJoin(PlayerLoginEvent event) {
		Player sender = event.getPlayer();
	    if (event.getResult() == Result.KICK_BANNED) {
	    	event.setKickMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] \n &9You are banned for &c" + PunishManager.getCurrentBanReason(sender.getUniqueId()) + "&9 until &c" + PunishManager.getCurrentBanEndDate(sender.getUniqueId())));
	    }
	    else {
	    	return;
	    }
	  }
}
	  
