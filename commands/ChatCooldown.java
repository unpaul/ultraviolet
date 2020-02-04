package me.pavl.ultraviolet.commands;

import me.pavl.ultraviolet.Main;

public class ChatCooldown {
  public static void addChatCooldown(int seconds, String issuer) { 
	Main plugin = (Main)Main.getPlugin(Main.class);
    plugin.getConfig().set("Chat.cooldown.active", Boolean.valueOf(true));
    plugin.getConfig().set("Chat.cooldown.issuer", issuer);
    plugin.getConfig().set("Chat.cooldown.time", Integer.valueOf(seconds));
    plugin.saveConfig();
  }
  
  public static void silenceChat(String issuer) { 
	Main plugin = (Main)Main.getPlugin(Main.class);
    plugin.getConfig().set("Chat.cooldown.active", Boolean.valueOf(true));
    plugin.getConfig().set("Chat.cooldown.issuer", issuer);
    plugin.getConfig().set("Chat.cooldown.time", Integer.valueOf(-1));
    plugin.saveConfig();
  }
  
  public static void removeChatCooldown() { 
	Main plugin = (Main)Main.getPlugin(Main.class);
    plugin.getConfig().set("Chat.cooldown.active", Boolean.valueOf(false));
    plugin.getConfig().set("Chat.cooldown.issuer", "null");
    plugin.getConfig().set("Chat.cooldown.time", Integer.valueOf(0));
    plugin.saveConfig();
  }
}
