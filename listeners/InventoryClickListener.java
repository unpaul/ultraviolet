package me.pavl.ultraviolet.listeners;

import java.util.UUID;
import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.punishments.Punishments;
import me.pavl.ultraviolet.ui.AdvisorUI;
import me.pavl.ultraviolet.ui.HistoryUI;
import me.pavl.ultraviolet.ui.MacroUI;
import me.pavl.ultraviolet.ui.PunishUI;
import me.pavl.ultraviolet.ui.StatusUI;
import me.pavl.ultraviolet.ui.UVServerUI;
import me.pavl.ultraviolet.ui.UltravioletSelfUI;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements org.bukkit.event.Listener
{
  @SuppressWarnings("unused")
private Main plugin;
  private String label = "";
  @SuppressWarnings("unused")
private boolean hasLabel = false;
  
  public InventoryClickListener(Main plugin) { this.plugin = plugin;
    
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }
  
  @SuppressWarnings("deprecation")
@org.bukkit.event.EventHandler
  public void onInventoryClick(InventoryClickEvent event)
  {
    Main plugin = (Main)Main.getPlugin(Main.class);
    Player player = (Player)event.getWhoClicked();
    Player criminalPlayer = Bukkit.getPlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"));
    OfflinePlayer criminalOfflinePlayer = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"));
    if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta())) {
      return;
    }
    else if ((ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("punishUV")) || (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("punishUV+"))) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
      case REDSTONE_BLOCK: 
        Punishments.issueNetworkBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + label);
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Network Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        
        label = "";
        return;
      case GREEN_RECORD: 
        Punishments.issueSev1Mute(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Muted &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        if (criminalPlayer != null) {
          criminalPlayer.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &6muted &9by &c" + player.getName() + " &9for &5" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + " &9until &d" + PunishManager.getCurrentMuteEndDate(criminalOfflinePlayer.getUniqueId())));
        }
        player.closeInventory();
        return;
      case GOLD_RECORD: 
        Punishments.issueSev2Mute(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Muted &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        if (criminalPlayer != null) {
          criminalPlayer.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &6muted &9by &c" + player.getName() + " &9for &5" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + "&9until &d" + PunishManager.getCurrentMuteEndDate(criminalOfflinePlayer.getUniqueId())));
        }
        player.closeInventory();
        return;
      case RECORD_4: 
        Punishments.issueSev3Mute(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Muted &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        if (criminalPlayer != null) {
          criminalPlayer.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &6muted &9by &c" + player.getName() + " &9for &5" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + "&9until &d" + PunishManager.getCurrentMuteEndDate(criminalOfflinePlayer.getUniqueId())));
        }
        player.closeInventory();
        return;
      case STAINED_GLASS_PANE: 
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("1")) {
          Punishments.issueSev1GameplayBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
          player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
          label = "";
          player.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("2")) {
          Punishments.issueSev2GameplayBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
          player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
          label = "";
          player.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("3")) {
          Punishments.issueSev3GameplayBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
          player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
          label = "";
          player.closeInventory();
          return;
        }
      case GOLD_BOOTS: 
        Punishments.issueSev1HackingBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        player.closeInventory();
        return;
      case IRON_BOOTS: 
        Punishments.issueSev2HackingBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        player.closeInventory();
        return;
      case DIAMOND_BOOTS: 
        Punishments.issueSev3HackingBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        player.closeInventory();
        return;
      case GLOWSTONE_DUST: 
        Punishments.issuePermanentMute(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Muted &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        if (criminalPlayer != null) {
          criminalPlayer.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &6muted &9by &c" + player.getName() + " &9for &5" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + "&9until &d" + PunishManager.getCurrentMuteEndDate(criminalOfflinePlayer.getUniqueId())));
        }
        player.closeInventory();
        return;
      case REDSTONE: 
        Punishments.issuePermanentBan(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Banned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        player.closeInventory();
        return;
      case FEATHER: 
        Punishments.issueWarning(player.getName(), Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason"));
        player.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &6Warned &b» &5" + Bukkit.getOfflinePlayer(plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishee").toString())).getName() + " &b(" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + ")"));
        label = "";
        if (criminalPlayer != null) {
          criminalPlayer.sendMessage(Utils.chat("&b&l[&c&lpunish&d&lUV&b&l] &9You were &3warned &9by &c" + player.getName() + " &9for &5" + plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString())));
        }
        player.closeInventory();
        return;
      case TORCH: 
        label = " [SR]";
        hasLabel = true;
        Inventory toReturnSR = PunishUI.GUI(player, plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"), plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + label);
        toReturnSR.remove(Material.TORCH);
        toReturnSR.remove(Material.REDSTONE_TORCH_ON);
        player.openInventory(toReturnSR);
        return;
      case REDSTONE_TORCH_ON: 
        label = " [FR]";
        hasLabel = true;
        Inventory toReturnFR = PunishUI.GUI(player, plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"), plugin.getConfig().getString(new StringBuilder().append(player.getUniqueId()).append(".lastPunishReason").toString()) + label);
        toReturnFR.remove(Material.TORCH);
        toReturnFR.remove(Material.REDSTONE_TORCH_ON);
        player.openInventory(toReturnFR);
        return;
      case SKULL_ITEM: 
        String currentUser = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        player.performCommand("uv " + currentUser);
        return;
      case BOOK: 
        player.openInventory(HistoryUI.GUI(player, plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"), plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason") + label));
        return;
      case SIGN:
    	  currentUser = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).replace("Report ", "").replace(" as an Inappropriate Name", "");
    	  PunishManager.addInappropriateNameReport(currentUser, player.getName());
    	  player.sendMessage(Utils.chat("&b&l[&9&lreports&d&lUV&b&l] &9Inappropriate Name &3Reported &b»&c " + currentUser));
    	  player.closeInventory();
    	  return;
      default:
    	  return;
      }
      
    }
    else if ((ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("historyUV")) || (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("historyUV+"))) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
      case BOOK_AND_QUILL: 
        UUID punisher = player.getUniqueId();
        UUID criminal = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")).getUniqueId();
        String reason = plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason");
        String punishmentDate = ChatColor.stripColor(((String)event.getInventory().getItem(event.getSlot()).getItemMeta().getLore().get(3)).replace("Date: ", ""));
        PunishManager.removePunishment(criminal, punishmentDate, punisher, reason, Utils.getCurrentDate());
        event.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
        return;
      case PAPER: 
        return;
      case REDSTONE_BLOCK: 
        punisher = player.getUniqueId();
        criminal = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")).getUniqueId();
        reason = plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason");
        punishmentDate = ChatColor.stripColor(((String)event.getInventory().getItem(event.getSlot()).getItemMeta().getLore().get(2)).replace("Date: ", ""));
        PunishManager.removePunishment(criminal, punishmentDate, punisher, reason, Utils.getCurrentDate());
        event.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
        return;
      case MAP: 
        punisher = player.getUniqueId();
        criminal = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")).getUniqueId();
        reason = plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason");
        punishmentDate = ChatColor.stripColor(((String)event.getInventory().getItem(event.getSlot()).getItemMeta().getLore().get(3)).replace("Date: ", ""));
        PunishManager.removePunishment(criminal, punishmentDate, punisher, reason, Utils.getCurrentDate());
        event.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
        return;
      case DIAMOND_SWORD: 
        punisher = player.getUniqueId();
        criminal = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")).getUniqueId();
        reason = plugin.getConfig().getString(player.getUniqueId() + ".lastPunishReason");
        punishmentDate = ChatColor.stripColor(((String)event.getInventory().getItem(event.getSlot()).getItemMeta().getLore().get(3)).replace("Date: ", ""));
        PunishManager.removePunishment(criminal, punishmentDate, punisher, reason, Utils.getCurrentDate());
        event.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
        return;
      case BEDROCK: 
        String punishee = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")).getName();
        PunishManager.clearPunishmentHistory(Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee")).getUniqueId());
        player.sendMessage(Utils.chat("&b&l[&e&lhistory&d&lUV&b&l] &9Cleared &b» &5" + punishee));
        if (criminalPlayer != null) {
          criminalPlayer.sendMessage(Utils.chat("&b&l[&e&lhistory&d&lUV&b&l] &9Your &cpunishment history &9was &acleared &9by &c" + player.getName()));
        }
        return;
      case SKULL_ITEM: 
        String currentUser = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        player.performCommand("uv " + currentUser);
        return;
      default:
    	  return;
      }
      
    }
    else if ((ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("myUV")) || (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("myUV+"))) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
      case SKULL_ITEM: 
        String currentUser = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        player.performCommand("uv " + currentUser);
        return;
      case BOOK: 
        player.openInventory(HistoryUI.GUI(player, plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"), " "));
        return;
      case FLOWER_POT_ITEM:
    	player.openInventory(AdvisorUI.GUI(player, "main"));
    	return;
      case WATER_BUCKET:
    	player.performCommand("uv vanish");
    	player.closeInventory();
    	return;
      case LAVA_BUCKET:
    	player.performCommand("uv unvanish");
    	player.closeInventory();
    	return;
      case JACK_O_LANTERN:
    	player.performCommand("uv reports InappName");
    	return;
      case ANVIL:
    	player.openInventory(UVServerUI.GUI(player));
    	return;
      case EXP_BOTTLE:
        if (player.getGameMode().getValue() == 1) {
          player.setGameMode(GameMode.valueOf("SURVIVAL"));
          player.closeInventory();
          player.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &9Updated Gamemode &b» &4Survival"));
        }
        else if (player.getGameMode().getValue() == 0) {
            player.setGameMode(GameMode.valueOf("CREATIVE"));
            player.closeInventory();
            player.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &9Updated Gamemode &b» &aCreative"));
          }
        return;
      case DRAGON_EGG:
    	  criminalPlayer.getInventory().setContents(new ItemStack[3]);
    	  criminalPlayer.updateInventory();
    	  player.closeInventory();
    	  player.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &a» Inventory Cleared"));
    	  return;
      case BREAD:
    	  player.setHealth(20);
    	  player.setFoodLevel(20);
    	  player.closeInventory();
    	  criminalPlayer.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &a» Healed"));
    	  return;
      default:
    	  return;
      }
      
    }
    else if ((ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("clientUV")) || (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("clientUV+"))) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
      case SKULL_ITEM: 
        String currentUser = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        player.performCommand("uv " + currentUser);
        return;
      case GLASS_BOTTLE:
    	  currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
    	  if (Bukkit.getPlayer(currentUser) != null) {
    		  Location playerLoc = Bukkit.getPlayer(currentUser).getLocation();
    		  player.closeInventory();
    		  player.teleport(playerLoc);
    		  player.sendMessage(Utils.chat("&b&l[&d&lUV&b&l] &9Teleported &b» " + Bukkit.getPlayer(currentUser).getName()));
    	  }
    	  return;
      case PAINTING:
    	  currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
    	  if (Bukkit.getPlayer(currentUser) != null) {
    		  Inventory playerInv = Bukkit.getPlayer(currentUser).getInventory();
    		  player.openInventory(playerInv);
    	  }
    	  return;
      case IRON_FENCE:
    	  currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
    	  player.performCommand("punish " + currentUser + " .");
    	  return;
      case BOOK: 
        player.openInventory(HistoryUI.GUI(player, plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"), " "));
        return;
      case BEACON: 
        if ((player.hasPermission("uv.rank.srmod")) || (player.hasPermission("uv.rank.admin")) || (player.hasPermission("uv.rank.owner"))) {
          player.openInventory(StatusUI.GUI(player, ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "")));
        }
        return;
      case FIREBALL:
    	  player.openInventory(MacroUI.GUI(player, ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "")));
    	  return;
      case FLOWER_POT_ITEM:
          currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
    	  String userStatus = PermissionManager.getUserStatus(Bukkit.getOfflinePlayer(currentUser).getUniqueId());
    	  String userAdvisor = PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(currentUser).getUniqueId());
    	  if (event.getCurrentItem().getItemMeta().getLore().get(1).contains("Click to Open")) {
    		  player.openInventory(AdvisorUI.GUI(player, currentUser));
    		  return;
    	  }
    	  else {
        	  if ((userStatus == "ADMIN" || userStatus == "OWNER")) { // BE SURE TO ADD PREMIUM RANKS IN THIS
        		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + " &9cannot be assigned a &cadvisor&9."));
        	  }
        	  else if ((userStatus == "")) {
        		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + " &9must be &7[&3HELPER&7&c+&7] &9to be assigned a &csupervisor&9."));
        	  }
        	  else if (userStatus == "SENIOR_MODERATOR"){
        		  if (player.hasPermission("uv.rank.admin")) {
            		  if (userAdvisor == "") {
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
                		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + player.getName()));
            		  }
            		  else {
            			  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(currentUser).getUniqueId()) + " &a» &c" + player.getName()));
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
            		  }
        		  }
        	  }
        	  else {
           		  if (player.hasPermission("uv.rank.srmod")) {
            		  if (userAdvisor == "") {
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
                		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + player.getName()));
            		  }
            		  else {
            			  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(currentUser).getUniqueId()) + " &a» &c" + player.getName()));
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
            		  }
        		  }  
        	  }
    	  }
          player.closeInventory();
          return;
      case EXP_BOTTLE:
          if (criminalPlayer.getGameMode().getValue() == 1) {
            criminalPlayer.setGameMode(GameMode.valueOf("SURVIVAL"));
            player.closeInventory();
            player.sendMessage(Utils.chat("&b&l[&a&lclient&d&lUV&b&l] &9Updated &b" + criminalPlayer.getName() + "&9's Gamemode &b» &4Survival"));
            criminalPlayer.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &9Updated Gamemode &b» &4Survival"));
          }
          else if (criminalPlayer.getGameMode().getValue() == 0) {
              criminalPlayer.setGameMode(GameMode.valueOf("CREATIVE"));
              player.closeInventory();
              player.sendMessage(Utils.chat("&b&l[&a&lclient&d&lUV&b&l] &9Updated &b" + criminalPlayer.getName() + "&9's Gamemode &b» &aCreative"));
              criminalPlayer.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &9Updated Gamemode &b» &aCreative"));
            }
          return;
      case DRAGON_EGG:
    	  criminalPlayer.getInventory().setContents(new ItemStack[3]);
    	  criminalPlayer.updateInventory();
    	  player.closeInventory();
    	  player.sendMessage(Utils.chat("&b&l[&a&lclient&d&lUV&b&l] &9Cleared &b" + criminalPlayer.getName() + "&9's Inventory"));
    	  criminalPlayer.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &b» Inventory Cleared"));
    	  return;
      case BREAD:
    	  criminalPlayer.setHealth(20);
    	  criminalPlayer.setExhaustion(0);
    	  player.closeInventory();
    	  player.sendMessage(Utils.chat("&b&l[&a&lclient&d&lUV&b&l] &9Healed &b" + criminalPlayer.getName() + "&9."));
    	  criminalPlayer.sendMessage(Utils.chat("&b&l[&a&lmy&d&lUV&b&l] &b» &aHealed"));
    	  return;
      default:
    	  return;
      }
      
    }
    else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("statusUV")) {
      event.setCancelled(true);
      String currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
      switch (event.getCurrentItem().getType()) {
      case SKULL_ITEM: 
        String uvUser = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        player.performCommand("uv " + uvUser);
        return;
      case BOOK: 
        player.openInventory(HistoryUI.GUI(player, plugin.getConfig().getString(player.getUniqueId() + ".lastPunishee"), ""));
      case STAINED_GLASS: 
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("HELPER")) {
          player.performCommand("uv rank " + currentUser + " helper");
          player.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("SR MODERATOR")) {
          player.performCommand("uv rank " + currentUser + " sr.mod");
          player.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("MODERATOR")) {
          player.performCommand("uv rank " + currentUser + " mod");
          player.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("ADMIN")) {
          player.performCommand("uv rank " + currentUser + " admin");
          player.closeInventory();
          return;
        }
        if (!event.getCurrentItem().getItemMeta().getDisplayName().contains("OWNER")) break;
        player.performCommand("uv rank " + currentUser + " owner");
        player.closeInventory();
        return;
      case WOOL:
    	  if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Member")) {
              player.performCommand("uv rank " + currentUser + " remove");
              player.closeInventory();
              return;
    	  }
      case FLOWER_POT_ITEM:
          currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
    	  String userStatus = PermissionManager.getUserStatus(Bukkit.getOfflinePlayer(currentUser).getUniqueId());
    	  String userAdvisor = PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(currentUser).getUniqueId());
    	  if (event.getCurrentItem().getItemMeta().getLore().get(1).contains("Click to Open")) {
    		  player.openInventory(AdvisorUI.GUI(player, currentUser));
    		  return;
    	  }
    	  else {
        	  if ((userStatus == "ADMIN" || userStatus == "OWNER")) { // BE SURE TO ADD PREMIUM RANKS IN THIS
        		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + " &9cannot be assigned an &cadvisor&9."));
        	  }
        	  else if ((userStatus == "")) {
        		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + " &9must be &7[&3HELPER&7&c+&7] &9to be assigned an &cadvisor&9."));
        	  }
        	  else if (userStatus == "SENIOR_MODERATOR"){
        		  if (player.hasPermission("uv.rank.admin")) {
            		  if (userAdvisor == "") {
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
                		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + player.getName()));
            		  }
            		  else {
            			  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(currentUser).getUniqueId()) + " &a» &c" + player.getName()));
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
            		  }
        		  }
        	  }
        	  else {
           		  if (player.hasPermission("uv.rank.srmod")) {
            		  if (userAdvisor == "") {
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
                		  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + player.getName()));
            		  }
            		  else {
            			  player.sendMessage(Utils.chat("&b&l&b&l[&3&ladvisor&d&lUV&b&l] &a" + currentUser + "&9's &cAdvisor &b» &e" + PermissionManager.getAdvisor(Bukkit.getOfflinePlayer(currentUser).getUniqueId()) + " &a» &c" + player.getName()));
            			  PermissionManager.assignAdvisorToUser(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), player.getName());
            		  }
        		  }  
        	  }
    	  }
    	  player.closeInventory();
    	  return;
      case TORCH:
          currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
          PermissionManager.addUserToGroup(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), "STAFF_MANAGEMENT", "&3&l[SR]");
          player.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Permissions Added: &3&l[STAFF REPORTS]"));
          player.closeInventory();
          return;
      case REDSTONE_TORCH_ON:
          currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
          PermissionManager.addUserToGroup(Bukkit.getOfflinePlayer(currentUser).getUniqueId(), "FORUM_REPORTS", "&3&l[FR]");
          player.sendMessage(Utils.chat("&b&l&b&l[&6&lstatus&d&lUV&b&l] &9Permissions Added: &3&l[FORUM REPORTS]"));
          player.closeInventory();
          return;
      default:
    	  return;
      }
      
    }
    else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("advisorUV")) {
        event.setCancelled(true);
        String currentUserMain = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
        String currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        switch (event.getCurrentItem().getType()) {
        case BEACON:
              player.openInventory(StatusUI.GUI(player, currentUser));
              return;
        case RED_ROSE:
        	player.openInventory(AdvisorUI.GUI(player, currentUserMain));
        	return;
        default:
			break;
        }
    }
    else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("serverUV")) {
        event.setCancelled(true);
//        String currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        switch (event.getCurrentItem().getType()) {
        case PAPER:
        	if (event.getCurrentItem().getItemMeta().getLore().get(0).contains("UNSLOWED")) {
          	  player.performCommand("uv chat silence");
          	  player.closeInventory();
      		  return;
      	    }
        	else if(event.getCurrentItem().getItemMeta().getLore().get(0).contains("DISABLED") || event.getCurrentItem().getItemMeta().getLore().get(0).contains("SLOWED"))
        	  player.performCommand("uv chat enable");
              player.closeInventory();
              return;
        case MUTTON:
        	if (event.getCurrentItem().getItemMeta().getLore().get(0).contains("Normal") || event.getCurrentItem().getItemMeta().getLore().get(1).contains("Night")) {
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doDaylightCycle false");
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doWeatherCycle false");
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "minecraft:weather clear");
            	player.getWorld().setTime(1200);
            	player.closeInventory();
        		player.sendMessage(Utils.chat("&b&l&b&l[&4&lserver&d&lUV&b&l] &9Weather Updated &b» &6Daytime &c(Permanent)"));
        		return;
        	    }
          	else if (event.getCurrentItem().getItemMeta().getLore().get(1).contains("Day"))
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doDaylightCycle true");
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doWeatherCycle true");
        		player.closeInventory();
        		player.sendMessage(Utils.chat("&b&l&b&l[&4&lserver&d&lUV&b&l] &9Weather Updated &b» &aNormal"));
        		return;
        case COOKED_MUTTON:
        	if (event.getCurrentItem().getItemMeta().getLore().get(0).contains("Normal") || event.getCurrentItem().getItemMeta().getLore().get(1).contains("Day")) {
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doDaylightCycle false");
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doWeatherCycle false");
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "minecraft:weather clear");
            	player.getWorld().setTime(18000);
            	player.closeInventory();
        		player.sendMessage(Utils.chat("&b&l&b&l[&4&lserver&d&lUV&b&l] &9Weather Updated &b» &9Night Time &c(Permanent)"));
        		return;
        	    }
          	else if (event.getCurrentItem().getItemMeta().getLore().get(1).contains("Night"))
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doDaylightCycle true");
        		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doWeatherCycle true");
        		player.closeInventory();
        		player.sendMessage(Utils.chat("&b&l&b&l[&4&lserver&d&lUV&b&l] &9Weather Updated &b» &aNormal"));
        		return;
        case DEAD_BUSH:
        	if (event.getCurrentItem().getItemMeta().getLore().get(0).contains("Creative")) {
          		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "defaultgamemode survival");
                player.closeInventory();
        		player.sendMessage(Utils.chat("&b&l&b&l[&4&lserver&d&lUV&b&l] &cDefault Gamemode &9Updated &b» &4Survival"));
                return;
        	    }
          	else if (event.getCurrentItem().getItemMeta().getLore().get(0).contains("Survival"))
          		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "defaultgamemode creative");
                player.closeInventory();
        		player.sendMessage(Utils.chat("&b&l&b&l[&4&lserver&d&lUV&b&l] &cDefault Gamemode &9Updated &b» &aCreative"));
                return;
        case ANVIL:
        	player.openInventory(UltravioletSelfUI.GUI(player));
        	return;
		default:
			break;
        }
    }
    else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("reportsUV")) {
        event.setCancelled(true);
        String currentUser = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).replace("User: ", ""));
        switch (event.getCurrentItem().getType()) {
        case BLAZE_ROD:
        	Punishments.issuePermanentBan(event.getWhoClicked().getName().toString(), Bukkit.getOfflinePlayer(currentUser), "Inappropriate Name");
        	player.getInventory().clear(event.getSlot());
        	player.updateInventory();
        	player.sendMessage(Utils.chat("&b&l[&9&lreports&d&lUV&b&l] &9Report Approved &b» &c" + ChatColor.stripColor(currentUser)));
              return;
        case BEDROCK:
        	PunishManager.clearReports("Inappropriate Name [Pending]");
        	player.getInventory().remove(Material.BLAZE_ROD);
        	player.closeInventory();
        	player.sendMessage(Utils.chat("&b&l[&9&lreports&d&lUV&b&l] &cRemaining Reports Denied"));
        case SKULL_ITEM: 
            player.performCommand("uv");
            return;
		default:
			break;
        }
    }
    else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("macroUV")) {
        event.setCancelled(true);
        String currentUser = ChatColor.stripColor(event.getInventory().getItem(4).getItemMeta().getDisplayName()).replace("CLIENT: ", "");
        switch (event.getCurrentItem().getType()) {
        case SNOW_BALL:
        	String macroMsg = ChatColor.stripColor(event.getInventory().getItem(event.getSlot()).getItemMeta().getLore().get(0).replace("'", ""));
        	player.performCommand("uv msg " + currentUser + " " + currentUser + ", " + macroMsg);
        	player.closeInventory();
            return;
        case SKULL_ITEM: 
            player.performCommand("uv " + currentUser);
            return;
        case FIREBALL:
        	event.setCancelled(true);
        	return;
		default:
			break;
        }
    }
  }
}
