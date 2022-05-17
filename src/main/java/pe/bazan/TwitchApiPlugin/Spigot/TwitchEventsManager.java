package pe.bazan.TwitchApiPlugin.Spigot;

import com.github.philippheuer.events4j.core.EventManager;
import com.github.twitch4j.TwitchClientHelper;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchEvents.ChannelGoLive;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchEvents.ChannelOffline;

public class TwitchEventsManager {
  private Plugin plugin;
  private TwitchApiClient twitchApiClient;

  public TwitchEventsManager(Plugin plugin, TwitchApiClient twitchApiClient) {
    this.plugin = plugin;
    this.twitchApiClient = twitchApiClient;
    registerEvents();
  }

  private void registerEvents() {
    EventManager eventManager = twitchApiClient.getTwitchClient().getEventManager();

    // Events
    eventManager.onEvent(ChannelGoLiveEvent.class, e -> ChannelGoLive.event(e));
    eventManager.onEvent(ChannelGoOfflineEvent.class, e -> ChannelOffline.event(e));
  }
}
