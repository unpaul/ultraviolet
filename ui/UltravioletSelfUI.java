package me.pavl.ultraviolet.ui;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UltravioletSelfUI
{
  public static Inventory inv;
  public static String inventory_name;
  
  public UltravioletSelfUI() {}
  
  public static int inv_rows = 54;
  public static Main plugin = (Main)Main.getPlugin(Main.class);
  
  public static void initialize() { 
	inventory_name = Utils.chat("&a&lmy&e&l&d&lUV");
    inv = Bukkit.createInventory(null, inv_rows);
  }
  
  @SuppressWarnings("deprecation")
public static Inventory GUI(Player s)
  {
    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(s.getName());
    plugin.getConfig().set(s.getUniqueId() + ".lastPunishee", s.getName());
    plugin.saveConfig();
    String lastIP = plugin.getConfig().getString(offlinePlayer.getUniqueId() + ".lastIP");
    if (s.hasPermission("uv.selfadmin")) {
      toReturn = Bukkit.createInventory(s, inv_rows, Utils.chat("&a&lmy&d&lUV&4&l+"));
      Utils.createItemHead(inv, 5, s, "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE", " ", "&cIP Address: &b" + lastIP);
    }
    else {
      toReturn = Bukkit.createInventory(s, inv_rows, Utils.chat("&a&lmy&d&lUV"));
      Utils.createItemHead(inv, 5, s, "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE");
    }
    Utils.createItem(inv, 399, 1, 1, "==");
    Utils.createItem(inv, 399, 1, 2, "==");
    Utils.createItem(inv, 399, 1, 3, "==");
    Utils.createItem(inv, 399, 1, 4, "==");
    Utils.createItem(inv, 399, 1, 6, "==");
    Utils.createItem(inv, 399, 1, 7, "==");
    Utils.createItem(inv, 399, 1, 8, "==");
    Utils.createItem(inv, 399, 1, 9, "==");
    Utils.createItem(inv, 340, 1, 46, "&aAccess &cYour &aPunishment History.", new String[0]);
    if (s.hasPermission("uv.rank.admin")) {
        Utils.createItem(inv, 145, 1, 54, "&4&lServer Settings", "&cProceed With Caution");
        Utils.createItem(inv, 326, 1, 31, "&4&lVanish From Players");
        Utils.createItem(inv, 327, 1, 33, "&a&lUnvanish From Players");
        // game mode manager
        if (s.getGameMode().getValue() == 1) {
            Utils.createItem(inv, 384, 1, 27, "&c&lClient Gamemode", "&a&lCreative", " ", "&9Click to Update: &4&lSurvival");
        }
        else if (s.getGameMode().getValue() == 0) {
            Utils.createItem(inv, 384, 1, 27, "&c&lClient Gamemode", "&4&lSurvival", " ", "&9Click to Update: &a&lCreative");
        }
        Utils.createItem(inv, 122, 1, 19, "&c&lClear Your Inventory");
        Utils.createItem(inv, 297, 1, 20, "&a&lHealth + Hunger", "&cHealth: &d" + s.getHealth(), "&bHunger: &d" + s.getFoodLevel(), " ", "&9Click to &aHeal&9.");
        Utils.createItem(inv, 91, 1, 23, "&b&lProcess Inappropriate Name Reports");
    }
    if (s.hasPermission("uv.rank.srmod")) {
        Utils.createItem(inv, 390, 1, 41, "&c&lManage Your Advisees");
    }
    toReturn.setContents(inv.getContents());
    inv.clear();
    return toReturn;
  }
}
