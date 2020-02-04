package me.pavl.ultraviolet.commands;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PrefManager;
import me.pavl.ultraviolet.ui.PunishUI;
import me.pavl.ultraviolet.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunishCommand implements CommandExecutor
{
  @SuppressWarnings("unused")
private Main plugin;
  
  public PunishCommand(Main plugin)
  {
    this.plugin = plugin;
    
    plugin.getCommand("punish").setExecutor(this);
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
    if (args.length >= 2) {
      if (!(sender instanceof Player)) {
        return true;
      }
      String p = args[0];
      String punishReasonRaw = "";
      int i = 1;
      while (i < args.length) {
        punishReasonRaw = punishReasonRaw + args[i] + " ";
        i++;
      }
      String punishReason = punishReasonRaw.substring(0, punishReasonRaw.length() - 1);
      if (s.hasPermission("uv.punish")) {
        if (((punishReason.contains("[SR]") ? 0 : 1) & (punishReason.contains("[FR]") ? 0 : 1)) != 0) {
          s.openInventory(PunishUI.GUI(s, p, punishReason));
        }
        else {
          s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Reason &b» &5[FR] &9&l: &3[SR]"));
        }
      }
      else {
        s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
      }
      
    }
    else if (sender.hasPermission("uv.punish")) {
      sender.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9Correct Usage &b» &a/punish &e<client> &c<reason>"));
    }
    else {
      sender.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
    }
    
    return false;
  }
}