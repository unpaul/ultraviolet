package me.pavl.ultraviolet.ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishDatabase;
import me.pavl.ultraviolet.utils.Utils;

public class ReportsUI {
	  public static Inventory inv;
	  public static String inventory_name;
	  
	  
	  public static int inv_rows = 54;
	  public static Main plugin = (Main)Main.getPlugin(Main.class);
	  
	  public static void initialize() { 
		inventory_name = Utils.chat("&9&lreports&d&lUV");
	    inv = Bukkit.createInventory(null, inv_rows);
	  }
	  
	public static Inventory GUI(Player s, String criteria)
	  {
	    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
	    UUID senderUUID = s.getUniqueId();

	    Inventory inv = Bukkit.createInventory(null, 54);
	    Utils.createItemHead(inv, 5, s, "&dRank: " + PermissionManager.getUserTag(senderUUID), "&b&l[&d&lUV&b&l] &9&lREPORTS MENU", "&9[Only Displays Up To 36 Recent Entries]", "&2[More Recent Entries are Shown First]");
	    Utils.createItem(inv, 31, 1, 1, "==");
	    Utils.createItem(inv, 31, 1, 2, "==");
	    Utils.createItem(inv, 31, 1, 3, "==");
	    Utils.createItem(inv, 31, 1, 4, "==");
	    Utils.createItem(inv, 31, 1, 6, "==");
	    Utils.createItem(inv, 31, 1, 7, "==");
	    Utils.createItem(inv, 31, 1, 8, "==");
	    Utils.createItem(inv, 31, 1, 9, "==");
        Utils.createItem(inv, 326, 1, 50, "&b&lReport Criteria", "&c" + criteria);
        if (criteria.startsWith("InappName")) {
            try
            {
              PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE PUNISH_OCCURRENCE=?");
              statement.setInt(1, 0);
              
              ResultSet results = statement.executeQuery();
              int i = 10;
              boolean resultsFound = false;
              Utils.createItem(inv, 7, 1, 46, "&c&lDeny Remaining Reports", "&a- Click on the Reports to Approve", "&a- Click this Button to Deny Remaining Reports" );
              while ((results.next()) && (i != 46)) {
                String reportType = results.getString("PUNISH_TYPE");
            	if (reportType.startsWith("Inappropriate Name [Pending]")) {
                    resultsFound = true;
                    String criminal = results.getString("CRIMINAL");
                    String punisher = results.getString("PUNISHER");
                    Utils.createItem(inv, 369, 1, i, "&c&lInappropriate Name", "&bUser: " + criminal, "&eReporter: " + punisher, "&4Click to Approve Report", "&c- Permanently Bans " + criminal);
                    i += 1;
            	}
              
        }
              if (!resultsFound) {
                  inv.remove(Material.BEDROCK);
                  Utils.createItem(inv, 382, 1, 32, "&a&lNo Reports Available", "&c- Try modifying criteria");
                  Utils.createItem(inv, 102, 1, 19, "==");
                  Utils.createItem(inv, 102, 1, 20, "==");
                  Utils.createItem(inv, 102, 1, 21, "==");
                  Utils.createItem(inv, 102, 1, 22, "==");
                  Utils.createItem(inv, 102, 1, 23, "==");
                  Utils.createItem(inv, 102, 1, 24, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 25, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 26, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 27, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 37, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 38, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 39, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 40, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 41, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 42, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 43, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 44, "==", new String[0]);
                  Utils.createItem(inv, 102, 1, 45, "==", new String[0]);

                }
            }
          catch (SQLException e){
        	  e.printStackTrace();
          }
	  }
	    toReturn.setContents(inv.getContents());
	    return toReturn;
}
}
	  
