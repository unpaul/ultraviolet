package me.pavl.ultraviolet.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import me.pavl.ultraviolet.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;

public class PrefManager
{
  static Main main = (Main)Main.getPlugin(Main.class);
  public static BanList banList = Bukkit.getBanList(BanList.Type.NAME);
  static BanList ipBanList = Bukkit.getBanList(BanList.Type.IP);
  

  public PrefManager() {}
  public static void addUser(UUID UUID)
  {
    try {
      PreparedStatement statement = PrefDatabase.getConnection().prepareStatement("SELECT * FROM pref_data WHERE UUID=?");
      statement.setString(1, UUID.toString());
      
      ResultSet results = statement.executeQuery();
      if (!results.next()) {
	      PreparedStatement insert = PrefDatabase.getConnection().prepareStatement("INSERT INTO pref_data (SPY,FF_RADIUS,UUID) VALUES (?,?,?)");
	        insert.setString(1, String.valueOf(0));
	        insert.setString(2, String.valueOf(-1));
	        insert.setString(3, UUID.toString());
	        insert.executeUpdate();
	        }
      }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }  

  public static void setForcefield(UUID bossUUID, int radius)
  {
    try {
      PreparedStatement statement = PrefDatabase.getConnection().prepareStatement("SELECT * FROM pref_data WHERE UUID=?");
      statement.setString(1, bossUUID.toString());
      
      ResultSet results = statement.executeQuery();
      if (results.next()) {
	      PreparedStatement update = PrefDatabase.getConnection().prepareStatement("UPDATE pref_data SET FF_RADIUS=? WHERE UUID=?");
	        update.setString(1, String.valueOf(radius));
	        update.setString(2, bossUUID.toString());
	        update.executeUpdate();
	        }
      }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public static String getForcefield(UUID bossUUID) {
	  try { 
		PreparedStatement statement = PrefDatabase.getConnection().prepareStatement("SELECT * FROM pref_data WHERE UUID=?");
	  	statement.setString(1, bossUUID.toString());
		      
		ResultSet results = statement.executeQuery();
		if (results.next()) {
		   return results.getString("FF_RADIUS");
		}
	  }
	  catch (SQLException e) {
		e.printStackTrace();
	  }
	  return "";
 }
  
  public static void setSpy(UUID bossUUID, int isHe)
  {
    try {
      PreparedStatement statement = PrefDatabase.getConnection().prepareStatement("SELECT * FROM pref_data WHERE UUID=?");
      statement.setString(1, bossUUID.toString());
      
      ResultSet results = statement.executeQuery();
      if (results.next()) {
	      PreparedStatement update = PrefDatabase.getConnection().prepareStatement("UPDATE pref_data SET SPY=? WHERE UUID=?");
	        update.setString(1, String.valueOf(isHe));
	        update.setString(2, bossUUID.toString());
	        update.executeUpdate();
	        }
      }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public static String getSpy(UUID bossUUID) {
	  try { 
		PreparedStatement statement = PrefDatabase.getConnection().prepareStatement("SELECT * FROM pref_data WHERE UUID=?");
	  	statement.setString(1, bossUUID.toString());
		      
		ResultSet results = statement.executeQuery();
		if (results.next()) {
		   return results.getString("SPY");
		}
	  }
	  catch (SQLException e) {
		e.printStackTrace();
	  }
	  return "";
 }
}