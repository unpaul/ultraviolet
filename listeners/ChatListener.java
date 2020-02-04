package me.pavl.ultraviolet.listeners;

import java.util.Date;
import java.util.HashMap;
import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
  public static Main plugin = (Main)Main.getPlugin(Main.class);
  static boolean slowedChat;
  static String chatSlowIssuer;
  static int chatSlowTime;
  HashMap<String, Date> chatAllowanceDate = new HashMap<>();
  
  public ChatListener(Main plugin) { slowedChat = plugin.getConfig().getBoolean("Chat.cooldown.active");
    chatSlowIssuer = plugin.getConfig().getString("Chat.cooldown.issuer");
    chatSlowTime = plugin.getConfig().getInt("Chat.cooldown.time");
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  public static void reload() { slowedChat = plugin.getConfig().getBoolean("Chat.cooldown.active");
    chatSlowIssuer = plugin.getConfig().getString("Chat.cooldown.issuer");
    chatSlowTime = plugin.getConfig().getInt("Chat.cooldown.time");
  }
  
  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    Player sender = event.getPlayer();
    if (PunishManager.isMuted(sender.getUniqueId())) {
      event.setCancelled(true);
      sender.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You are currently &cmuted &9for &c" + PunishManager.getCurrentMuteReason(sender.getUniqueId()) + " &9until &d" + PunishManager.getCurrentMuteEndDate(sender.getUniqueId())));
    }
    else {
      String message = event.getMessage();
      event.setCancelled(true);
      if (!slowedChat) {
        chatAllowanceDate.clear();
        for (Player s : Bukkit.getServer().getOnlinePlayers()) {
          s.sendMessage(Utils.chat(PermissionManager.getUserTag(sender.getUniqueId()) + " " + sender.getName() + "&a: " + message));
        }
        
      }
      else if (!chatAllowanceDate.containsKey(event.getPlayer().getUniqueId().toString())) {
        if (chatSlowTime == -1) {
          event.getPlayer().sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Chat is currently &cdisabled."));
        }
        else {
          for (Player s : Bukkit.getServer().getOnlinePlayers()) {
            s.sendMessage(Utils.chat(PermissionManager.getUserTag(sender.getUniqueId()) + " " + sender.getName() + "&a: " + message));
            chatAllowanceDate.put(event.getPlayer().getUniqueId().toString(), Utils.getLaterSeconds(chatSlowTime));
          }
          
        }
      }
      else if (chatSlowTime == -1) {
        event.getPlayer().sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Chat is currently &cdisabled."));
      }
      else if (((Date)chatAllowanceDate.get(event.getPlayer().getUniqueId().toString())).before(Utils.getCurrentDateDate())) {
        for (Player s : Bukkit.getServer().getOnlinePlayers()) {
          s.sendMessage(Utils.chat(PermissionManager.getUserTag(sender.getUniqueId()) + " " + sender.getName() + "&a: " + message));
          chatAllowanceDate.put(event.getPlayer().getUniqueId().toString(), Utils.getLaterSeconds(chatSlowTime));
        }
      }
      else {
        event.getPlayer().sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Chat Slow &b» You must wait &c" + chatSlowTime + " seconds &9between messages."));
      }
    }
  }
}
