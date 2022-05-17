package pe.bazan.TwitchApiPlugin.Spigot.TwitchEvents;

import org.bukkit.Bukkit;
import pe.bazan.TwitchApiPlugin.Spigot.CustomEvents.TwitchChannelLive;
import pe.bazan.TwitchApiPlugin.Spigot.Plugin;

public class ChannelGoLive {
  private static Plugin plugin;

  public ChannelGoLive(Plugin plugin) {
    this.plugin = plugin;
  }

  public static void event(com.github.twitch4j.events.ChannelGoLiveEvent e) {
    Bukkit.getServer().getPluginManager().callEvent(new TwitchChannelLive(plugin, e));
  }
}
