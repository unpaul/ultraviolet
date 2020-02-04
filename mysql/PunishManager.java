package me.pavl.ultraviolet.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishManager
{
  static Main main = (Main)Main.getPlugin(Main.class);
  public static BanList banList = Bukkit.getBanList(BanList.Type.NAME);
  static BanList ipBanList = Bukkit.getBanList(BanList.Type.IP);
  

  public PunishManager() {}
  

  public static int getOccurrence(UUID criminalUUID)
  {
    int i = 0;
    try {
      PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=?");
      statement.setString(1, criminalUUID.toString());
      
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        String punishType = results.getString("PUNISH_TYPE");
    	if (!(punishType.contains("Pending"))) {
            i++;
    	}
      }
      return i + 1;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return 1;
  }
  
  public static int getSev1MuteLengthHours(UUID criminalUUID) { 
	  int calculation = (int)Math.pow(2.0D, getOccurrence(criminalUUID));
    if (calculation > 336) {
      calculation = 336;
    }
    return calculation;
  }
  
  public static int getSev2MuteLengthHours(UUID criminalUUID) { int calculation = 4 * (int)Math.pow(2.0D, getOccurrence(criminalUUID) - 1);
    if (calculation > 336) {
      calculation = 336;
    }
    return calculation;
  }
  
  public static int getSev3MuteLengthHours(UUID criminalUUID) { int calculation = 24 * (int)Math.pow(2.0D, getOccurrence(criminalUUID) - 1);
    if (calculation > 720) {
      calculation = 720;
    }
    return calculation;
  }
  
  public static int getSev1GameplayBanLengthHours(UUID criminalUUID) { int calculation = 4 * (int)Math.pow(2.0D, getOccurrence(criminalUUID) - 1);
    if (calculation > 336) {
      calculation = 336;
    }
    return calculation;
  }
  
  public static int getSev2GameplayBanLengthHours(UUID criminalUUID) { int calculation = 24 * (int)Math.pow(2.0D, getOccurrence(criminalUUID) - 1);
    if (calculation > 720) {
      calculation = 720;
    }
    return calculation;
  }
  
  public static int getSev3GameplayBanLengthHours(UUID criminalUUID) { return 720; }
  
  public static int getSev1HackingBanLengthHours(UUID criminalUUID) {
    int calculation = 24 * (int)Math.pow(2.0D, getOccurrence(criminalUUID) - 1);
    if (calculation > 720) {
      calculation = 720;
    }
    return calculation;
  }
  
  public static int getSev2HackingBanLengthHours(UUID criminalUUID) { int calculation = 720;
    if (getOccurrence(criminalUUID) >= 2) {
      calculation = 1000;
    }
    return calculation;
  }
  
  public static void addPunishment(UUID criminalUUID, String type, String severity, UUID punisherUUID, String reason, String startDate, String endDate) {
    try { PreparedStatement insert = PunishDatabase.getConnection().prepareStatement("INSERT INTO punish_data (CRIMINAL,PUNISH_OCCURRENCE,PUNISH_TYPE,SEVERITY,PUNISHER,REASON,START_DATE,END_DATE,REMOVER,REMOVE_REASON,REMOVE_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
      insert.setString(1, criminalUUID.toString());
      insert.setInt(2, getOccurrence(criminalUUID));
      insert.setString(3, type);
      insert.setString(4, severity);
      insert.setString(5, punisherUUID.toString());
      insert.setString(6, reason);
      insert.setString(7, startDate);
      insert.setString(8, endDate);
      insert.setString(9, "null");
      insert.setString(10, "null");
      insert.setString(11, "null");
      insert.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  public static void addInappropriateNameReport(String criminalName, String punisherName) {
	    try { 
	      PreparedStatement insert = PunishDatabase.getConnection().prepareStatement("INSERT INTO punish_data (CRIMINAL,PUNISH_OCCURRENCE,PUNISH_TYPE,SEVERITY,PUNISHER,REASON,START_DATE,END_DATE,REMOVER,REMOVE_REASON,REMOVE_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
	      insert.setString(1, criminalName);
	      insert.setInt(2, 0);
	      insert.setString(3, "Inappropriate Name [Pending]");
	      insert.setString(4, "0");
	      insert.setString(5, punisherName);
	      insert.setString(6, " ");
	      insert.setString(7, "PENDING");
	      insert.setString(8, "PENDING");
	      insert.setString(9, "null");
	      insert.setString(10, "null");
	      insert.setString(11, "null");
	      insert.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
  }
  public static void clearReports(String criteria) {
	    try { PreparedStatement update = PunishDatabase.getConnection().prepareStatement("DELETE FROM punish_data WHERE PUNISH_TYPE=?");
	      update.setString(1, criteria);
	      update.executeUpdate();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	  }
  public static void removePunishment(UUID criminalUUID, String startDate, UUID removerUUID, String removeReason, String removeDate) {
    try { Player onlineCriminal = Bukkit.getPlayer(criminalUUID);
      PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=? AND START_DATE=?");
      statement.setString(1, criminalUUID.toString());
      statement.setString(2, startDate);
      ResultSet results = statement.executeQuery();
      if (results.next()) {
        if (results.getString("END_DATE").startsWith("Never")) {
          PreparedStatement update = PunishDatabase.getConnection().prepareStatement("UPDATE punish_data SET END_DATE=? , REMOVER=? , REMOVE_REASON=? , REMOVE_DATE=? WHERE CRIMINAL=? AND START_DATE=?");
          update.setString(1, removeDate);
          update.setString(2, removerUUID.toString());
          update.setString(3, removeReason);
          update.setString(4, removeDate);
          update.setString(5, criminalUUID.toString());
          update.setString(6, startDate);
          update.executeUpdate();
          banList.pardon(Bukkit.getOfflinePlayer(criminalUUID).getName());
          ipBanList.pardon(main.getConfig().getString(criminalUUID + ".lastIP"));
          Bukkit.getPlayer(removerUUID).sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Removed &b» &5" + Bukkit.getOfflinePlayer(criminalUUID).getName()));
          if (onlineCriminal != null) {
            onlineCriminal.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &aunpunished &9by &c" + Bukkit.getPlayer(removerUUID).getName().toString()));
          }
        }
        else if (!Utils.getDateHasPassed(Utils.getStringAsDate(results.getString("END_DATE")))) {
          PreparedStatement update = PunishDatabase.getConnection().prepareStatement("UPDATE punish_data SET END_DATE=? , REMOVER=? , REMOVE_REASON=? , REMOVE_DATE=? WHERE CRIMINAL=? AND START_DATE=?");
          update.setString(1, removeDate);
          update.setString(2, removerUUID.toString());
          update.setString(3, removeReason);
          update.setString(4, removeDate);
          update.setString(5, criminalUUID.toString());
          update.setString(6, startDate);
          update.executeUpdate();
          banList.pardon(Bukkit.getOfflinePlayer(criminalUUID).getName());
          ipBanList.pardon(main.getConfig().getString(criminalUUID + ".lastIP"));
          Bukkit.getPlayer(removerUUID).sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Removed &b» &5" + Bukkit.getOfflinePlayer(criminalUUID).getName()));
          if (onlineCriminal != null) {
            onlineCriminal.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &aunpunished &9by &c" + Bukkit.getPlayer(removerUUID).getName().toString()));
          }
        }
        else {
          Bukkit.getPlayer(removerUUID).sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Unsuccessful &b» &bAlready Removed/Expired"));
          return;
        }
        
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void clearPunishmentHistory(UUID criminalUUID) {
    try { PreparedStatement update = PunishDatabase.getConnection().prepareStatement("DELETE FROM punish_data WHERE CRIMINAL=?");
      update.setString(1, criminalUUID.toString());
      update.executeUpdate();
      banList.pardon(Bukkit.getOfflinePlayer(criminalUUID).getName());
      ipBanList.pardon(main.getConfig().getString(criminalUUID + ".lastIP"));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public static boolean isMuted(UUID criminalUUID) {
    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=?");
      statement.setString(1, criminalUUID.toString());
      
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        String punishType = results.getString("PUNISH_TYPE");
        String punishEndDate = results.getString("END_DATE");
        if (punishType.startsWith("Mute")) {
          if (punishEndDate.startsWith("Never")) {
            return true;
          }
          
          if (!Utils.getDateHasPassed(Utils.getStringAsDate(punishEndDate))) {
            return true;
          }
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public static String getCurrentMuteReason(UUID criminalUUID) {
    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=?");
      statement.setString(1, criminalUUID.toString());
      
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        String punishType = results.getString("PUNISH_TYPE");
        String punishEndDate = results.getString("END_DATE");
        String punishReason = results.getString("REASON");
        if (punishType.startsWith("Mute")) {
          if (punishEndDate.startsWith("Never")) {
            return punishReason;
          }
          
          if (!Utils.getDateHasPassed(Utils.getStringAsDate(punishEndDate))) {
            return punishReason;
          }
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return "";
  }
  
  public static String getCurrentMuteEndDate(UUID criminalUUID) {
    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=?");
      statement.setString(1, criminalUUID.toString());
      
      ResultSet results = statement.executeQuery();
      while (results.next()) {
        String punishType = results.getString("PUNISH_TYPE");
        String punishEndDate = results.getString("END_DATE");
        if (punishType.startsWith("Mute")) {
          if (punishEndDate.startsWith("Never")) {
            return "Permanent";
          }
          
          if (!Utils.getDateHasPassed(Utils.getStringAsDate(punishEndDate))) {
            return punishEndDate;
          }
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return "";
  }
  public static String getCurrentBanReason(UUID criminalUUID) {
	    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=?");
	      statement.setString(1, criminalUUID.toString());
	      
	      ResultSet results = statement.executeQuery();
	      while (results.next()) {
	        String punishType = results.getString("PUNISH_TYPE");
	        String punishEndDate = results.getString("END_DATE");
	        String punishReason = results.getString("REASON");
	        if (punishType.contains("Ban")) {
	          if (punishEndDate.startsWith("Never")) {
	            return punishReason;
	          }
	          
	          if (!Utils.getDateHasPassed(Utils.getStringAsDate(punishEndDate))) {
	            return punishReason;
	          }
	        }
	      }
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	    return "";
	  }
	  
	  public static String getCurrentBanEndDate(UUID criminalUUID) {
	    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM punish_data WHERE CRIMINAL=?");
	      statement.setString(1, criminalUUID.toString());
	      
	      ResultSet results = statement.executeQuery();
	      while (results.next()) {
	        String punishType = results.getString("PUNISH_TYPE");
	        String punishEndDate = results.getString("END_DATE");
	        if (punishType.contains("Ban")) {
	          if (punishEndDate.startsWith("Never")) {
	            return "Permanent";
	          }
	          
	          if (!Utils.getDateHasPassed(Utils.getStringAsDate(punishEndDate))) {
	            return punishEndDate;
	          }
	        }
	      }
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	    return "";
	  }
}
