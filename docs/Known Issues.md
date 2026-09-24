PaperPing Known Issues
-
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

PaperPing v0.0.3
- The plugin is intentionally limited to Paper; Spigot and Bukkit-only servers are not supported.
- The sound-manager configuration is retained, but the current command implementation does not play a sound.
- `/ping <username>` can only resolve online players.
- The tab-list updater runs asynchronously because it is periodic; server owners should report any platform-specific tab-list issues.
- There are no inventory screens, inventory click handlers, or Minepacks integration.
