package io.github.woundedkoba.paperping.commands;

import io.github.woundedkoba.paperping.PaperPing;
import io.github.woundedkoba.paperping.utils.PingUtil;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class PingCommand implements CommandExecutor {
  private final PaperPing plugin;
  
  public PingCommand(PaperPing plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command c, @NotNull String label, String @NotNull [] args) {
    if (!(sender instanceof Player p)) {
      this.plugin.getLogger().info(NamedTextColor.RED + "This command is only executable as a Player.");
      return true;
    }
    if (args.length == 0) {
      if (lacksPerm(p, "PaperPing.ping")) {
        String noPerm = this.plugin.getConfig().getString("permission-system.no-perm-message");
        assert noPerm != null;
        p.sendMessage(LegacyComponentSerializer.legacy('&').deserialize(noPerm));
        return true;
      } 
      String ping = "" + PingUtil.getPing(p);
      String customMex = Objects.requireNonNull(this.plugin.getConfig().getString("ping-command.ping-message")).replaceAll("%ping%", ping);
      p.sendMessage(LegacyComponentSerializer.legacy('&').deserialize(customMex));
    } else {
      if (lacksPerm(p, "PaperPing.ping.others")) {
        String noPerm = this.plugin.getConfig().getString("others-ping.not-allowed-message");
        assert noPerm != null;
        p.sendMessage(LegacyComponentSerializer.legacy('&').deserialize(noPerm));
        return true;
      } 
      String target = args[0];
      Player targetP = Bukkit.getPlayer(target);
      if (targetP == null) {
        String notFoundMsg = Objects.requireNonNull(this.plugin.getConfig().getString("others-ping.player-not-found"));
        p.sendMessage(LegacyComponentSerializer.legacy('&').deserialize(notFoundMsg));
        return true;
      }
      String pingTargetMsg = Objects.requireNonNull(this.plugin.getConfig().getString("ping-command.ping-target-message"))
              .replace("%ping%", "" + PingUtil.getPing(targetP))
              .replace("%target%", targetP.getName());
      p.sendMessage(LegacyComponentSerializer.legacy('&').deserialize(pingTargetMsg));
    }
    return true;
  }

  private boolean lacksPerm(Player p, String perm) {
    return this.plugin.getConfig().getBoolean("permission-system.enabled") && !p.hasPermission(perm);
  }
}
