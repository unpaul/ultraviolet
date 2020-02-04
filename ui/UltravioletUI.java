package me.pavl.ultraviolet.ui;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UltravioletUI
{
  public static Inventory inv;
  public static String inventory_name;
  public static int inv_rows = 54;
  public static Main plugin = Main.getPlugin(Main.class);
  
  public static void initialize() { 
	inventory_name = Utils.chat("&d&lUV");
    inv = Bukkit.createInventory(null, inv_rows);
  }
  
  @SuppressWarnings("deprecation")
public static Inventory GUI(Player s, String p)
  {
    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
    Player player = Bukkit.getPlayer(p);
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p);
    plugin.getConfig().set(s.getUniqueId() + ".lastPunishee", p);
    plugin.saveConfig();
    String lastIP = plugin.getConfig().getString(offlinePlayer.getUniqueId() + ".lastIP");
    if (s.hasPermission("uv.othersadmin")) {
      toReturn = Bukkit.createInventory(s, inv_rows, Utils.chat("&a&lclient&d&lUV&4&l+"));
    }
    else {
      toReturn = Bukkit.createInventory(s, inv_rows, Utils.chat("&a&lclient&d&lUV"));
    }
    if (player == null) {
      if (offlinePlayer.hasPlayedBefore()) {
        if (s.hasPermission("uv.othersAdmin")) {
          Utils.createItemHead(inv, 5, offlinePlayer);
          Utils.createItem(inv, 339, 1, 26, "&e&lPlayer Data", "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &cOFFLINE", "&bLast Played: &d" + plugin.getConfig().getString(new StringBuilder().append(offlinePlayer.getUniqueId()).append(".lastSeen").toString()), " ", "&cLast IP Address: &b" + lastIP);
          Utils.createItem(inv, 101, 1, 54, "&a&lOpen &c&lpunish&d&lUV&c&l+ &a&lMenu", "&c- WARNING: No Reason Will Be Provided For Punishments Issued", "&c- If you use it, unpunish the player and issue a new punishment with reason.");
        }
        else {
          Utils.createItem(inv, 339, 1, 26, "&e&lPlayer Data", "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &cOFFLINE", "&bLast Played: &d" + plugin.getConfig().getString(new StringBuilder().append(offlinePlayer.getUniqueId()).append(".lastSeen").toString()));
          Utils.createItemHead(inv, 5, offlinePlayer);
        }
      }
      else {
        Utils.createItemHead(inv, 5, offlinePlayer, "&eIn-Game Status: &cNEVER SEEN");
        Utils.createItem(inv, 38, 1, 54, "&c&oWARNING: &5Unregistered User", new String[0]);
        Utils.createItem(inv, 399, 1, 1, "==");
        Utils.createItem(inv, 399, 1, 2, "==");
        Utils.createItem(inv, 399, 1, 3, "==");
        Utils.createItem(inv, 399, 1, 4, "==");
        Utils.createItem(inv, 399, 1, 6, "==");
        Utils.createItem(inv, 399, 1, 7, "==");
        Utils.createItem(inv, 399, 1, 8, "==");
        Utils.createItem(inv, 399, 1, 9, "==");
        Utils.createItem(inv, 340, 1, 46, "&aAccess " + Bukkit.getOfflinePlayer(p).getName() + "'s Punishment History", new String[] { "&cUse for: ", "&c- Punishment Decisions", "&c- Staffing Process", "&c- Abuse Investigation" });
        toReturn.setContents(inv.getContents());
        inv.clear();
        return toReturn;
      }
      
    }
    else if (s.hasPermission("uv.othersAdmin")) {
        Utils.createItemHead(inv, 5, player);
      	Utils.createItem(inv, 339, 1, 26, "&e&lPlayer Data", "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE", " ", "&cLast IP Address: &b" + lastIP);
        Utils.createItem(inv, 101, 1, 54, "&a&lOpen &c&lpunish&d&lUV&c&l+ &a&lMenu", "&c- WARNING: No Reason Will Be Provided For Punishments Issued", "&c- If you use it, unpunish the player and issue a new punishment with reason.");
        Utils.createItem(inv, 385, 1, 31, "&b&l[&a&lmsg&d&lUV&b&l] &b&lMacros", "&cClick for Private Message Macros");
        Utils.createItem(inv, 374, 1, 33, "&c&lTeleport To Player");
        Utils.createItem(inv, 297, 1, 20, "&a&lHealth + Hunger", "&cHealth: &d" + player.getHealth(), "&bHunger: &d" + player.getFoodLevel(), " ", "&9Click to &aHeal&9.");
        Utils.createItem(inv, 321, 1, 44, "&4&lOpen Player Inventory", "&cProceed With Caution");
        // game mode manager
        if (player.getGameMode().getValue() == 1) {
            Utils.createItem(inv, 384, 1, 27, "&c&lClient Gamemode", "&a&lCreative", " ", "&9Click to Update: &4&lSurvival");
        }
        else if (player.getGameMode().getValue() == 0) {
            Utils.createItem(inv, 384, 1, 27, "&c&lClient Gamemode", "&4&lSurvival", " ", "&9Click to Update: &a&lCreative");
        }
        Utils.createItem(inv, 122, 1, 19, "&c&lClear &b&l" + player.getName() + "'s &c&lInventory");
    }
    else {
        Utils.createItemHead(inv, 5, player);
        Utils.createItem(inv, 339, 1, 26, "&e&lPlayer Data", "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&cPast Punishments: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE");
        Utils.createItem(inv, 385, 1, 31, "&b&l[&a&lmsg&d&lUV&b&l] &b&lMacros", "&cClick for Private Message Macros");
    }
    if ((s.hasPermission("uv.rank.srmod")) || (s.hasPermission("uv.rank.admin")) || (s.hasPermission("uv.rank.owner"))) {
      Utils.createItem(inv, 138, 1, 23, "&c&lClient Status", PermissionManager.getUserTag(Bukkit.getOfflinePlayer(p).getUniqueId()), "&f[&6&lSR.MOD&c&l+&f]&9: Click to Modify");
    }
    else {
      Utils.createItem(inv, 138, 1, 23, "&c&lClient Status", PermissionManager.getUserTag(Bukkit.getOfflinePlayer(p).getUniqueId()));
    }
    String userStatus = PermissionManager.getUserStatus(offlinePlayer.getUniqueId());
    String userAdvisor = PermissionManager.getAdvisor(offlinePlayer.getUniqueId());
    if ((userStatus.startsWith("HELPER")) || (userStatus.startsWith("MODERATOR") || userStatus.startsWith("SENIOR_MODERATOR"))) {
    	if (userAdvisor == "" && (userStatus.startsWith("HELPER") || userStatus.startsWith("MODERATOR"))) {
    		if (s.hasPermission("uv.rank.srmod")) {
        	    Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&e&oUnassigned", "&f[&6&lSR.MOD&c&l+&f]&9: Click to Claim as &cAdvisee");
    		}
    		else {
        	    Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&e&oUnassigned");
    		}
    	}
    	else if (userAdvisor == "" && userStatus.startsWith("SENIOR_MODERATOR")) {
    		if (s.hasPermission("uv.rank.admin")) {
        	    Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&e&oUnassigned", "&f[&c&lADMIN+&f]&9: Click to Claim as &cAdvisee");
    		}
    		else {
        	    Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&e&oUnassigned");
    		}
    	}
    	else if (userAdvisor.startsWith(s.getName().toString())) {
    	    Utils.createEnchantedItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&4&l" + s.getName(), "&cClick to Open Advisor Menu");
    	}
    	else {
    		if (userStatus.startsWith("SENIOR_MODERATOR")) {
        		if (s.hasPermission("uv.rank.admin")) {
            	    Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName(), "&f[&c&lADMIN+&f]&9: Click to Claim as &cAdvisee");	
        		}
        		else {
        			Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName());
        		}
    		}
    		else {
        		if (s.hasPermission("uv.rank.srmod")) {
            	    Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName(), "&f[&6&lSR.MOD&c&l+&f]&9: Click to Claim as &cAdvisee");	
        		}
        		else {
        			Utils.createItem(inv, 390, 1, 41, "&c&lAdvisor Info", "&a&l" + Bukkit.getOfflinePlayer(PermissionManager.getAdvisor(offlinePlayer.getUniqueId())).getName());
        		}
    		}

    	}
    }
    Utils.createItem(inv, 111, 1, 1, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 2, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 3, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 4, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 6, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 7, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 8, "==", new String[0]);
    Utils.createItem(inv, 111, 1, 9, "==", new String[0]);
    Utils.createItem(inv, 340, 1, 46, "&aAccess " + Bukkit.getOfflinePlayer(p).getName() + "'s Punishment History", new String[] { "&cUse for: ", "&c- Punishment Decisions", "&c- Staffing Process", "&c- Abuse Investigation" });
    toReturn.setContents(inv.getContents());
    inv.clear();
    return toReturn;
  }
}
