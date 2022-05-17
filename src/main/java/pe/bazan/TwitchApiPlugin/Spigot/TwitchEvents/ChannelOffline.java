package pe.bazan.TwitchApiPlugin.Spigot.TwitchEvents;

import com.github.twitch4j.events.ChannelGoOfflineEvent;
import org.bukkit.Bukkit;
import pe.bazan.TwitchApiPlugin.Spigot.CustomEvents.TwitchChannelOffline;
import pe.bazan.TwitchApiPlugin.Spigot.Plugin;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchApiClient;

public class ChannelOffline {
  private static Plugin plugin;

  public ChannelOffline(Plugin plugin, TwitchApiClient client) {
    this.plugin = plugin;
  }

  public static void event(ChannelGoOfflineEvent e) {
    Bukkit.getServer().getPluginManager().callEvent(new TwitchChannelOffline(plugin, e));
  }
}
