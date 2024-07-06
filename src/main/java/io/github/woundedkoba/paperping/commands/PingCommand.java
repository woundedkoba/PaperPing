package io.github.woundedkoba.paperping.commands;

import io.github.woundedkoba.paperping.PaperPing;
import io.github.woundedkoba.paperping.utils.PingUtil;
import io.github.woundedkoba.paperping.utils.SoundUtil;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class PingCommand implements CommandExecutor {
  private PaperPing plugin;
  
  public PingCommand(PaperPing plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
    if (!(sender instanceof Player)) {
      this.plugin.getLogger().info(NamedTextColor.RED + "This command is only executable as a Player.");
      return true;
    } 
    Player p = (Player)sender;
    if (args.length == 0) {
      if (!hasPerms(p, "PaperPing.ping")) {
        String noPerm = this.plugin.getConfig().getString("permission-system.no-perm-message");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
        return true;
      } 
      String ping = "" + PingUtil.getPing(p);
      String customMex = this.plugin.getConfig().getString("ping-command.ping-message").replaceAll("%ping%", ping);
      p.sendMessage(ChatColor.translateAlternateColorCodes('&', customMex));
    } else {
      if (!hasPerms(p, "PaperPing.ping.others")) {
        String noPerm = this.plugin.getConfig().getString("others-ping.not-allowed-message");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
        return true;
      } 
      String target = (args.length > 0) ? args[0] : null;
      Player targetP = Bukkit.getPlayer(target);
      if (targetP == null) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
              .getConfig().getString("others-ping.player-not-found")));
        return true;
      } 
      p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin
            .getConfig().getString("ping-command.ping-target-message")
            .replace("%ping%", "" + PingUtil.getPing(targetP))
            .replace("%target%", targetP.getName())));
    } 
    if (this.plugin.getConfig().getBoolean("sound-manager.enabled"))
      SoundUtil.playSound(p, this.plugin.getConfig().getString("sound-manager.sound-type")); 
    return true;
  }
  
  private boolean hasPerms(Player p, String perm) {
    return (!this.plugin.getConfig().getBoolean("permission-system.enabled") || p.hasPermission(perm));
  }
}
