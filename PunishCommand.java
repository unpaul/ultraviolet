package me.pavl.ultraviolet.commands;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.ui.PunishUI;
import me.pavl.ultraviolet.utils.Utils;

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
    if (args.length >= 2) {
      if (!(sender instanceof Player)) {
        return true;
      }
      Player s = (Player)sender;
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
          s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9You may not use &b[FR] &9or &b[SR] &9in your punishment reason."));
        }
      }
      else {
        s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9You must be &7[&3HELPER&7&c+&7] &9to use that command."));
      }
      
    }
    else if (sender.hasPermission("uv.punish")) {
      sender.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &eCorrect Usage: &a/punish|p <player name> <reason>"));
    }
    else {
      sender.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9You must be &7[&3HELPER&7&c+&7] &9to use that command."));
    }
    
    return false;
  }
}