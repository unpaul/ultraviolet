package me.pavl.ultraviolet.commands;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.ui.HistoryUI;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HistoryCommand implements CommandExecutor
{
  @SuppressWarnings("unused")
private Main plugin;
  
  public HistoryCommand(Main plugin)
  {
    this.plugin = plugin;
    
    plugin.getCommand("history").setExecutor(this);
  }
  

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (args.length >= 1) {
      if (!(sender instanceof Player)) {
        return true;
      }
      Player s = (Player)sender;
      String p = args[0];
      if (s.hasPermission("uv.punish")) {
    	if (args.length == 1) {
            s.openInventory(HistoryUI.GUI(s, p, " "));
    	}
    	else if (args.length >= 2) {
            s.openInventory(HistoryUI.GUI(s, p, args[1]));
    	}
      }
      else {
        s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9You must be &7[&3HELPER&7&c+&7] &9to use that command."));
      }
      
    }
    else if (sender.hasPermission("uv.punish")) {
      sender.sendMessage(Utils.chat("&b&l[&e&lhistory&d&lUV&b&l] &9Correct Usage: &a/history <player name> &c<remove reason>"));
    }
    else {
      sender.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9You must be &7[&3HELPER&7&c+&7] &9to use that command."));
    }
    
    return false;
  }
}