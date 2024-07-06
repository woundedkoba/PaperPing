package io.github.woundedkoba.paperping;

import io.github.woundedkoba.paperping.commands.PingCommand;
import io.github.woundedkoba.paperping.commands.PingReloadCommand;
import io.github.woundedkoba.paperping.tablist.PingTabList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperPing extends JavaPlugin {
  private static PaperPing instance;
  
  public static PaperPing getInstance() {
    return instance;
  }
  
  public void onEnable() {
    instance = this;
    saveDefaultConfig();
    getCommand("ping").setExecutor((CommandExecutor)new PingCommand(this));
    getCommand("pingreload").setExecutor((CommandExecutor)new PingReloadCommand(this));
    registerTasks();
  }
  
  public void onDisable() {
    instance = null;
    getLogger().info("Cancelling tasks...");
    getServer().getScheduler().cancelTasks((Plugin)this);
  }
  
  private void registerTasks() {
    if (getConfig().getBoolean("permission-system.enabled"))
      getLogger().info("The permission-system is enabled. Please check that users have proper permissions."); 
    if (!getConfig().getBoolean("sound-manager.enabled"))
      getLogger().info("The sound manager is disabled, no sounds will be played on commands. You can change this option in the config."); 
    if (!getConfig().getBoolean("tablist.enabled")) {
      getLogger().info("The tablist is disabled, the ping will not be shown as a prefix. You can change this option in the config.");
    } else {
      Long delay = Long.valueOf(getConfig().getLong("tablist.update-delay"));
      (new PingTabList(this)).runTaskTimerAsynchronously((Plugin)this, delay.longValue() * 20L, delay.longValue() * 20L);
      getLogger().info("TabList is enabled, task added with a delay of " + delay + " second/s.");
    } 
  }
  
  public void reload() {
    getLogger().info("Reloading the plugin...");
    getServer().getScheduler().cancelTasks((Plugin)this);
    reloadConfig();
    registerTasks();
    getLogger().info("Plugin reloaded.");
  }
}
