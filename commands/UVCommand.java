package me.pavl.ultraviolet.commands;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.listeners.ChatListener;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PrefManager;
import me.pavl.ultraviolet.permissions.Permissions;
import me.pavl.ultraviolet.ui.ReportsUI;
import me.pavl.ultraviolet.ui.UltravioletSelfUI;
import me.pavl.ultraviolet.ui.UltravioletUI;
import me.pavl.ultraviolet.utils.Utils;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UVCommand implements CommandExecutor
{
  @SuppressWarnings("unused")
private Main plugin;
  
  public UVCommand(Main plugin)
  {
    this.plugin = plugin;
    
    plugin.getCommand("uv").setExecutor(this);
  }
  


  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player)) {
      if (args.length == 2) {
    	  if (args[0] == "op") {
    		  OfflinePlayer client = Bukkit.getOfflinePlayer(args[1]);
    		  Player onlinePlayer = Bukkit.getPlayer(args[1]);
              if (onlinePlayer != null) {
                  Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
                  Permissions.setup(onlinePlayer);
                  onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &4&l[OWNER]"));
              }
              PermissionManager.removeUserFromAllGroups(client.getUniqueId());
              PermissionManager.addUserToGroup(client.getUniqueId(), "OWNER", "&4&l[OWNER]");
              }
      }
      return false;
    }
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
    if (args.length == 0) {
      if (s.hasPermission("uv.self")) {
        s.openInventory(UltravioletSelfUI.GUI(s));
      }
      else {
        s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
      }
    }
    else if (args.length == 1) {
      String p = args[0].toLowerCase();
      if (s.getName().toLowerCase().startsWith(p.toLowerCase())) {
        if (s.hasPermission("uv.self")) {
          s.openInventory(UltravioletSelfUI.GUI(s));
          return false;
        }
        else {
          s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
        }
      }
      else if (ChatColor.stripColor(p).startsWith("stfsrv")) {
    	  World stff = Bukkit.getWorld("world");
    	  s.teleport(new Location(stff, 0.5, 105, 0.5));
    	  s.sendMessage(Utils.chat("&b&l[&d&ltp&d&lUV&b&l] You moved to staff."));
      }
      else if (ChatColor.stripColor(p).startsWith("hub")) {
    	  World hub = Bukkit.getWorld("HubWorld");
    	  s.teleport(new Location(hub, -9, 154, -6));
    	  s.sendMessage(Utils.chat("&b&l[&d&ltp&d&lUV&b&l] You moved to hub."));
      }
      else if (ChatColor.stripColor(p).startsWith("ff")) {
          if (s.hasPermission("uv.rank.admin")) {
        	  s.sendMessage(Utils.chat("&b&l[&9&lmove&d&lUV&b&l] &9Correct Usage &b» &a/uv ff &e<radius>"));
            }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            }
      }
      else if (ChatColor.stripColor(p).startsWith("spy")) {
          if (s.hasPermission("uv.rank.admin")) {
        	  if (PrefManager.getSpy(s.getUniqueId()).startsWith("1")) {
        		  PrefManager.setSpy(s.getUniqueId(), 0);
        		  s.sendMessage(Utils.chat("&b&l[&8&lspy&d&lUV&b&l] &9Spy &cDisabled"));
        	  }
        	  else {
        		  PrefManager.setSpy(s.getUniqueId(), 1);
        		  s.sendMessage(Utils.chat("&b&l[&8&lspy&d&lUV&b&l] &9Spy &aEnabled"));
        	  }
            }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            }
      }
      else if (ChatColor.stripColor(p).startsWith("vanish")) {
    	  if (s.hasPermission("uv.rank.admin")) {
    		  for (Player online : Bukkit.getOnlinePlayers()) {
    			  online.hidePlayer(s);
    		  }
    		  s.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&4&l+&b&l] &a» &9Vanished"));
    	  }
    	  else {
    		  s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
    	  }
      }
      else if (ChatColor.stripColor(p).startsWith("unvanish")) {
    	  if (s.hasPermission("uv.rank.admin")) {
    		  for (Player online : Bukkit.getOnlinePlayers()) {
    			  online.showPlayer(s);
    		  }
    		  s.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&4&l+&b&l] &a» &9Unvanished"));
    	  }
    	  else {
    		  s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
    	  }
      }
      else if (ChatColor.stripColor(p).startsWith("chat")) {
    	if (s.hasPermission("uv.rank.admin")) {
            s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Correct Usage &b» &a/uv chat &e<silence &c|&e (seconds) &c|&e enable>"));

    	}
    	else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
    	}
        return false;
      }
      else if (ChatColor.stripColor(p).startsWith("msg") || ChatColor.stripColor(p).startsWith("message") || ChatColor.stripColor(p).startsWith("send")) {
    	if (s.hasPermission("uv.rank.helper")) {
            s.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &9Correct Usage &b» &a/uv msg &e<client> &c<message>"));

    	}
    	else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
    	}
        return false;
      }
      else if (ChatColor.stripColor(p).startsWith("bc")) {
      	if (s.hasPermission("uv.rank.admin")) {
            s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Correct Usage &b» &a/uv &ebc &c<message>"));

    	}
    	else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
    	}
        return false;
      }
      else if (ChatColor.stripColor(p).startsWith("sbc")) {
        	if (s.hasPermission("uv.rank.admin")) {
              s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Correct Usage &b» &a/uv &csbc &e<message>"));

      	}
      	else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
      	}
          return false;
        }
      else if (ChatColor.stripColor(p).startsWith("rank")) {
        if (s.hasPermission("uv.rank.srmod")) {
          s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Correct Usage &b» &a/uv rank &e<client> &c<rank &b|&c remove>"));
        }
        else {
          s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
        }
        
      }
      else if (ChatColor.stripColor(args[0]).startsWith("advisor")) {
          if (s.hasPermission("uv.rank.srmod")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Correct Usage &b» &a/uv advisor &e<client> &c<advisor>"));
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
          }
        }
      else if (ChatColor.stripColor(args[0]).startsWith("tag")) {
          if (s.hasPermission("uv.rank.admin")) {
              s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Correct Usage &b» &a/uv tag &e<client> &c<tag>"));
            }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            } 
      }
      else if (s.hasPermission("uv.others")) {
        s.openInventory(UltravioletUI.GUI(s, p));
      }
      else {
        s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lMOD&c+&f]"));
      }
      
    }
    else if (args.length == 2) {
      if (ChatColor.stripColor(args[0]).startsWith("rank")) {
        if (s.hasPermission("uv.rank.srmod")) {
          s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Correct Usage &b» &a/uv rank &e<client> &c<rank &b|&c remove>"));
        }
        else {
          s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
        }
      }
      else if (ChatColor.stripColor(args[0]).startsWith("msg") || ChatColor.stripColor(args[0]).startsWith("message") || ChatColor.stripColor(args[0]).startsWith("send")) {
    	if (s.hasPermission("uv.rank.helper")) {
            s.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &9Correct Usage &b» &a/uv msg &e<client> &c<message>"));

    	}
    	else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
    	}
      }
      else if (ChatColor.stripColor(args[0]).startsWith("ff")) {
          if (s.hasPermission("uv.rank.admin")) {
        	  if (ChatColor.stripColor(args[1]).startsWith("0")) {
                PrefManager.setForcefield(s.getUniqueId(), -1);
                s.sendMessage(Utils.chat("&b&l[&9&lmove&d&lUV&b&l] &9Forcefield Disabled"));
        	  }
        	  else if (Integer.valueOf(ChatColor.stripColor(args[1])) > 10) {
                  s.sendMessage(Utils.chat("&b&l[&9&lmove&d&lUV&b&l] &9Forcefield cannot be greater than 10 blocks."));
        	  }
        	  else {
                PrefManager.setForcefield(s.getUniqueId(), Integer.valueOf(args[1]));
        	    s.sendMessage(Utils.chat("&b&l[&9&lmove&d&lUV&b&l] &9Forcefield Enabled &b» &aRadius: &c" + args[1] + " &9Blocks"));
        	  }
            }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            }
      }
      else if (ChatColor.stripColor(args[0]).startsWith("advisor")) {
          if (s.hasPermission("uv.rank.srmod")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Correct Usage &b» &a/uv advisor &e<client> &c<advisor>"));
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
          }
        }
      else if (ChatColor.stripColor(args[0]).startsWith("bc")) {
        	if (s.hasPermission("uv.rank.admin")) {
              for (Player b : Bukkit.getOnlinePlayers()) {
                  String broadcastRaw = "";
                  int i = 1;
                  while (i < args.length) {
                    broadcastRaw = broadcastRaw + args[i] + " ";
                    i++;
                  }
                  String broadcastMsg = broadcastRaw.substring(0, broadcastRaw.length() - 1);
            	  b.sendMessage(Utils.chat("&b&l[&4&lalert&d&lUV&b&l] &c" + broadcastMsg));
              }

      	}
      	else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
      	}
          return false;
        }
      else if (ChatColor.stripColor(args[0]).startsWith("sbc")) {
      	if (s.hasPermission("uv.rank.admin")) {
            for (Player b : Bukkit.getOnlinePlayers()) {
                String broadcastRaw = "";
                int i = 1;
                while (i < args.length) {
                  broadcastRaw = broadcastRaw + args[i] + " ";
                  i++;
                }
                String broadcastMsg = broadcastRaw.substring(0, broadcastRaw.length() - 1);
          	  if (b.hasPermission("uv.rank.helper")) {
                  b.sendMessage(Utils.chat("&b&l[&4&lalert&d&lUV&4&l+&b&l] &4" + broadcastMsg));
          	  }
            }

    	}
    	else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
    	}
      }
      else if (ChatColor.stripColor(args[0]).startsWith("tag")) {
          if (s.hasPermission("uv.rank.admin")) {
        	  s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + Bukkit.getOfflinePlayer(args[1]).getName() + "'s Tag &b» " + PermissionManager.getUserTag(Bukkit.getOfflinePlayer(args[1]).getUniqueId())));
              s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Update Client Tag &b» &a/uv tag &e<client> &c<tag>"));
            }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            } 
      }
      else if (ChatColor.stripColor(args[0]).startsWith("chat")) {
    	  if (s.hasPermission("uv.rank.admin")) {
        	  if (ChatColor.stripColor(args[1]).startsWith("silence")) {
                  ChatCooldown.silenceChat(s.getName());
                  ChatListener.reload();
        		  s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Chat &b» &cSilenced"));
        	  }
        	  else if (StringUtils.isNumeric(ChatColor.stripColor(args[1]))){
        		  ChatCooldown.addChatCooldown(Integer.valueOf(args[1]), s.getName());
        		  ChatListener.reload();
        		  s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Chat &b» &cSlowed to a Rate of: &e" + args[1] + " &9Seconds"));
        	  }
        	  else if (ChatColor.stripColor(args[1]).startsWith("enable")) {
        		  ChatCooldown.removeChatCooldown();
        		  ChatListener.reload();
        		  s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Chat &b» &aEnabled"));
        	  }
        	  else {
        		  s.sendMessage(Utils.chat("&b&l[&a&lchat&d&lUV&b&l] &9Correct Usage &b» &a/uv chat &e<silence &b|&e (seconds) &b|&e enable>"));
        	  }
    	  }
    	  else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
    	  }
      }
      else if (ChatColor.stripColor(args[0]).startsWith("reports")) {
    	  s.openInventory(ReportsUI.GUI(s, args[1]));
      }
      else {
    	  if (s.hasPermission("uv.self")) {
    	      s.sendMessage(Utils.chat("&b&l[&d&lUV&b&l] &9Correct Usage &b» &a/uv &e<client>"));
    	  }
      }
    }
    else if (args.length == 3) {
      if (ChatColor.stripColor(args[0]).startsWith("rank")) {
        OfflinePlayer client = Bukkit.getOfflinePlayer(args[1]);
        Player onlinePlayer = Bukkit.getPlayer(args[1]);
        String userStatus = PermissionManager.getUserStatus(client.getUniqueId());
        if (sender.getName().equalsIgnoreCase(args[1])) {
        	if (!(s.hasPermission("uv.rank.owner"))) {
                s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9You cannot modify your own status."));
                return false;
        	}
        }
        if (userStatus.startsWith("ADMINISTRATOR") || userStatus.startsWith("OWNER")) {
        	if (!(s.hasPermission("uv.rank.admin"))) {
                s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
                return false;
        	}
        }
        if (ChatColor.stripColor(args[2]).equalsIgnoreCase("helper")) {
          if (s.hasPermission("uv.rank.srmod")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + client.getName() + ": " + PermissionManager.getUserTag(client.getUniqueId()) + " &a» &f[&3&lHELPER&f]"));
            if (onlinePlayer != null) {
              Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
              Permissions.setup(onlinePlayer);
              onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &f[&3&lHELPER&f]"));
            }
            PermissionManager.removeUserFromAllGroups(client.getUniqueId());
            PermissionManager.addUserToGroup(client.getUniqueId(), "HELPER", "&f[&3&lHELPER&f]");
            s.performCommand("uv advisor " + client.getName() + " " + s.getName());
            s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Use &a/uv advisor &9to update client's advisor."));
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
          }
          
        }
        else if ((ChatColor.stripColor(args[2]).equalsIgnoreCase("mod")) || (ChatColor.stripColor(args[2]).equalsIgnoreCase("moderator"))) {
          if (s.hasPermission("uv.rank.srmod")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + client.getName() + ": " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &f[&6&lMODERATOR&f]"));
            if (onlinePlayer != null) {
              Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
              Permissions.setup(onlinePlayer);
              onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a» &f[&6&lMODERATOR&f]"));
            }
            PermissionManager.removeUserFromAllGroups(client.getUniqueId());
            PermissionManager.addUserToGroup(client.getUniqueId(), "MODERATOR", "&f[&6&lMODERATOR&f]");
            s.performCommand("uv advisor " + client.getName() + " " + s.getName());
            s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Use &a/uv advisor &9to update client's advisor."));
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
          }
          
        }
        else if ((ChatColor.stripColor(args[2]).equalsIgnoreCase("sr.mod")) || (ChatColor.stripColor(args[2]).equalsIgnoreCase("srmod"))) {
          if (s.hasPermission("uv.rank.admin")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + client.getName() + ": " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &f[&e&lSR.MOD&f]"));
            if (onlinePlayer != null) {
              Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
              Permissions.setup(onlinePlayer);
              onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &f[&e&lSR.MOD&f]"));
            }
            PermissionManager.removeUserFromAllGroups(client.getUniqueId());
            PermissionManager.addUserToGroup(client.getUniqueId(), "SENIOR_MODERATOR", "&f[&e&lSR.MOD&f]");
            PermissionManager.assignAdvisorToUser(client.getUniqueId(), s.getName());
  		    s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + client.getName() + "&9's &cAdvisor &b» &e" + s.getName()));
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
          }
        }
        else if ((ChatColor.stripColor(args[2]).equalsIgnoreCase("admin")) || (ChatColor.stripColor(args[2]).equalsIgnoreCase("administrator"))) {
          if (s.hasPermission("uv.rank.admin")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + client.getName() + ": " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &f[&c&lADMIN&f]"));
            if (onlinePlayer != null) {
              Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
              Permissions.setup(onlinePlayer);
              onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &f[&c&lADMIN&f]"));
            }
            PermissionManager.removeUserFromAllGroups(client.getUniqueId());
            PermissionManager.addUserToGroup(client.getUniqueId(), "ADMINISTRATOR", "&f[&c&lADMIN&f]");
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
          }
        }
        else if ((ChatColor.stripColor(args[2]).equalsIgnoreCase("owner"))) {
          if (s.hasPermission("uv.rank.owner")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + client.getName() + ": " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &4&l[OWNER]"));
            if (onlinePlayer != null) {
              Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
              Permissions.setup(onlinePlayer);
              onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a&a&a» &4&l[OWNER]"));
            }
            PermissionManager.removeUserFromAllGroups(client.getUniqueId());
            PermissionManager.addUserToGroup(client.getUniqueId(), "OWNER", "&4&l[OWNER]");
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&4OWNER&c&l+&f]"));
          }
        }
        else if (ChatColor.stripColor(args[2]).equalsIgnoreCase("remove")) {
          if (s.hasPermission("uv.rank.admin")) {
            s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &a" + client.getName() + ": " + PermissionManager.getUserTag(client.getUniqueId()) + " &a» &7[Member]"));
            if (onlinePlayer != null) {
              onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Rank Updated: " + PermissionManager.getUserTag(client.getUniqueId()) + " &a» &7[Member]"));
              Permissions.clientPermissions.remove(onlinePlayer.getUniqueId());
              Permissions.setup(onlinePlayer);
            }
            PermissionManager.removeUserFromAllGroups(client.getUniqueId());
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
          }
          
        }
        else if (s.hasPermission("uv.rank.owner")) {
          s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Valid Ranks &b» &4OWNER&9, &cADMIN&9, &6SR.MOD&9, &6MOD&9, &3HELPER&9."));
        }
        else if (s.hasPermission("uv.rank.admin")) {
          s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Valid Ranks &b» &cADMIN&9, &6SR.MOD&9, &6MOD&9, &3HELPER&9."));
        }
        else if (s.hasPermission("uv.rank.srmod")) {
          s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Valid Ranks &b» &6MOD&9, &3HELPER&9."));
        }
        else {
          s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));

        }
        
      }
      else if (ChatColor.stripColor(args[0]).startsWith("bc")) {
        	if (s.hasPermission("uv.rank.admin")) {
              for (Player b : Bukkit.getOnlinePlayers()) {
                  String broadcastRaw = "";
                  int i = 1;
                  while (i < args.length) {
                    broadcastRaw = broadcastRaw + args[i] + " ";
                    i++;
                  }
                  String broadcastMsg = broadcastRaw.substring(0, broadcastRaw.length() - 1);
            	  b.sendMessage(Utils.chat("&b&l[&4&lalert&d&lUV&b&l] &c" + broadcastMsg));
              }
      	}
        	else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
        	}
        }
      else if (ChatColor.stripColor(args[0]).startsWith("ff")) {
          if (s.hasPermission("uv.rank.admin")) {
        	  s.sendMessage(Utils.chat("&b&l[&9&lmove&d&lUV&b&l] &9Correct Usage &b» &a/uv ff &e<radius>"));
            }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            }
      }
      else if (ChatColor.stripColor(args[0]).startsWith("msg") || ChatColor.stripColor(args[0]).startsWith("message") || ChatColor.stripColor(args[0]).startsWith("send")) {
          Player client = Bukkit.getPlayer(args[1]);
    	  if (s.hasPermission("uv.rank.helper")) {
    		if (client != null) {
                String msg = "";
                int i = 2;
                while (i < args.length) {
                  msg = msg + args[i] + " ";
                  i++;
                }
                String completeMsg = msg.substring(0, msg.length() - 1);
                client.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &c&lStaff &4" + s.getName() + " &a» &3" + completeMsg));
                s.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &9To " + client.getName() + " &a» &3" + completeMsg));
    		}
    		else {
    			s.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &e[" + args[1] + "] &9is not currently &aonline&9."));
    		}
    	}
      }
      else if (ChatColor.stripColor(args[0]).startsWith("sbc")) {
        	if (s.hasPermission("uv.rank.admin")) {
              for (Player b : Bukkit.getOnlinePlayers()) {
                  String broadcastRaw = "";
                  int i = 1;
                  while (i < args.length) {
                    broadcastRaw = broadcastRaw + args[i] + " ";
                    i++;
                  }
                  String broadcastMsg = broadcastRaw.substring(0, broadcastRaw.length() - 1);
            	  if (b.hasPermission("uv.rank.helper")) {
                    b.sendMessage(Utils.chat("&b&l[&4&lalert&d&lUV&4&l+&b&l] &4" + broadcastMsg));
            	  }
              }

      	}
      	else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
      	}
        }
      else if (ChatColor.stripColor(args[0]).startsWith("advisor")) {
          OfflinePlayer currentUser = Bukkit.getOfflinePlayer(args[1]);
          Player currentOnlineUser = Bukkit.getPlayer(args[1]);
          OfflinePlayer newAdvisor = Bukkit.getOfflinePlayer(args[2]);
    	  String userStatus = PermissionManager.getUserStatus(currentUser.getUniqueId());
    	  String userAdvisor = PermissionManager.getAdvisor(currentUser.getUniqueId());
    	  if (s.hasPermission("uv.rank.srmod")) {
        	  if (PermissionManager.getUserStatus(newAdvisor.getUniqueId()).startsWith("OWNER") || PermissionManager.getUserStatus(newAdvisor.getUniqueId()).startsWith("ADMINISTRATOR") || PermissionManager.getUserStatus(newAdvisor.getUniqueId()).startsWith("SENIOR_MODERATOR")) {
        		  if (currentUser.getName() == s.getName()) {
            		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9You cannot assign yourself an &cadvisor&9."));
            	  }
            	  else if (ChatColor.stripColor(args[1]).equalsIgnoreCase(ChatColor.stripColor(args[2]))){
            		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9You cannot assign a client as their own &cadvisor&9."));
            	  }
            	  else if ((userStatus.startsWith("ADMINISTRATOR") || userStatus.startsWith("OWNER"))) { // BE SURE TO ADD PREMIUM RANKS IN THIS
            		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser.getName() + " &9cannot be assigned an &cadvisor&9."));
            	  }
            	  else if ((userStatus == "")) {
            		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser.getName() + " &9must be &7[&3HELPER&7&c+&7] &9to be assigned an &cadvisor&9."));
            	  }
            	  else if (userStatus.startsWith("SENIOR_MODERATOR")) {
            		  if (s.hasPermission("uv.rank.admin")) {
                		  if (userAdvisor == "") {
                			  PermissionManager.assignAdvisorToUser(currentUser.getUniqueId(), Bukkit.getOfflinePlayer(args[2]).getName());
                    		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser.getName() + "&9's &cAdvisor &b» &e" + Bukkit.getOfflinePlayer(args[2]).getName()));
                    		  if (currentOnlineUser != null) {
                    			  currentOnlineUser.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Your &cAdvisor &b» &e" + Bukkit.getOfflinePlayer(args[2]).getName()));
                    		  }
                		  }
                		  else {
                			  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser.getName() + "&9's &cAdvisor&b: &e" + PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) + " &a» &c" + Bukkit.getOfflinePlayer(args[2]).getName()));
                			  PermissionManager.assignAdvisorToUser(currentUser.getUniqueId(), Bukkit.getOfflinePlayer(args[2]).getName());
                    		  if (currentOnlineUser != null) {
                    			  currentOnlineUser.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Your &cAdvisor &b» &e" + Bukkit.getOfflinePlayer(args[2]).getName()));
                    		  }
                		  }
            		  }
            		  else {
            			  s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            		  }
            	  }
            	  else if (userStatus.startsWith("MODERATOR") || userStatus.startsWith("HELPER")){
            		  if (userAdvisor == "") {
            			  PermissionManager.assignAdvisorToUser(currentUser.getUniqueId(), Bukkit.getOfflinePlayer(args[2]).getName());
                		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser.getName() + "&9's &cAdvisor &b» &e" + Bukkit.getOfflinePlayer(args[2]).getName()));
                		  if (currentOnlineUser != null) {
                			  currentOnlineUser.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Your &cAdvisor &b» &e" + Bukkit.getOfflinePlayer(args[2]).getName()));
                		  }
            		  }
            		  else {
            			  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser.getName() + "&9's &cAdvisor&b: &e" + PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) + " &a» &c" + Bukkit.getOfflinePlayer(args[2]).getName()));
            			  PermissionManager.assignAdvisorToUser(currentUser.getUniqueId(), Bukkit.getOfflinePlayer(args[2]).getName());
                		  if (currentOnlineUser != null) {
                			  currentOnlineUser.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &9Your &cAdvisor &b» &e" + Bukkit.getOfflinePlayer(args[2]).getName()));
                		  }
            		  }
            	  }
        	  }
        	  else {
        		  s.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &c" + newAdvisor.getName() + " &9is not &f[&e&lSR.MOD&c+&f]&9."));
        	  }
    	  }
    	  else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&e&lSR.MOD&c+&f]"));
    	  }
      }
      else if (ChatColor.stripColor(args[0]).startsWith("tag")) {
          if (s.hasPermission("uv.rank.admin")) {
        	  s.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9" + Bukkit.getOfflinePlayer(args[1]).getName() + "'s Tag: " + PermissionManager.getUserTag(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) + " &a» " + Utils.chat(args[2])));
        	  PermissionManager.editUserTag(Bukkit.getOfflinePlayer(args[1]).getUniqueId(), args[2]);
              Player onlinePlayer = Bukkit.getPlayer(args[1]);
        	  if (onlinePlayer != null) {
                  onlinePlayer.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Tag Updated: " + PermissionManager.getUserTag(Bukkit.getOfflinePlayer(args[1]).getUniqueId()) + " &a» " + Utils.chat(args[2])));
            
        	  }
          }
            else {
              s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
            } 
      }

    else if (s.hasPermission("uv.self")) {
      sender.sendMessage(Utils.chat("&b&l[&d&lUV&b&l] &9Correct Usage: &a/uv &e<client>"));
    }
    else {
      s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
    }
    
  }
    else if (args.length > 3) {
        if (ChatColor.stripColor(args[0]).startsWith("bc")) {
          	if (s.hasPermission("uv.rank.admin")) {
                for (Player b : Bukkit.getOnlinePlayers()) {
                    String broadcastRaw = "";
                    int i = 1;
                    while (i < args.length) {
                      broadcastRaw = broadcastRaw + args[i] + " ";
                      i++;
                    }
                    String broadcastMsg = broadcastRaw.substring(0, broadcastRaw.length() - 1);
              	  b.sendMessage(Utils.chat("&b&l[&4&lalert&d&lUV&b&l] &c" + broadcastMsg));
                }
        	}
          	else {
                s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
          	}
          }
        else if (ChatColor.stripColor(args[0]).startsWith("ff")) {
            if (s.hasPermission("uv.rank.admin")) {
          	  s.sendMessage(Utils.chat("&b&l[&9&lmove&d&lUV&b&l] &9Correct Usage &b» &a/uv ff &e<radius>"));
              }
              else {
                s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
              }
        }
        else if (ChatColor.stripColor(args[0]).startsWith("msg") || ChatColor.stripColor(args[0]).startsWith("message") || ChatColor.stripColor(args[0]).startsWith("send")) {
            Player client = Bukkit.getPlayer(args[1]);
      	  if (s.hasPermission("uv.rank.helper")) {
      		if (client != null) {
                  String msg = "";
                  int i = 2;
                  while (i < args.length) {
                    msg = msg + args[i] + " ";
                    i++;
                  }
                  String completeMsg = msg.substring(0, msg.length() - 1);
                  client.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &c&lStaff &4" + s.getName() + " &a» &3" + completeMsg));
                  s.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &9To " + client.getName() + " &a» &3" + completeMsg));
      		}
      		else {
      			s.sendMessage(Utils.chat("&b&l[&a&lmsg&d&lUV&b&l] &e[" + args[1] + "] &9is not currently &aonline&9."));
      		}
      	}
        }
        else if (ChatColor.stripColor(args[0]).startsWith("sbc")) {
          	if (s.hasPermission("uv.rank.admin")) {
                for (Player b : Bukkit.getOnlinePlayers()) {
                    String broadcastRaw = "";
                    int i = 1;
                    while (i < args.length) {
                      broadcastRaw = broadcastRaw + args[i] + " ";
                      i++;
                    }
                    String broadcastMsg = broadcastRaw.substring(0, broadcastRaw.length() - 1);
              	  if (b.hasPermission("uv.rank.helper")) {
                      b.sendMessage(Utils.chat("&b&l[&4&lalert&d&lUV&4&l+&b&l] &4" + broadcastMsg));
              	  }
                }

        	}
        	else {
                s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &f[&cADMIN&c&l+&f]"));
        	}
            return false;
          }
        else if (s.hasPermission("uv.self")) {
            sender.sendMessage(Utils.chat("&b&l[&d&lUV&b&l] &9Correct Usage: &a/uv &e<client>"));
          }
          else {
            s.sendMessage(Utils.chat("&b&l[&c&laccess&d&lUV&b&l] &9Invalid Permissions &b» &7[&3HELPER&7&c+&7]"));
          }
    }
    return false;
}
}
