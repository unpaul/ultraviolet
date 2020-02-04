package me.pavl.ultraviolet.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermissionManager
{
  public static void addUserToGroup(UUID playerUUID, String rank, String tag)
  {
    try {
      PreparedStatement insert = PermissionDatabase.getConnection().prepareStatement("INSERT INTO perm_data (UUID,RANK,TAG,ADVISOR) VALUES (?,?,?,?)");
      insert.setString(1, playerUUID.toString());
      insert.setString(2, rank);
      insert.setString(3, tag);
      insert.setString(4, "");
      insert.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void removeUserFromGroup(UUID playerUUID, String rank) {
    try { PreparedStatement update = PunishDatabase.getConnection().prepareStatement("DELETE FROM perm_data WHERE UUID=? AND RANK=?");
      update.setString(1, playerUUID.toString());
      update.setString(2, rank);
      update.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void removeUserFromAllGroups(UUID playerUUID) {
    try { PreparedStatement update = PunishDatabase.getConnection().prepareStatement("DELETE FROM perm_data WHERE UUID=?");
      update.setString(1, playerUUID.toString());
      update.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  public static boolean checkUserInGroup(UUID playerUUID, String rank) {
    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=? AND RANK=?");
      statement.setString(1, playerUUID.toString());
      statement.setString(2, rank);
      
      ResultSet results = statement.executeQuery();
      if (results.next()) {
        return true;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public static void assignAdvisorToUser(UUID playerUUID, String advisor) {
	    try {
	      PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=?");
	      statement.setString(1, playerUUID.toString());
	      ResultSet results = statement.executeQuery();
	      if (results.next()) {
	        PreparedStatement update = PunishDatabase.getConnection().prepareStatement("UPDATE perm_data SET ADVISOR=? WHERE UUID=?");
	        update.setString(1, advisor);
	        update.setString(2, playerUUID.toString());
	        update.executeUpdate();
	        }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }	
  }
  public static String getAdvisor(UUID playerUUID) {
	  try { 
		PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=?");
	  	statement.setString(1, playerUUID.toString());
		      
		ResultSet results = statement.executeQuery();
		if (results.next()) {
		   return results.getString("ADVISOR");
		}
	  }
	  catch (SQLException e) {
		e.printStackTrace();
	  }
	  return "";
 }
  public static String getUserTag(UUID playerUUID) {
	    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=?");
	      statement.setString(1, playerUUID.toString());
	      ResultSet results = statement.executeQuery();
	      if (results.next()) {
	    	return results.getString("TAG");
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return "&7[Member]";
	  }
public static String[] getUserTags(UUID playerUUID) {
    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=?");
	  List<String> userTags = new ArrayList<String>();
      statement.setString(1, playerUUID.toString());
      ResultSet results = statement.executeQuery();
      while (results.next()) {
    	userTags.add(results.getString("TAG"));
      }
      String[] tagArray = userTags.toArray(new String[userTags.size()]);
      return tagArray;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    String[] tagArray = {};
    return tagArray;
  }
public static void editUserTag(UUID playerUUID, String newTag) {
    try {
      PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=?");
      statement.setString(1, playerUUID.toString());
      ResultSet results = statement.executeQuery();
      if (results.next()) {
        PreparedStatement update = PunishDatabase.getConnection().prepareStatement("UPDATE perm_data SET TAG=? WHERE UUID=?");
        update.setString(1, newTag);
        update.setString(2, playerUUID.toString());
        update.executeUpdate();
        }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }	
}
public static String[] getUserAdvisees(String playerName) {
    try { 
      PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE ADVISOR=?");
	  List<String> advisees = new ArrayList<String>();
      statement.setString(1, playerName);
      ResultSet results = statement.executeQuery();
      while (results.next()) {
    	advisees.add(results.getString("UUID"));
      }
      String[] adviseeArray = advisees.toArray(new String[advisees.size()]);
      return adviseeArray;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    String[] adviseeArray = {};
    return adviseeArray;
  }
  public static String getUserStatus(UUID playerUUID) {
    try { PreparedStatement statement = PunishDatabase.getConnection().prepareStatement("SELECT * FROM perm_data WHERE UUID=?");
      statement.setString(1, playerUUID.toString());
      
      ResultSet results = statement.executeQuery();
      if (results.next()) {
        return results.getString("RANK");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return "";
  }
}
