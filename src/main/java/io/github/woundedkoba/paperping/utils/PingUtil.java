package io.github.woundedkoba.paperping.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PingUtil {
  public static int getPing(Player p) {
    String packageName = Bukkit.getServer().getClass().getPackage().getName();
    String[] versionParts = packageName.replace(".", ",").split(",");
    String version;

    if (versionParts.length < 4) {
      // Try an alternative method to get the version, if available
      version = Bukkit.getBukkitVersion().split("-")[0]; // Use the first part of the version if needed
      //System.err.println("Alternative server version: " + version);
    } else {
      version = versionParts[3];
    }

    if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer"))
      p = Bukkit.getPlayer(p.getUniqueId()); 
    
    try {
      assert p != null;
      return p.getPing();
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    } 
  }
}
