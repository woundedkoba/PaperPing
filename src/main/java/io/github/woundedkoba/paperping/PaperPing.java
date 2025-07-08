package io.github.woundedkoba.paperping;

import io.github.woundedkoba.paperping.commands.PingCommand;
import io.github.woundedkoba.paperping.commands.PingReloadCommand;
import io.github.woundedkoba.paperping.tablist.PingTabList;
import io.github.woundedkoba.paperping.utils.PingUtil;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public class PaperPing extends JavaPlugin {
  private static PaperPing instance;
  
  public static PaperPing getInstance() {
    return instance;
  }
  
  public void onEnable() {
    instance = this;
    PingUtil.setLogger(this.getLogger());
    saveDefaultConfig();
    Objects.requireNonNull(getCommand("ping")).setExecutor(new PingCommand(this));
    Objects.requireNonNull(getCommand("pingreload")).setExecutor(new PingReloadCommand(this));
    registerTasks();
  }
  
  public void onDisable() {
    instance = null;
    getLogger().info("Cancelling tasks...");
    getServer().getScheduler().cancelTasks(this);
  }
  
  private void registerTasks() {
    if (getConfig().getBoolean("permission-system.enabled"))
      getLogger().info("The permission-system is enabled. Please check that users have proper permissions."); 
    if (!getConfig().getBoolean("sound-manager.enabled"))
      getLogger().info("The sound manager is disabled, no sounds will be played on commands. You can change this option in the config."); 
    if (!getConfig().getBoolean("tablist.enabled")) {
      getLogger().info("The tablist is disabled, the ping will not be shown as a prefix. You can change this option in the config.");
    } else {
      long delay = getConfig().getLong("tablist.update-delay");
      (new PingTabList(this)).runTaskTimerAsynchronously(this, delay * 20L, delay * 20L);
      getLogger().info("TabList is enabled, task added with a delay of " + delay + " second/s.");
    } 
  }
  
  public void reload() {
    getLogger().info("Reloading the plugin...");
    getServer().getScheduler().cancelTasks(this);
    reloadConfig();
    registerTasks();
    getLogger().info("Plugin reloaded.");
  }
}
