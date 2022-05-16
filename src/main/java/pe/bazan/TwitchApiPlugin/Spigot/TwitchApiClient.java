package pe.bazan.TwitchApiPlugin.Spigot;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;

public class TwitchApiClient {
  private TwitchClient twitchClient;
  private Plugin plugin;

  public TwitchApiClient (Plugin plugin) {
    this.plugin = plugin;
  }

  private void initializeClient() {
    TwitchClientBuilder builder = TwitchClientBuilder.builder();
    builder.withEnableHelix(plugin.getConfig().getBoolean("widgets.helix"));
    builder.withEnableChat(plugin.getConfig().getBoolean("widgets.chat"));
    builder.withEnableKraken(plugin.getConfig().getBoolean("widgets.kraken"));
    builder.withEnablePubSub(plugin.getConfig().getBoolean("widgets.pubsub"));
    builder.withEnableTMI(plugin.getConfig().getBoolean("widgets.tmi"));
  }
}
