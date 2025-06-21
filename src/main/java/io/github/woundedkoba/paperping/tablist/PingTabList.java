package io.github.woundedkoba.paperping.tablist;


import io.github.woundedkoba.paperping.PaperPing;
import io.github.woundedkoba.paperping.utils.PingUtil;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PingTabList extends BukkitRunnable {
  private final PaperPing plugin;

  public PingTabList(PaperPing plugin) {
    this.plugin = plugin;
  }

  public void run() {
    for (Player player : this.plugin.getServer().getOnlinePlayers()) {
      var nameComponent = this.plugin.getConfig().getBoolean("tablist.show-real-name")
              ? net.kyori.adventure.text.Component.text(player.getName())
              : player.displayName();

      String prefix = this.plugin.getConfig().getString("tablist.prefix");
      String suffix = this.plugin.getConfig().getString("tablist.suffix");
      assert prefix != null && suffix != null;

      var prefixComponent = prefix.isEmpty() ? net.kyori.adventure.text.Component.empty()
              : LegacyComponentSerializer.legacy('&').deserialize(prefix.replace("%ping%", "" + PingUtil.getPing(player)));
      var suffixComponent = suffix.isEmpty() ? net.kyori.adventure.text.Component.empty()
              : LegacyComponentSerializer.legacy('&').deserialize(suffix.replace("%ping%", "" + PingUtil.getPing(player)));

      // Compose final tab name: prefix + (space) + name + (space) + suffix
      var finalName = net.kyori.adventure.text.Component.empty()
              .append(prefixComponent)
              .append(prefixComponent == net.kyori.adventure.text.Component.empty() ? net.kyori.adventure.text.Component.empty() : net.kyori.adventure.text.Component.space())
              .append(nameComponent)
              .append(suffixComponent == net.kyori.adventure.text.Component.empty() ? net.kyori.adventure.text.Component.empty() : net.kyori.adventure.text.Component.space())
              .append(suffixComponent);

      player.playerListName(finalName);
    }
  }
}