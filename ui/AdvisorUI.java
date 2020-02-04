package me.pavl.ultraviolet.ui;


import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.utils.Utils;

public class AdvisorUI {
	  public static Inventory inv;
	  public static String inventory_name;
	  public static int inv_rows = 54;
	  public static Main plugin = Main.getPlugin(Main.class);
	  
	  public static void initialize() {
		inventory_name = Utils.chat("&3&ladvisor&d&lUV");
	    inv = Bukkit.createInventory(null, inv_rows);
	  }
	  
	  @SuppressWarnings("deprecation")
	public static Inventory GUI(Player s, String p) {
		inventory_name = Utils.chat("&3&ladvisor&d&lUV");
	    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
	    Inventory inv = Bukkit.createInventory(s, 54);

	    Utils.createItem(inv, 390, 1, 1, " ");
	    Utils.createItem(inv, 390, 1, 2, " ");
	    Utils.createItem(inv, 390, 1, 3, " ");
	    Utils.createItem(inv, 390, 1, 4, " ");
	    Utils.createItem(inv, 390, 1, 5, "&b&l[&3&ladvisor&d&lUV&b&l] &cMENU");
	    Utils.createItem(inv, 390, 1, 6, " ");
	    Utils.createItem(inv, 390, 1, 7, " ");
	    Utils.createItem(inv, 390, 1, 8, " ");
	    Utils.createItem(inv, 390, 1, 9, " ");
	    if (p == "main") {
	    	String[] clientAdvisees = PermissionManager.getUserAdvisees(s.getName());
	    	int spot = 10;
	    	
	    	for (String i : clientAdvisees) {
	    		UUID adviseeString = UUID.fromString(i);
	    	    Utils.createItem(inv, 38, 1, spot, "&c" + Bukkit.getOfflinePlayer(adviseeString).getName(), "&4&lClick to Manage.");
	    	    spot += 1;
	    	}
	    }
	    else {
	    	Utils.createItemHead(inv, 5, p, "&b&l[&3&ladvisor&d&lUV&b&l] &cMENU");
		    String[] playerTag = PermissionManager.getUserTags(Bukkit.getOfflinePlayer(p).getUniqueId());
		    if (playerTag.length == 1) {
			    Utils.createItem(inv, 138, 1, 54, "&c&lClient Status", playerTag[0], "Click to Modify");
		    }
		    else if (playerTag.length == 2){
			    Utils.createItem(inv, 138, 1, 54, "&c&lClient Status", playerTag[0], playerTag[1], "Click to Modify");
		    }
		    else if (playerTag.length == 3) {
			    Utils.createItem(inv, 138, 1, 54, "&c&lClient Status", playerTag[0], playerTag[1], playerTag[2], "Click to Modify");
		    }
		    else if (playerTag.length == 0) {
			    Utils.createItem(inv, 138, 1, 54, "&c&lClient Status", "&7[Member]", "Click to Modify");
		    }
	    }

	    toReturn.setContents(inv.getContents());
	    inv.clear();
	    return toReturn;
    }
}
