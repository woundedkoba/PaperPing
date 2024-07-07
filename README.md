# PaperPing
A fork of xDefcon's SpigotPing 3.0 Plugin

Want to see your in-game ping? With this plugin you can do this by typing /ping or by pressing TAB

SpigotPing is a Bukkit/Spigot/Paper plugin that allows you and your users to check their in-game ping with a simple command, /ping. The output message is customizable with prefix and colors by config. In addition to this you can enable sounds, and show players' ping in the Tab List (prefix/suffix).

![image](https://github.com/woundedkoba/PaperPing/assets/174161751/67ca95af-87e4-454b-9cb8-7df685832ef0)

Commands & Permissions:                                  
Permissions are checked only if you set "permission-system.enabled" in the config to true.
  - /ping (PaperPing.ping)
  - /ping <username> (PaperPing.ping.others)
  - /pingreload (PaperPing.reload)

This is the default ping message:

![image](https://github.com/woundedkoba/PaperPing/assets/174161751/fda6b3e8-0057-4cf2-8fad-81b2523f5b88)

Configuration:                               
You can configure almost everything of the plugin, here is the current configuration file.                                 
Config file: https://github.com/woundedkoba/PaperPing/blob/main/config.yml

All credit to xDefcon for all original work. I only take credit for having updated this plugin for MC 1.20.6 & 1.21 and other items listed below.

Without going into full detail changes include but are not limited to:
- Upgraded to Java 21 (xDefcon's SpigotPing 3.0 uses Java 8)
- Upgraded to Paper API 1.21-R0.1-SNAPSHOT (xDefcon's SpigotPing 3.0 used Spigot API 1.17-R0.1-SNAPSHOT)
- Upgraded to Maven Compiler Plugin 3.13.0
- Removed JSON.simple 1.1.1 (com.googlecode.json-simple)
- Removed the use of bStats and Metrics
- Implemented the use of the Adventure Platform API (to replace the use of ChatColor from the Spigot API as this is deprecated for the Paper API) ; forgot to replace the use of ChatColor.translateAlternateColorCodes(), will do so in the next release
- Added some instances of @SuppressWarnings("deprecation") due to my use of the Paper API versus the Spigot API ; will resolve the deprecations in the next release
- BUGFIX: Re-wrote how PingUtil acquires the Minecraft Version (This was causing the plugin to fail to work and output errors in the server console for MC 1.20.5, 1.20.6, and 1.21)










