package io.github.woundedkoba.paperping.tablist;


import io.github.woundedkoba.paperping.PaperPing;
import io.github.woundedkoba.paperping.utils.PingUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class PingTabList extends BukkitRunnable {
  private PaperPing plugin;
  
  public PingTabList(PaperPing plugin) {
    this.plugin = plugin;
  }

public void run() {
    for (Player player : this.plugin.getServer().getOnlinePlayers()) {
      String currentName;
      if (this.plugin.getConfig().getBoolean("tablist.show-real-name")) {
        currentName = player.getName();
      } else {
        currentName = player.getDisplayName();
      } 
      String prefix = this.plugin.getConfig().getString("tablist.prefix");
      if (!prefix.equals(""))
        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', prefix
              .replace("%ping%", "" + PingUtil.getPing(player))) + " " + currentName); 
      String suffix = this.plugin.getConfig().getString("tablist.suffix");
      if (!suffix.equals(""))
        player.setPlayerListName(currentName + " " + ChatColor.translateAlternateColorCodes('&', suffix
              .replace("%ping%", "" + PingUtil.getPing(player)))); 
    } 
  }
}