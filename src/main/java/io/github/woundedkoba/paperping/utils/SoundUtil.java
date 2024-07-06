package io.github.woundedkoba.paperping.utils;


import io.github.woundedkoba.paperping.PaperPing;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtil {
  public static void playSound(Player p, String soundType) {
    float volume = Float.parseFloat(PaperPing.getInstance().getConfig().getString("sound-manager.volume"));
    float pitch = Float.parseFloat(PaperPing.getInstance().getConfig().getString("sound-manager.pitch"));
    try {
      p.playSound(p.getLocation(), Sound.valueOf(soundType), volume, pitch);
    } catch (IllegalArgumentException e) {
      Bukkit.getServer().getConsoleSender().sendMessage(NamedTextColor.RED + "[PaperPing] Can not play a sound. The specified sound-type is not available in your server version, please ensure to read carefully the config file.");
    } 
  }
}