package me.pavl.ultraviolet;

import me.pavl.ultraviolet.commands.HistoryCommand;
import me.pavl.ultraviolet.commands.PunishCommand;
import me.pavl.ultraviolet.commands.UVCommand;
import me.pavl.ultraviolet.listeners.ChatListener;
import me.pavl.ultraviolet.listeners.InventoryClickListener;
import me.pavl.ultraviolet.listeners.PlayerJoinListener;
import me.pavl.ultraviolet.listeners.PlayerLeaveListener;
import me.pavl.ultraviolet.listeners.PlayerLoginListener;
import me.pavl.ultraviolet.mysql.PermissionDatabase;
import me.pavl.ultraviolet.mysql.PunishDatabase;
import me.pavl.ultraviolet.ui.HistoryUI;
import me.pavl.ultraviolet.ui.MacroUI;
import me.pavl.ultraviolet.ui.PunishUI;
import me.pavl.ultraviolet.ui.ReportsUI;
import me.pavl.ultraviolet.ui.StatusUI;
import me.pavl.ultraviolet.ui.UltravioletSelfUI;
import me.pavl.ultraviolet.ui.UltravioletUI;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
  public void onEnable()
  {
    getConfig().options().copyDefaults();
    saveDefaultConfig();
    new UVCommand(this);
    new HistoryCommand(this);
    new PunishCommand(this);
    new InventoryClickListener(this);
    new PlayerJoinListener(this);
    new PlayerLeaveListener(this);
    new ChatListener(this);
    new PlayerLoginListener(this);
    StatusUI.initialize();
    PunishUI.initialize();
    UltravioletUI.initialize();
    UltravioletSelfUI.initialize();
    HistoryUI.initialize();
    ReportsUI.initialize();
    MacroUI.initialize();
    PermissionDatabase.mysqlSetup();
    PunishDatabase.mysqlSetup();
  }
}
