package me.pavl.ultraviolet.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.utils.Utils;

public class PermissionDatabase
{
  private static Connection c;
  public static String host;
  public static String database;
  public static String username;
  public static String password;
  public static int port;
  
  public PermissionDatabase() {}
  
  public static Main plugin = (Main)Main.getPlugin(Main.class);
  
  public static void mysqlSetup() { plugin.getConfig().options().copyDefaults(true);
    plugin.saveConfig();
    database = plugin.getConfig().getString("MYSQL.permission_data.database");
    host = plugin.getConfig().getString("MYSQL.permission_data.host");
    port = plugin.getConfig().getInt("MYSQL.permission_data.port");
    username = plugin.getConfig().getString("MYSQL.permission_data.username");
    password = plugin.getConfig().getString("MYSQL.permission_data.password");
    try
    {
      if ((getConnection() != null) && (!getConnection().isClosed())) {
        return;
      }
      

      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
      setConnection(con);
      Statement st = con.createStatement();
      st.executeUpdate("CREATE TABLE IF NOT EXISTS perm_data (UUID text, RANK text, TAG text, ADVISOR text)");
      Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &9Connected> &a&lPERMISSION"));
    }
    catch (ClassNotFoundException event) {
      event.printStackTrace();
      org.bukkit.Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &cFailed> &a&lPERMISSION"));
    }
    catch (SQLException event) {
      event.printStackTrace();
      org.bukkit.Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &cFailed> &a&lPERMISSION"));
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
