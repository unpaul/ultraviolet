package me.pavl.ultraviolet.ui;

import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PermissionManager;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PunishUI
{
  public static Inventory inv;
  public static String inventory_name;
  public static int inv_rows = 54;
  
  public static void initialize() {
    inventory_name = Utils.chat("&c&lpunish&d&lUV");
    
    inv = Bukkit.createInventory(null, inv_rows);
  }
  
  public static String lastIP;
  @SuppressWarnings("deprecation")
  public static Inventory GUI(Player sender, String p, String reason)
  {
    Main plugin = (Main)Main.getPlugin(Main.class);
    Player player = Bukkit.getPlayerExact(p);
    java.util.UUID senderUUID = sender.getUniqueId();
    plugin.getConfig().set(senderUUID + ".lastPunishee", p);
    plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
    plugin.saveConfig();
    Inventory toReturn = Bukkit.createInventory(sender, inv_rows, inventory_name);
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(p);
    String playerTag = PermissionManager.getUserTag(offlinePlayer.getUniqueId());
    String playerStatus = PermissionManager.getUserStatus(offlinePlayer.getUniqueId());
    lastIP = plugin.getConfig().getString(offlinePlayer.getUniqueId() + ".lastIP");
    float sevOneMute = PunishManager.getSev1MuteLengthHours(offlinePlayer.getUniqueId());
    

    String sevOneMuteString;
   
    

    if (sevOneMute >= 24.0F) {
      sevOneMuteString = String.valueOf(sevOneMute / 24.0F) + " days";
    }
    else {
      sevOneMuteString = String.valueOf(sevOneMute) + " hours";
    }
    float sevTwoMute = PunishManager.getSev2MuteLengthHours(offlinePlayer.getUniqueId());
    String sevTwoMuteString; 
    if (sevTwoMute >= 24.0F) {
      sevTwoMuteString = String.valueOf(sevTwoMute / 24.0F) + " days";
    }
    else {
      sevTwoMuteString = String.valueOf(sevTwoMute) + " hours";
    }
    float sevThreeMute = PunishManager.getSev3MuteLengthHours(offlinePlayer.getUniqueId());
    String sevThreeMuteString;
    if (sevThreeMute >= 24.0F) {
      sevThreeMuteString = String.valueOf(sevThreeMute / 24.0F) + " days";
    }
    else {
      sevThreeMuteString = String.valueOf(sevThreeMute) + " hours";
    }
    float sevOneGameplay = PunishManager.getSev1GameplayBanLengthHours(offlinePlayer.getUniqueId());
    String sevOneGameplayString;
    if (sevOneGameplay >= 24.0F) {
      sevOneGameplayString = String.valueOf(sevOneGameplay / 24.0F) + " days";
    }
    else {
      sevOneGameplayString = String.valueOf(sevOneGameplay) + " hours";
    }
    float sevTwoGameplay = PunishManager.getSev2GameplayBanLengthHours(offlinePlayer.getUniqueId());
    String sevTwoGameplayString;
    if (sevTwoGameplay >= 24.0F) {
      sevTwoGameplayString = String.valueOf(sevTwoGameplay / 24.0F) + " days";
    }
    else {
      sevTwoGameplayString = String.valueOf(sevTwoGameplay) + " hours";
    }
    float sevThreeGameplay = PunishManager.getSev3GameplayBanLengthHours(offlinePlayer.getUniqueId());
    String sevThreeGameplayString;
    if (sevThreeGameplay >= 24.0F) {
      sevThreeGameplayString = String.valueOf(sevThreeGameplay / 24.0F) + " days";
    }
    else {
      sevThreeGameplayString = String.valueOf(sevThreeGameplay) + " hours";
    }
    float sevOneHacking = PunishManager.getSev1HackingBanLengthHours(offlinePlayer.getUniqueId());
    String sevOneHackingString;
    if (sevOneHacking >= 24.0F) {
      sevOneHackingString = String.valueOf(sevOneHacking / 24.0F) + " days";
    }
    else {
      sevOneHackingString = String.valueOf(sevOneHacking) + " hours";
    }
    float sevTwoHacking = PunishManager.getSev2HackingBanLengthHours(offlinePlayer.getUniqueId());
    String sevTwoHackingString;
    if (sevTwoHacking >= 24) {
      sevTwoHackingString = String.valueOf(sevTwoHacking / 24) + " days";
      if (sevTwoHacking == 1000.0F) {
        sevTwoHackingString = "Permanent";
      }
    }
    else {
      sevTwoHackingString = String.valueOf(sevTwoHacking) + " hours";
    }
    if (player == null) {
      if (offlinePlayer.hasPlayedBefore()) {
        if (sender.hasPermission("uv.punishAdmin")) {
          if (playerTag.equalsIgnoreCase("null")) {
            Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &cOFFLINE", "&bLast Played: &d" + plugin.getConfig().getString(new StringBuilder().append(offlinePlayer.getUniqueId()).append(".lastSeen").toString()), "&dRank: " + playerTag, " ", "&cLast IP Address: &b" + lastIP });
          }
          else {
            Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &cOFFLINE", "&bLast Played: &d" + plugin.getConfig().getString(new StringBuilder().append(offlinePlayer.getUniqueId()).append(".lastSeen").toString()), " ", "&cLast IP Address: &b" + lastIP });
          }
          
        }
        else if (playerTag != "null") {
          Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &cOFFLINE", "&bLast Played: &d" + plugin.getConfig().getString(new StringBuilder().append(offlinePlayer.getUniqueId()).append(".lastSeen").toString()), "&dRank: " + playerTag });
        }
        else {
          Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &cOFFLINE", "&bLast Played: &d" + plugin.getConfig().getString(new StringBuilder().append(offlinePlayer.getUniqueId()).append(".lastSeen").toString()) });
        }
      }
      else{
        Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&eIn-Game Status: &cNEVER SEEN" });
        Utils.createItem(inv, 38, 1, 54, "&c&oWARNING: &5Unregistered User", new String[0]);
        Utils.createItem(inv, 101, 1, 1, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 2, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 3, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 4, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 6, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 7, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 8, "==", new String[0]);
        Utils.createItem(inv, 101, 1, 9, "==", new String[0]);
        Utils.createItem(inv, 340, 1, 46, "&aAccess " + Bukkit.getOfflinePlayer(p).getName() + "'s Punishment History", new String[] { "&cUse for: ", "&c- Punishment Decisions", "&c- Staffing Process", "&c- Abuse Investigation" });
        if (!sender.hasPermission("uv.punishAdmin")) {
          Utils.createItem(inv, 323, 1, 54, "&cReport " + offlinePlayer.getName() + " as an Inappropriate Name", new String[] { "&b- Use Judgement/Guidelines.", "&b- &7[&4ADMIN&c+&7]&b Will Review/Judge This." });
          Utils.createItem(inv, 325, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &cVIEW-ONLY MODE", "&c<&b" + offlinePlayer.getName() + " &chas never joined>" });
          toReturn.setContents(inv.getContents());
          inv.clear();
          return toReturn;
        }
      }
    }
    else {
      if (sender.hasPermission("uv.punishAdmin")) {
        if (playerTag != "null") {
          Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE", "&dRank: " + playerTag, " ", "&cLast IP Address: &b" + lastIP });
        }
        else {
          Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE", " ", "&cLast IP Address: &b" + lastIP });
        }
        
      }
      else if (playerTag != "null") {
        Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE", "&dRank: " + playerTag });
      }
      else {
        Utils.createItemHead(inv, 5, offlinePlayer, new String[] { "&cPast Offenses: &c" + String.valueOf(PunishManager.getOccurrence(offlinePlayer.getUniqueId()) - 1), "&eIn-Game Status: &aONLINE" });
      }
      
      Utils.createItem(inv, 339, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason });
    }
    if (!sender.hasPermission("uv.punishAdmin")) {
      Utils.createItem(inv, 323, 1, 54, "&cReport " + offlinePlayer.getName() + " as an Inappropriate Name", new String[] { "&b- Use Judgement/Guidelines.", "&b- &7[&4ADMIN&c+&7]&b Will Review/Judge This." });
    }
    Utils.createItem(inv, 101, 1, 1, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 2, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 3, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 4, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 6, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 7, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 8, "==", new String[0]);
    Utils.createItem(inv, 101, 1, 9, "==", new String[0]);
    Utils.createItem(inv, 340, 1, 46, "&aAccess " + Bukkit.getOfflinePlayer(p).getName() + "'s Punishment History", new String[] { "&cUse for: ", "&c- Punishment Decisions", "&c- Staffing Process", "&c- Abuse Investigation" });
    
    if (sender.hasPermission("uv.punishadmin")) {
      Utils.createItem(inv, 327, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &4&l[OVERRIDEN]" });
      Utils.createItem(inv, 76, 1, 45, "&c[ADD LABEL] &dForum Report", new String[] { "&a- Adds [FR] Label", "&e- Use when processing forum reports.", "&b- Only accessible to &7[&9Reports Team&c+&7]" });
      Utils.createItem(inv, 50, 1, 37, "&c[ADD LABEL] &dStaff Report", new String[] { "&a- Adds [SR] Label", "&e- Use when processing reports from subordinate members.", "&b- Only accessible to mentors / leadership members" });
      Utils.createItem(inv, 166, 1, 54, "&4&o&lNETWORK BAN", new String[] { "&cActions:", "- &4Permanent &aIn-Game Ban", "- &eIP Address Ban", "&c&oWARNING: This will notify administrators", "&aONLY USE IN EXTREME CASES" });
      Utils.createItem(inv, 386, 1, 12, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
      Utils.createItem(inv, 358, 1, 14, "&e&lGameplay Offense", new String[] { "&bIncludes Map Abuse / General Gameplay Issues" });
      Utils.createItem(inv, 267, 1, 16, "&c&lHacking/Unapproved Client Offense", new String[] { "&cIncludes Hacking/Unapproved Mods" });
      Utils.createItem(inv, 2257, 1, 21, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
      Utils.createItem(inv, 2256, 1, 30, "&9&lChat Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoMuteString, "&cIncludes Advertisement, Bullying, etc." });
      Utils.createItem(inv, 2259, 1, 39, "&9&lChat Offense: &cSeverity 3", new String[] { "&cPunishment Time: &b" + sevThreeMuteString, "&cIncludes Advertisement, Bullying, etc." });
      Utils.createItemByte(inv, 160, 5, 1, 23, "&9&lGameplay Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneGameplayString, "&bIncludes Map Abuse / General Gameplay Issues" });
      Utils.createItemByte(inv, 160, 4, 1, 32, "&9&lGameplay Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoGameplayString, "&bIncludes Map Abuse / General Gameplay Issues" });
      Utils.createItemByte(inv, 160, 14, 1, 41, "&9&lGameplay Offense: &cSeverity 3", new String[] { "&cPunishment Time: &b" + sevThreeGameplayString, "&bIncludes Map Abuse / General Gameplay Issues" });
      Utils.createItem(inv, 317, 1, 25, "&9&lHacking Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneHackingString, "&cIncludes Hacking/Unapproved Mods" });
      Utils.createItem(inv, 309, 1, 34, "&9&lHacking Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoHackingString, "&cIncludes Hacking/Unapproved Mods" });
      Utils.createItem(inv, 313, 1, 43, "&9&lHacking Offense: &cSeverity 3", new String[] { "&cPunishment Time: &bPermanent", "&cIncludes Hacking/Unapproved Mods" });
      Utils.createItem(inv, 348, 1, 48, "&4&lPERMANENT &c&lMute", new String[] { "&cExtreme Cases Only.", "&7[&6MOD&c+&7] &bAccess" });
      Utils.createItem(inv, 331, 1, 52, "&4&lPERMANENT &c&lBan", new String[] { "&cExtreme Cases Only.", "&7[&6MOD&c+&7] &bAccess" });
      Utils.createItem(inv, 288, 1, 27, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
      toReturn = Bukkit.createInventory(sender, inv_rows, Utils.chat("&c&lpunish&d&lUV&c&l+"));

    }
    else if (reason.length() > 3) {
      if ((ChatColor.stripColor(playerStatus).equalsIgnoreCase("ADMIN")) || (ChatColor.stripColor(playerStatus).equalsIgnoreCase("OWNER"))) {
        Utils.createItem(inv, 166, 1, 23, "&c" + offlinePlayer.getName() + " &9has &b&l[&c&lpunish&d&lUV&4&l+&b&l]", new String[0]);
        Utils.createItem(inv, 327, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &cVIEW-ONLY MODE", "&c" + offlinePlayer.getName() + " &9has &b&l[&c&lpunish&d&lUV&c&l+&b&l]" });
      }
      else if (reason.toLowerCase().startsWith("light advertising")) {
        reason = reason.replace("light", "Light");
        reason = reason.replace("advertising", "Advertisement");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("light advertisement")) {
        reason = reason.replace("light", "Light");
        reason = reason.replace("advertisement", "Advertisement");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("spam")) {
        reason = reason.replace("spam", "Spamming");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Spamming, Caps, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("spamming")) {
        reason = reason.replace("spamming", "Spamming");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Spamming, Caps, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("general rudeness")) {
        reason = reason.replace("general", "General");
        reason = reason.replace("rudeness", "Rudeness");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("chat trolling")) {
        reason = reason.replace("chat", "Chat");
        reason = reason.replace("trolling", "Trolling");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("filter bypass")) {
        reason = reason.replace("filter", "Filter");
        reason = reason.replace("bypass", "Bypass");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2256, 1, 42, "&9&lChat Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("ghosting")) {
        reason = reason.replace("ghosting", "Ghosting");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("inappropriate behavior")) {
        reason = reason.replace("inappropriate", "Inappropriate");
        reason = reason.replace("behavior", "Behavior");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("rioting")) {
        reason = reason.replace("rioting", "Rioting");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2257, 1, 33, "&9&lChat Offense: &aSeverity 1", new String[] { "&cPunishment Time: &b" + sevOneMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 288, 1, 31, "&3Document a Warning", new String[] { "&cCheck Previous Warnings First.", "&eWarned Players = Punishment" });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });

      }
      else if (reason.toLowerCase().startsWith("abusive behavior")) {
        reason = reason.replace("abusive", "Abusive");
        reason = reason.replace("behavior", "Behavior");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2256, 1, 32, "&9&lChat Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("harassment")) {
        reason = reason.replace("harassment", "Harassment");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2256, 1, 32, "&9&lChat Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("sexual harassment")) {
        reason = reason.replace("sexual", "Sexual");
        reason = reason.replace("harassment", "Harassment");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2256, 1, 32, "&9&lChat Offense: &eSeverity 2", new String[] { "&cPunishment Time: &b" + sevTwoMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });

      }
      else if (reason.toLowerCase().startsWith("threats")) {
        reason = reason.replace("threats", "Malicious Threats");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2259, 1, 32, "&9&lChat Offense: &cSeverity 3", new String[] { "&cPunishment Time: &b" + sevThreeMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("malicious threats")) {
        reason = reason.replace("malicious", "Malicious");
        reason = reason.replace("threats", "Threats");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2259, 1, 32, "&9&lChat Offense: &cSeverity 3", new String[] { "&cPunishment Time: &b" + sevThreeMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("discrimination")) {
        reason = reason.replace("discrimination", "Discrimination");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2259, 1, 32, "&9&lChat Offense: &cSeverity 3", new String[] { "&cPunishment Time: &b" + sevThreeMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else if (reason.toLowerCase().startsWith("racism")) {
        reason = reason.replace("racism", "Discrimination");
        reason = reason.replace("Racism", "Discrimination");
        plugin.getConfig().set(senderUUID + ".lastPunishReason", reason);
        plugin.saveConfig();
        Utils.createItem(inv, 386, 1, 14, "&a&lChat Offense", new String[] { "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 2259, 1, 32, "&9&lChat Offense: &cSeverity 3", new String[] { "&cPunishment Time: &b" + sevThreeMuteString, "&cIncludes Advertisement, Bullying, etc." });
        Utils.createItem(inv, 326, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &aTRUE" });
      }
      else {
        Utils.createItem(inv, 325, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &cFALSE", "&b<Invalid Reason>", "&7Enter Another Reason or Contact &4&l[ADMIN+]" });
        Utils.createItem(inv, 30, 1, 23, "&c&lNo Punishments Available", new String[] { "&b<Reason was Unrecognizable/Invalid>", "&aContact an &4&l[ADMIN+] for Additional Help." });
      }
      if (sender.hasPermission("uv.staffreports")) {
        Utils.createItem(inv, 50, 1, 37, "&c[ADD LABEL] &dStaff Report", new String[] { "&a- Adds [SR] Label", "&e- Use when processing reports from subordinate members.", "&b- Only accessible to mentors / leadership members" });
      }
      if (sender.hasPermission("uv.forumreports")) {
        Utils.createItem(inv, 76, 1, 45, "&c[ADD LABEL] &dForum Report", new String[] { "&a- Adds [FR] Label", "&e- Use when processing forum reports.", "&b- Only accessible to &7[&9Reports Team&c+&7]" });
      }
    }
    else {
      Utils.createItem(inv, 325, 1, 50, "&b&lPunishment Reason", new String[] { "&c" + reason, "&9[AutoFill] &f= &cVIEW-ONLY MODE", "&b<Short Reason Detected>" });
    }
    toReturn.setContents(inv.getContents());
    inv.clear();
    return toReturn;
  }
}
