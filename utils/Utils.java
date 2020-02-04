package me.pavl.ultraviolet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils
{
  public Utils() {}
  
  public static String chat(String s)
  {
    return org.bukkit.ChatColor.translateAlternateColorCodes('&', s);
  }
  


  @SuppressWarnings("deprecation")
public static ItemStack createItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, String... loreString)
  {
    ArrayList<String> lore = new ArrayList<>();
    
    ItemStack item = new ItemStack(materialId, amount);
    
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(chat(displayName));
    for (String s : loreString) {
      lore.add(chat(s));
    }
    meta.setLore(lore);
    item.setItemMeta(meta);
    inv.setItem(invSlot - 1, item);
    return item;
  }
  


  @SuppressWarnings("deprecation")
public static ItemStack createEnchantedItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, String... loreString)
  {
    ArrayList<String> lore = new ArrayList<>();
    
    ItemStack item = new ItemStack(materialId, amount);
    
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(chat(displayName));
    for (String s : loreString) {
      lore.add(chat(s));
    }
    meta.setLore(lore);
    item.setItemMeta(meta);
    item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    inv.setItem(invSlot - 1, item);
    return item;
  }
  


  @SuppressWarnings("deprecation")
public static ItemStack createItemByte(Inventory inv, int materialId, int byteId, int amount, int invSlot, String displayName, String... loreString)
  {
    ArrayList<String> lore = new ArrayList<>();
    
    ItemStack item = new ItemStack(Material.getMaterial(materialId), amount, (short)byteId);
    
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(chat(displayName));
    for (String s : loreString) {
      lore.add(chat(s));
    }
    meta.setLore(lore);
    item.setItemMeta(meta);
    
    inv.setItem(invSlot - 1, item);
    return item;
  }
  
  @SuppressWarnings("deprecation")
public static ItemStack createItemHead(Inventory inv, int invSlot, String displayName, String... loreString) { 
	ArrayList<String> lore = new ArrayList<>();
    String name = Bukkit.getOfflinePlayer(displayName).getName();
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setOwner(name);
    meta.setDisplayName(chat("&a&nCLIENT&r: &b" + name));
    for (String s : loreString) {
      lore.add(chat(s));
    }
    meta.setLore(lore);
    skull.setItemMeta(meta);
    inv.setItem(invSlot - 1, skull);
    return skull;
  }
  
  public static ItemStack createItemHead(Inventory inv, int invSlot, Player targetPlayer, String... loreString) { ArrayList<String> lore = new ArrayList<>();
	lore = new ArrayList<>();
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setOwner(targetPlayer.getName());
    meta.setDisplayName(chat("&a&nCLIENT&r: &b" + targetPlayer.getName()));
    for (String s : loreString) {
      lore.add(chat(s));
    }
    meta.setLore(lore);
    skull.setItemMeta(meta);
    inv.setItem(invSlot - 1, skull);
    return skull;
  }
  
  public static ItemStack createItemHead(Inventory inv, int invSlot, OfflinePlayer targetPlayer, String... loreString) { ArrayList<String> lore = new ArrayList<>();
    lore = new ArrayList<>();
    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
    SkullMeta meta = (SkullMeta)skull.getItemMeta();
    meta.setOwner(targetPlayer.getName());
    meta.setDisplayName(chat("&a&nCLIENT&r: &b" + targetPlayer.getName()));
    for (String s : loreString) {
      lore.add(chat(s));
    }
    meta.setLore(lore);
    skull.setItemMeta(meta);
    inv.setItem(invSlot - 1, skull);
    return skull;
  }
  
  public static String getCurrentDate() { 
	  Date now = new Date();
    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
    return format.format(now);
  }
  
  public static Date getStringAsDate(String date) { 
	  if (date == "Never") {
      return getStringAsDate("01-01-1999 12:00:01 AM");
    }
    try
    {
      return new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a").parse(date);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return getCurrentDateDate();
  }
  
  public static String getDateAsString(Date date) { 
	  String dateValue = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a").format(date);
    return dateValue;
  }
  
  public static Date getCurrentDateDate() { 
	  Date now = new Date();
    return now;
  }
  
  public static boolean getDateHasPassed(Date date) { 
	  return date.before(getCurrentDateDate()); }
  
  public static Date getLaterDate(int hours) {
    Date newDate = DateUtils.addHours(getCurrentDateDate(), hours);
    return newDate;
  }
  
  public static Date getLaterSeconds(int seconds) { Date newDate = DateUtils.addSeconds(getCurrentDateDate(), seconds);
    return newDate;
  }
}
