package io.github.woundedkoba.paperping.commands;

import io.github.woundedkoba.paperping.PaperPing;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PingReloadCommand implements CommandExecutor {
  private final PaperPing plugin;
  
  public PingReloadCommand(PaperPing plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, @NotNull Command c, @NotNull String label, String @NotNull [] args) {
    if (!sender.hasPermission("PaperPing.reload")) {
      String noPerm = Objects.requireNonNull(this.plugin.getConfig().getString("permission-system.no-perm-message"));
      sender.sendMessage(LegacyComponentSerializer.legacy('&').deserialize(noPerm));
      return true;
    } 
    this.plugin.reload();
    if (sender instanceof org.bukkit.entity.Player)
      sender.sendMessage(NamedTextColor.GREEN + "Plugin reloaded."); 
    return true;
  }
}