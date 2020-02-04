package me.pavl.ultraviolet.ui;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.utils.Utils;

public class MacroUI {
	  public static Inventory inv;
	  public static String inventory_name;
	  
	  public static int inv_rows = 54;
	  public static Main plugin = (Main)Main.getPlugin(Main.class);
	  
	  public static void initialize() { inventory_name = Utils.chat("&5&lmacro&d&lUV");
	    inv = Bukkit.createInventory(null, inv_rows);
	  }
	  
	  @SuppressWarnings("deprecation")
	public static Inventory GUI(Player s, String p)
	  {
	    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
	    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p);
	    Inventory inv = Bukkit.createInventory(null, 54);
	    Utils.createItemHead(inv, 5, offlinePlayer, "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&b&l[&d&lUV&b&l] &d&lMACRO SHORTCUTS", "&9[Click to message " + offlinePlayer.getName() + "]");
	    Utils.createItem(inv, 385, 1, 1, " ");
	    Utils.createItem(inv, 385, 1, 2, " ");
	    Utils.createItem(inv, 385, 1, 3, " ");
	    Utils.createItem(inv, 385, 1, 4, " ");
	    Utils.createItem(inv, 385, 1, 6, " ");
	    Utils.createItem(inv, 385, 1, 7, " ");
	    Utils.createItem(inv, 385, 1, 8, " ");
	    Utils.createItem(inv, 385, 1, 9, " ");
	    Utils.createItem(inv, 332, 1, 10, "&9&lLink to Shop", "&2&o'You can find the shop at shop.unpaul.com'");
	    Utils.createItem(inv, 332, 1, 11, "&9&lLink to Forums", "&2&o'You can find the shop at forums.unpaul.com'");
	    toReturn.setContents(inv.getContents());
	    return toReturn;
}
}
