package pe.bazan.TwitchApiPlugin.Spigot.TwitchEvents;

import org.bukkit.Bukkit;
import pe.bazan.TwitchApiPlugin.Spigot.CustomEvents.TwitchChannelLive;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchPlugin;

public class ChannelGoLive {
  private static TwitchPlugin twitchPlugin;

  public ChannelGoLive(TwitchPlugin twitchPlugin) {
    this.twitchPlugin = twitchPlugin;
  }

  public static void event(com.github.twitch4j.events.ChannelGoLiveEvent e) {
    Bukkit.getServer().getPluginManager().callEvent(new TwitchChannelLive(twitchPlugin, e));
  }
}
