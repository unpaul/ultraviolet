package me.pavl.ultraviolet.ui;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UVServerUI
{
  public static Inventory inv;
  public static String inventory_name;
  public static int inv_rows = 54;
  public static Main plugin = Main.getPlugin(Main.class);
  public static boolean slowedChat;
  public static String chatSlowIssuer;
  public static int chatSlowTime;
  
  public UVServerUI() {}
  
  public static void initialize() { 
	inventory_name = Utils.chat("&4&lserver&d&lUV");
    inv = Bukkit.createInventory(null, inv_rows);
  }
  
  public static Inventory GUI(Player s) { slowedChat = plugin.getConfig().getBoolean("Chat.cooldown.active");
    @SuppressWarnings("deprecation")
	OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(s.getName());
	inventory_name = Utils.chat("&4&lserver&d&lUV");
    chatSlowIssuer = plugin.getConfig().getString("Chat.cooldown.issuer");
    chatSlowTime = plugin.getConfig().getInt("Chat.cooldown.time");
    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
    Inventory inv = Bukkit.createInventory(null, 54);
    String lastIP = plugin.getConfig().getString(offlinePlayer.getUniqueId() + ".lastIP");
    if (s.hasPermission("uv.selfadmin")) {
      Utils.createItemHead(inv, 5, s, "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE", " ", "&cIP Address: &b" + lastIP);
      // chat management
      if (!slowedChat) {
          Utils.createItem(inv, 339, 1, 23, "&b&lChat Status", "&a&lNORMAL // UNSLOWED", " ", "&e&lOptions: ", "&c- Click to &4SILENCE &cChat", "&c- Use &a/uv chat <seconds> &cto Slow Chat");

        }
      else if (chatSlowTime == -1) {
          Utils.createEnchantedItem(inv, 339, 1, 23, "&b&lChat Status","&c&lDISABLED", " ", "&e&lOptions: ", "&c- Click to &aENABLE &cChat", "&c- Use &a/uv chat <seconds> &cto Slow Chat");
      }
      else {
          Utils.createEnchantedItem(inv, 339, 1, 23, "&b&lChat Status", "&c&lSLOWED: &3" + String.valueOf(chatSlowTime) + " Second &aCOOLDOWN", " ", "&e&lOptions: ", "&c- Click to &aREMOVE Cooldown", "&c- Use &a/uv chat silence &cto &4SILENCE&c Chat");
      }
      // weather management
      long currentTime = s.getWorld().getTime();
      if (currentTime == 1200) {
          Utils.createEnchantedItem(inv, 423, 1, 31, "&e&lWeather","&cCurrent State:", "&6Daytime &c(Permanent)", " ", "&9Click to &aNormalize &eWeather");
          Utils.createItem(inv, 424, 1, 33, "&e&lWeather","&cCurrent State:", "&6Daytime &c(Permanent)", " ", "&aClick for &9Night Time &c(Permanent)");
      }
      else if (currentTime == 18000) {
          Utils.createEnchantedItem(inv, 424, 1, 33, "&e&lWeather","&cCurrent State:", "&9Night Time &c(Permanent)", " ", "&9Click to &aNormalize &eWeather");
          Utils.createItem(inv, 423, 1, 31, "&e&lWeather","&cCurrent State:", "&9Night Time &c(Permanent)", " ", "&aClick for &6Daytime &c(Permanent)");
      }
      else {
          Utils.createItem(inv, 423, 1, 31, "&e&lWeather","&cCurrent State: &a&lNormal", " ", "&aClick for &6Daytime &c(Permanent)");    	  
    	  Utils.createItem(inv, 424, 1, 33, "&e&lWeather","&cCurrent State: &a&lNormal", " ", "&aClick for &9Night Time &c(Permanent)");
      }
      // default game mode for server
      @SuppressWarnings("deprecation")
	  int defaultGM = Bukkit.getServer().getDefaultGameMode().getValue();
      if (defaultGM == 1) {
        Utils.createItem(inv, 32, 1, 41, "&c&lDefault Gamemode", "&cCurrent: &a&lCreative", " ", "&aClick to Update to &4&lSurvival");
      }
      if (defaultGM == 0) {
          Utils.createItem(inv, 32, 1, 41, "&c&lDefault Gamemode", "&cCurrent: &4&lSurvival", " ", "&aClick to Update to &a&lCreative");
        }
	  Utils.createItem(inv, 145, 1, 54, "&9Back to &b&l[&a&lmy&d&lUV&b&l]");
    }
    else {
      Utils.createItemHead(inv, 5, s, "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE");
    }
    Utils.createItem(inv, 145, 1, 1, "==");
    Utils.createItem(inv, 145, 1, 2, "==");
    Utils.createItem(inv, 145, 1, 3, "==");
    Utils.createItem(inv, 145, 1, 4, "==");
    Utils.createItem(inv, 145, 1, 6, "==");
    Utils.createItem(inv, 145, 1, 7, "==");
    Utils.createItem(inv, 145, 1, 8, "==");
    Utils.createItem(inv, 145, 1, 9, "==");
    toReturn.setContents(inv.getContents());
    return toReturn;
  }
 
}
