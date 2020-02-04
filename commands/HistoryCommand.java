package me.pavl.ultraviolet.commands;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PrefManager;
import me.pavl.ultraviolet.ui.HistoryUI;
import me.pavl.ultraviolet.utils.Utils;

import org.bukkit.Bukkit;
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
      Player s = (Player)sender;
      StringBuilder cmdd = new StringBuilder();
      for (String item : args) {
	    cmdd.append(" ");
		cmdd.append(item);
      }
      for (Player ss : Bukkit.getServer().getOnlinePlayers()) {
      	if (PrefManager.getSpy(ss.getUniqueId()).startsWith("1")) {
      		if ((s != ss) && !(s.hasPermission("uv.rank.admin"))) {
      			if (s.hasPermission("uv.rank.helper")) {
          			ss.sendMessage(Utils.chat("&b&l[&8&lspy&d&lUV&b&l] &c&l[Staff] &9" + s.getName() + "&9: &a/" + cmd.getName() + "&b" + cmdd));
      			}
      			else {
          			ss.sendMessage(Utils.chat("&b&l[&8&lspy&d&lUV&b&l] &a&l[Player] &9" + s.getName() + "&9: &a/" + cmd.getName() + "&b" + cmdd));
      			}
      		}
      	}
      }
    if (args.length >= 1) {
      if (!(sender instanceof Player)) {
        return true;
      }
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
        s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
      }
      
    }
    else if (sender.hasPermission("uv.punish")) {
      sender.sendMessage(Utils.chat("&b&l[&e&lhistory&d&lUV&b&l] &9Correct Usage &b» &a/history &e<client> &c<remove reason>"));
    }
    else {
      sender.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
    }
    
    return false;
  }
}