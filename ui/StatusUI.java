package me.pavl.ultraviolet.ui;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StatusUI
{
  public static Inventory inv;
  public static String inventory_name;
  public static int inv_rows = 54;
  public static Main plugin = Main.getPlugin(Main.class);
  
  public static void initialize() {
	inventory_name = Utils.chat("&6&lstatus&d&lUV");
    inv = Bukkit.createInventory(null, inv_rows);
  }
  
  @SuppressWarnings("deprecation")
public static Inventory GUI(Player s, String p) {
    String[] playerTag = PermissionManager.getUserTags(Bukkit.getOfflinePlayer(p).getUniqueId());
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p);
    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
    Inventory inv = Bukkit.createInventory(null, 54);
    Utils.createItemHead(inv, 5, p, new String[] { "&b&l[&6&lstatus&d&lUV&b&l] &cMENU" });
    Utils.createItem(inv, 138, 1, 1, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 2, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 3, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 4, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 6, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 7, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 8, "==", new String[0]);
    Utils.createItem(inv, 138, 1, 9, "==", new String[0]);
    Utils.createItem(inv, 102, 1, 32, "&a&lPlayer & Premium Ranks", new String[] { "&c[Only Use In Rare Situations]" });
    Utils.createItemByte(inv, 160, 14, 1, 14, "&c&lStaff Ranks", new String[] { "&c[Use for Administration Purposes]" });
    Utils.createItem(inv, 340, 1, 46, "&aAccess " + Bukkit.getOfflinePlayer(p).getName() + "'s Punishment History", new String[] { "&cUse for: ", "&c- Punishment Decisions", "&c- Staffing Process", "&c- Abuse Investigation" });
    if (playerTag.length == 0) {
      Utils.createItem(inv, 323, 1, 50, "&cCurrent Rank", "&7[Member]");
    }
    if (playerTag.length == 1) {
	    Utils.createItem(inv, 323, 1, 50, "&c&lClient Status", playerTag[0]);
    }
    else if (playerTag.length == 2){
	    Utils.createItem(inv, 323, 1, 50, "&c&lClient Status", playerTag[0], playerTag[1]);
    }
    else if (playerTag.length == 3) {
	    Utils.createItem(inv, 323, 1, 50, "&c&lClient Status", playerTag[0], playerTag[1], playerTag[2]);
    }
    if (s.hasPermission("uv.rank.owner")) {
      Utils.createItemByte(inv, 95, 9, 1, 19, "&f[&3&lHELPER&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 1, 1, 21, "&f[&6&lMODERATOR&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 4, 1, 23, "&f[&e&lSR MODERATOR&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 14, 1, 25, "&f[&c&lADMINISTRATOR&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 15, 1, 27, "&4&l[OWNER]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 35, 8, 1, 38, "&7[Member]", new String[0]);
      Utils.createItemByte(inv, 35, 5, 1, 40, "&a[Trusted]", new String[0]);
      Utils.createItemByte(inv, 35, 4, 1, 42, "&b[VIP]", new String[0]);
      Utils.createItemByte(inv, 35, 11, 1, 44, "&1[Elite]", new String[0]);
      Utils.createItemByte(inv, 50, 1, 1, 48, "&9&l[STAFF REPORTS]", "&c[Use for Staff Management Clients]", "&e[Allows Clients to add [SR] to Punish Reasons]");
      Utils.createItemByte(inv, 76, 1, 1, 52, "&9&l[FORUM REPORTS]", "&c[Use for Forum Clients]", "&e[Allows Clients to add [FR] to Punish Reasons]");
    }
    else if (s.hasPermission("uv.rank.admin")) {
      Utils.createItemByte(inv, 95, 9, 1, 20, "&f[&3&lHELPER&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 1, 1, 22, "&f[&6&lMODERATOR&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 4, 1, 24, "&f[&e&lSR MODERATOR&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 95, 14, 1, 26, "&f[&c&lADMINISTRATOR&f]", new String[] { "&c[Use for Administration Purposes]" });
      Utils.createItemByte(inv, 35, 8, 1, 38, "&7[Member]");
      Utils.createItemByte(inv, 35, 5, 1, 40, "&a[Trusted]", new String[0]);
      Utils.createItemByte(inv, 35, 4, 1, 42, "&b[VIP]", new String[0]);
      Utils.createItemByte(inv, 35, 11, 1, 44, "&1[Elite]", new String[0]);
      Utils.createItemByte(inv, 50, 1, 1, 48, "&9&l[STAFF REPORTS]", "&c[Use for Staff Management Clients]", "&e[Allows Clients to add [SR] to Punish Reasons]");
      Utils.createItemByte(inv, 76, 1, 1, 52, "&9&l[FORUM REPORTS]", "&c[Use for Forum Clients]", "&e[Allows Clients to add [FR] to Punish Reasons]");
    }
    else if (s.hasPermission("uv.rank.srmod")) {
      Utils.createItemByte(inv, 95, 9, 1, 21, "&f[&3&lHELPER&f]", "&c[Use for Administration Purposes]");
      Utils.createItemByte(inv, 95, 1, 1, 25, "&f[&6&lMODERATOR&f]", "&c[Use for Administration Purposes]");
      Utils.createItemByte(inv, 35, 8, 1, 41, "&7[Member]", "&c[Removes All Ranks    ]");
      Utils.createItemByte(inv, 50, 1, 1, 48, "&9&l[STAFF REPORTS]", "&c[Use for Staff Management Clients]", "&e[Allows Clients to add [SR] to Punish Reasons]");
      Utils.createItemByte(inv, 76, 1, 1, 52, "&9&l[FORUM REPORTS]", "&c[Use for Forum Clients]", "&e[Allows Clients to add [FR] to Punish Reasons]");
    }
    String userStatus = PermissionManager.getUserStatus(offlinePlayer.getUniqueId());
    String userAdvisor = PermissionManager.getAdvisor(offlinePlayer.getUniqueId());
    if ((userStatus.startsWith("HELPER")) || (userStatus.startsWith("MODERATOR") || userStatus.startsWith("SENIOR_MODERATOR"))) {
    	if (userAdvisor == "" && (userStatus.startsWith("HELPER") || userStatus.startsWith("MODERATOR"))) {
    		if (s.hasPermission("uv.rank.srmod")) {
        	    Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&e&oUnassigned", "&f[&6&lSR.MOD&c&l+&f]&9: Click to Claim as &cAdvisee");
    		}
    		else {
        	    Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&e&oUnassigned");
    		}
    	}
    	else if (userAdvisor == "" && userStatus.startsWith("SENIOR_MODERATOR")) {
    		if (s.hasPermission("uv.rank.admin")) {
        	    Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&e&oUnassigned", "&f[&c&lADMIN+&f]&9: Click to Claim as &cAdvisee");
    		}
    		else {
        	    Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&e&oUnassigned");
    		}
    	}
    	else if (userAdvisor.startsWith(s.getName().toString())) {
    	    Utils.createEnchantedItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&4&l" + s.getName(), "&cClick to Open Advisor Menu");
    	}
    	else {
    		if (userStatus.startsWith("SENIOR_MODERATOR")) {
        		if (s.hasPermission("uv.rank.admin")) {
            	    Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName(), "&f[&c&lADMIN+&f]&9: Click to Claim as &cAdvisee");	
        		}
        		else {
        			Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName());
        		}
    		}
    		else {
        		if (s.hasPermission("uv.rank.srmod")) {
            	    Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName(), "&f[&6&lSR.MOD&c&l+&f]&9: Click to Claim as &cAdvisee");	
        		}
        		else {
        			Utils.createItem(inv, 390, 1, 54, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName());
        		}
    		}

    	}
    }
    toReturn.setContents(inv.getContents());
    inv.clear();
    return toReturn;
  }
}
