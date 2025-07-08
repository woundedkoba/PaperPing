package io.github.woundedkoba.paperping.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class PingUtil {
  private static Logger logger;

  public static void setLogger(Logger pluginLogger) {
    logger = pluginLogger;
  }

  public static int getPing(Player p) {
    String packageName = Bukkit.getServer().getClass().getPackage().getName();
    String[] versionParts = packageName.replace(".", ",").split(",");
    String version;

    if (versionParts.length < 4) {
      version = Bukkit.getBukkitVersion().split("-")[0];
    } else {
      version = versionParts[3];
    }

    if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer"))
      p = Bukkit.getPlayer(p.getUniqueId());

    try {
      assert p != null;
      return p.getPing();
    } catch (Exception e) {
      if (logger != null) {
        logger.warning("Failed to get ping for player " + p.getName() + ": " + e.getMessage());
      }
      return 0;
    }
  }
}
