package me.pavl.ultraviolet.ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishDatabase;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HistoryUI
{
  public static Inventory inv;
  public static String inventory_name;
  
  public HistoryUI() {}
  
  public static int inv_rows = 54;
  public static Main plugin = (Main)Main.getPlugin(Main.class);
  
  public static void initialize() { inventory_name = Utils.chat("&e&lhistory&d&lUV");
    inv = Bukkit.createInventory(null, inv_rows);
  }
  
  @SuppressWarnings("deprecation")
public static Inventory GUI(Player s, String p, String reason)
  {
    Inventory toReturn = Bukkit.createInventory(s, inv_rows, inventory_name);
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p);
    UUID senderUUID = s.getUniqueId();
    plugin.getConfig().set(senderUUID + ".lastPunishee", p);
    plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
    plugin.saveConfig();

    Inventory inv = Bukkit.createInventory(null, 54);
    Utils.createItemHead(inv, 5, offlinePlayer, "&dRank: " + PermissionManager.getUserTag(offlinePlayer.getUniqueId()), "&b&l[&d&lUV&b&l] &c&lPUNISH &e&lHISTORY", "&9[Only Displays Up To 36 Recent Entries]", "&2[More Recent Entries are Shown First]");
    Utils.createItem(inv, 340, 1, 1, "==");
    Utils.createItem(inv, 340, 1, 2, "==");
    Utils.createItem(inv, 340, 1, 3, "==");
    Utils.createItem(inv, 340, 1, 4, "==");
    Utils.createItem(inv, 340, 1, 6, "==");
    Utils.createItem(inv, 340, 1, 7, "==");
    Utils.createItem(inv, 340, 1, 8, "==");
    Utils.createItem(inv, 340, 1, 9, "==");
    if (s.hasPermission("uv.punishAdmin")) {
      Utils.createItem(inv, 7, 1, 46, "&9Clear " + offlinePlayer.getName() + "&9's History", new String[] { "&7&l[&4&lADMIN&c&l+&7&l]: &aOnly in Rare Situations", "&cThis action will be logged." });
      toReturn = Bukkit.createInventory(s, inv_rows, Utils.chat("&e&lhistory&d&lUV&4&l+"));
    }
    if ((reason != "") && (!s.hasPermission("uv.punishAdmin"))) {
      Utils.createItem(inv, 323, 1, 54, "&cReport " + offlinePlayer.getName() + " as an Inappropriate Name", new String[] { "&b- Use Judgement/Guidelines.", "&b- &7[&4ADMIN&c+&7]&b Will Review/Judge This." });
    }
    try
    {
      PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=? ORDER BY PUNISH_OCCURRENCE DESC");
      statement.setString(1, offlinePlayer.getUniqueId().toString());
      
      ResultSet results = statement.executeQuery();
      boolean hasHistory = false;
      int i = 10;
      
      while ((results.next()) && (i != 46)) {
        hasHistory = true;
        String punishType = results.getString("PUNISH_TYPE");
        String remover = results.getString("REMOVER");
        if (punishType.startsWith("Network Ban")) {
          if (results.getString("END_DATE").startsWith("Never")) {
            Utils.createEnchantedItem(inv, 152, 1, i, "&4&lNetwork Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          else if (!Utils.getDateHasPassed(Utils.getStringAsDate(results.getString("END_DATE")))) {
            Utils.createEnchantedItem(inv, 152, 1, i, "&4&lNetwork Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });

          }
          else if (!remover.startsWith("null")) {
            Utils.createItem(inv, 152, 1, i, "&4&lNetwork Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(results.getString("PUNISHER")).getName(), "&dDate: " + results.getString("START_DATE"), " ", "&eRemove Reason: " + results.getString("REMOVE_REASON"), "&cRemover: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("REMOVER"))).getName(), "&dRemove Date: " + results.getString("REMOVE_DATE") });
          }
          else {
            Utils.createItem(inv, 152, 1, i, "&4&lNetwork Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          
        }
        else if (punishType.contains("Pending")) {
        	continue;
        }
        else if (punishType.startsWith("Hacking Ban")) {
          if (results.getString("END_DATE").startsWith("Never")) {
            Utils.createEnchantedItem(inv, 276, 1, i, "&c&lHacking Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          else if (!Utils.getDateHasPassed(Utils.getStringAsDate(results.getString("END_DATE")))) {
            Utils.createEnchantedItem(inv, 276, 1, i, "&c&lHacking Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });

          }
          else if (!remover.startsWith("null")) {
            Utils.createItem(inv, 276, 1, i, "&c&lHacking Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE"), " ", "&eRemove Reason: " + results.getString("REMOVE_REASON"), "&cRemover: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("REMOVER"))).getName(), "&dRemove Date: " + results.getString("REMOVE_DATE") });
          }
          else {
            Utils.createItem(inv, 276, 1, i, "&c&lHacking Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          
        }
        else if (punishType.startsWith("Gameplay Ban")) {
          if (results.getString("END_DATE").startsWith("Never")) {
            Utils.createEnchantedItem(inv, 358, 1, i, "&e&lGameplay Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          else if (!Utils.getDateHasPassed(Utils.getStringAsDate(results.getString("END_DATE")))) {
            Utils.createEnchantedItem(inv, 358, 1, i, "&e&lGameplay Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });

          }
          else if (!remover.startsWith("null")) {
            Utils.createItem(inv, 358, 1, i, "&e&lGameplay Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE"), " ", "&eRemove Reason: " + results.getString("REMOVE_REASON"), "&cRemover: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("REMOVER"))).getName(), "&dRemove Date: " + results.getString("REMOVE_DATE") });
          }
          else {
            Utils.createItem(inv, 358, 1, i, "&e&lGameplay Ban", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          
        }
        else if (punishType.startsWith("Mute")) {
          if (results.getString("END_DATE").startsWith("Never")) {
            Utils.createEnchantedItem(inv, 386, 1, i, "&a&lMute", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          else if (!Utils.getDateHasPassed(Utils.getStringAsDate(results.getString("END_DATE")))) {
            Utils.createEnchantedItem(inv, 386, 1, i, "&a&lMute", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });

          }
          else if (!remover.startsWith("null")) {
            Utils.createItem(inv, 386, 1, i, "&a&lMute", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE"), " ", "&eRemove Reason: " + results.getString("REMOVE_REASON"), "&cRemover: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("REMOVER"))).getName(), "&dRemove Date: " + results.getString("REMOVE_DATE") });
          }
          else {
            Utils.createItem(inv, 386, 1, i, "&a&lMute", new String[] { "&eReason: " + results.getString("REASON"), "&bSeverity: &c" + results.getString("SEVERITY"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
          }
          
        }
        else if (punishType.startsWith("Warning")) {
          Utils.createItem(inv, 339, 1, i, "&a&lWarning", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });

        }
        else if (results.getString("END_DATE").startsWith("Never")) {
          Utils.createEnchantedItem(inv, 152, 1, i, "&4&lPermanent Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
        }
        else if (!Utils.getDateHasPassed(Utils.getStringAsDate(results.getString("END_DATE")))) {
          Utils.createEnchantedItem(inv, 152, 1, i, "&4&lPermanent Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });

        }
        else if (!remover.startsWith("null")) {
          Utils.createItem(inv, 152, 1, i, "&4&lPermanent Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE"), " ", "&eRemove Reason: " + results.getString("REMOVE_REASON"), "&cRemover: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("REMOVER"))).getName(), "&dRemove Date: " + results.getString("REMOVE_DATE") });
        }
        else {
          Utils.createItem(inv, 152, 1, i, "&4&lPermanent Ban", new String[] { "&eReason: " + results.getString("REASON"), "&cStaff: " + Bukkit.getOfflinePlayer(UUID.fromString(results.getString("PUNISHER"))).getName(), "&dDate: " + results.getString("START_DATE") });
        }
        

        i++;
      }
      

      if (!hasHistory) {
        inv.remove(Material.BEDROCK);
        Utils.createItem(inv, 382, 1, 32, "&a" + offlinePlayer.getName() + " &bhas no punishment history.", new String[0]);
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
      else if (reason != "") {
        Utils.createItem(inv, 326, 1, 50, "&b&lRemove Reason", new String[] { "&c" + reason, "&9[Click a Glowing Punishment to Remove It]" });
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    toReturn.setContents(inv.getContents());
    return toReturn;
  }
}
