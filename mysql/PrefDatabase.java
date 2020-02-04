package me.pavl.ultraviolet.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.utils.Utils;

public class PrefDatabase
{
  private static Connection c;
  public static String host;
  public static String database;
  public static String username;
  public static String password;
  public static int port;
  
  public PrefDatabase() {}
  
  public static Main plugin = (Main)Main.getPlugin(Main.class);
  
  public static void mysqlSetup() { 
	plugin.getConfig().options().copyDefaults(true);
    plugin.saveConfig();
    database = plugin.getConfig().getString("MYSQL.pref_data.database");
    host = plugin.getConfig().getString("MYSQL.pref_data.host");
    port = plugin.getConfig().getInt("MYSQL.pref_data.port");
    username = plugin.getConfig().getString("MYSQL.pref_data.username");
    password = plugin.getConfig().getString("MYSQL.pref_data.password");
    try
    {
      if ((getConnection() != null) && (!getConnection().isClosed())) {
        return;
      }
      

      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
      setConnection(con);
      Statement st = con.createStatement();
      st.executeUpdate("CREATE TABLE IF NOT EXISTS pref_data (UUID text, FF_RADIUS int, SPY int)");
      Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &9Connected> &e&lPREFERENCES"));
    }
    catch (ClassNotFoundException event) {
      event.printStackTrace();
      org.bukkit.Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &cFailed> &e&lPREFERENCES"));
    }
    catch (SQLException event) {
      event.printStackTrace();
      org.bukkit.Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &cFailed> &e&lPREFERENCES"));
    }
  }
  
  public static Connection getConnection()
  {
    return c;
  }
  
  public static void setConnection(Connection connection) {
    c = connection;
  }
}
