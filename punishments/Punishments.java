package me.pavl.ultraviolet.punishments;

import java.util.Date;
import me.pavl.ultraviolet.Main;
import me.pavl.ultraviolet.mysql.PunishManager;
import me.pavl.ultraviolet.utils.Utils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class Punishments
{
  public Punishments() {}
  
  public static BanList banList = Bukkit.getBanList(org.bukkit.BanList.Type.NAME);
  static BanList ipBanList = Bukkit.getBanList(org.bukkit.BanList.Type.IP);
  static Main plugin = (Main)Main.getPlugin(Main.class);
  
  @SuppressWarnings("deprecation")
public static void issueNetworkBan(String s, OfflinePlayer p, String reason) {
    PunishManager.addPunishment(p.getUniqueId(), "Network Ban", "Permanent", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), "Never");
    banList.addBan(p.getName(), reason, null, s);
    ipBanList.addBan(plugin.getConfig().getString(p.getUniqueId() + ".lastIP"), reason, null, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issuePermanentBan(String s, OfflinePlayer p, String reason)
  {
    PunishManager.addPunishment(p.getUniqueId(), "Ban", "Permanent", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), "Never");
    banList.addBan(p.getName(), reason, null, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev1GameplayBan(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev1GameplayBanLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Gameplay Ban", "1", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
    banList.addBan(p.getName(), reason, punishmentEndDate, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev2GameplayBan(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev2GameplayBanLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Gameplay Ban", "2", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
    banList.addBan(p.getName(), reason, punishmentEndDate, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev3GameplayBan(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev3GameplayBanLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Gameplay Ban", "3", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
    banList.addBan(p.getName(), reason, punishmentEndDate, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev1HackingBan(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev1HackingBanLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Hacking Ban", "1", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
    banList.addBan(p.getName(), reason, punishmentEndDate, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev2HackingBan(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev2HackingBanLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Hacking Ban", "2", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
    banList.addBan(p.getName(), reason, punishmentEndDate, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev3HackingBan(String s, OfflinePlayer p, String reason) {
    PunishManager.addPunishment(p.getUniqueId(), "Hacking Ban", "3", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), "Never");
    banList.addBan(p.getName(), reason, null, s);
    if (p.getPlayer() != null) {
      p.getPlayer().kickPlayer(reason);
    }
  }
  
  @SuppressWarnings("deprecation")
public static void issuePermanentMute(String s, OfflinePlayer p, String reason)
  {
    PunishManager.addPunishment(p.getUniqueId(), "Mute", "Permanent", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), "Never");
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev1Mute(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev1MuteLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Mute", "1", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev2Mute(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev2MuteLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Mute", "2", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
  }
  
  @SuppressWarnings("deprecation")
public static void issueSev3Mute(String s, OfflinePlayer p, String reason) {
    Date punishmentEndDate = Utils.getLaterDate(PunishManager.getSev3MuteLengthHours(p.getUniqueId()));
    PunishManager.addPunishment(p.getUniqueId(), "Mute", "3", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getDateAsString(punishmentEndDate));
  }
  
  @SuppressWarnings("deprecation")
public static void issueWarning(String s, OfflinePlayer p, String reason) {
    PunishManager.addPunishment(p.getUniqueId(), "Warning", "null", Bukkit.getOfflinePlayer(s).getUniqueId(), reason, Utils.getCurrentDate(), Utils.getCurrentDate());
  }
}
