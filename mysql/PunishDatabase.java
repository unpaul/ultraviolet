package me.pavl.ultraviolet.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.utils.Utils;

public class PunishDatabase
{
  private static Connection c;
  public static String host;
  public static String database;
  public static String username;
  public static String password;
  public static int port;
  
  public PunishDatabase() {}
  
  public static Main plugin = (Main)Main.getPlugin(Main.class);
  
  public static void mysqlSetup() { plugin.getConfig().options().copyDefaults(true);
    plugin.saveConfig();
    database = plugin.getConfig().getString("MYSQL.punishment_data.database");
    host = plugin.getConfig().getString("MYSQL.punishment_data.host");
    port = plugin.getConfig().getInt("MYSQL.punishment_data.port");
    username = plugin.getConfig().getString("MYSQL.punishment_data.username");
    password = plugin.getConfig().getString("MYSQL.punishment_data.password");
    try
    {
      if ((getConnection() != null) && (!getConnection().isClosed())) {
        return;
      }
      

      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
      setConnection(con);
      Statement st = con.createStatement();
      st.executeUpdate("CREATE TABLE IF NOT EXISTS punish_data (CRIMINAL text, PUNISH_OCCURRENCE int, PUNISH_TYPE text, SEVERITY text, PUNISHER text, REASON text, START_DATE text, END_DATE text, REMOVER text, REMOVE_REASON text, REMOVE_DATE text)");
      Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &9Connected> &c&lPUNISHMENT"));
    }
    catch (ClassNotFoundException event) {
      event.printStackTrace();
      Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &cFailed> &c&lPUNISHMENT"));
    }
    catch (SQLException event) {
      event.printStackTrace();
      Bukkit.getConsoleSender().sendMessage(Utils.chat("&b&l[&8&lsql&d&lUV&b&l] &cFailed> &c&lPUNISHMENT"));
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
