package io.github.woundedkoba.paperping.commands;

import io.github.woundedkoba.paperping.PaperPing;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@SuppressWarnings("deprecation")
public class PingReloadCommand implements CommandExecutor {
  private PaperPing plugin;
  
  public PingReloadCommand(PaperPing plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
    if (!sender.hasPermission("PaperPing.reload")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("permission-system.no-perm-message")));
      return true;
    } 
    this.plugin.reload();
    if (sender instanceof org.bukkit.entity.Player)
      sender.sendMessage(NamedTextColor.GREEN + "Plugin reloaded."); 
    return true;
  }
}