package pe.bazan.TwitchApiPlugin.Spigot.TwitchEvents;

import com.github.twitch4j.events.ChannelGoOfflineEvent;
import org.bukkit.Bukkit;
import pe.bazan.TwitchApiPlugin.Spigot.CustomEvents.TwitchChannelOffline;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchPlugin;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchApiClient;

public class ChannelOffline {
  private static TwitchPlugin twitchPlugin;

  public ChannelOffline(TwitchPlugin twitchPlugin, TwitchApiClient client) {
    this.twitchPlugin = twitchPlugin;
  }

  public static void event(ChannelGoOfflineEvent e) {
    Bukkit.getServer().getPluginManager().callEvent(new TwitchChannelOffline(twitchPlugin, e));
  }
}
