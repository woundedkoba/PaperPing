package io.github.woundedkoba.paperping.utils;

import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class PingUtil {
  private static Logger logger;

  public static void setLogger(Logger pluginLogger) {
    logger = pluginLogger;
  }

  public static int getPing(Player p) {
    try {
      return p.getPing();
    } catch (Exception e) {
      if (logger != null) {
        logger.warning("Failed to get ping for player " + p.getName() + ": " + e.getMessage());
      }
      return 0;
    }
  }
}
