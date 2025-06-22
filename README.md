# PaperPing
PaperPing is a Paper Plugin forked from xDefcon's SpigotPing 3.0 Plugin

### Description:
Want to see your in-game ping? With this plugin you can do this by typing /ping or by pressing TAB

PaperPing allows you and your users to check their in-game ping with a simple command, /ping. The output message is customizable with prefix and colors by config. In addition to this you can enable sounds, and show players' ping in the Tab List (prefix/suffix).

<img src=https://github.com/user-attachments/assets/1c558d14-da29-4af8-b6a6-21394714207c width="300" height="75" alt="Description of image">  

PaperPing was created because Minecraft 1.20.5, 1.20.6, and 1.21 broke how xDefcon's SpigotPing 3.0 was acquiring the Minecraft Version. Starting with Minecraft 1.20.5 Java 21 is required. I assume this to be what caused the breaking issue.

##### Commands & Permissions:                                  
Permissions are checked only if you set "permission-system.enabled" in the config to true.
  - /ping (PaperPing.ping)
  - /ping <username> (PaperPing.ping.others)
  - /pingreload (PaperPing.reload)

This is the default ping message:

![image](https://github.com/woundedkoba/PaperPing/assets/174161751/fda6b3e8-0057-4cf2-8fad-81b2523f5b88)

##### Configuration:                               
You can configure almost everything of the plugin, here is the current configuration file.                                 
Config file: https://github.com/woundedkoba/PaperPing/blob/main/src/main/resources/config.yml

---------------------------------------------------------------------------------
#### Changes
See 'docs > Change Log.md' for Changes made in each version.  
A version for MC 1.20.6 was made but never commit to this repo.

#### Credit and Contribution
All credit to xDefcon for all original work.  
I only take credit for having updated this plugin for 1.21 and other items listed in 'docs > Change Log.md'.  

#### License:
SpigotPing by xDefcon is licensed under the [Gnu GPL v3](http://www.gnu.org/licenses/gpl-3.0.html)  
PaperPing adhers to the same license.

#### Resources:  
https://www.spigotmc.org/resources/spigotping-added-in-tablist-ping.24419/
https://github.com/xDefcon/spigot-ping
https://github.com/xDefcon/spigot-ping/blob/master/src/main/resources/config.yml
https://github.com/woundedkoba/PaperPing/blob/main/src/main/resources/config.yml






