package pe.bazan.TwitchApiPlugin.Spigot.CustomEvents;

import com.github.twitch4j.common.events.domain.EventChannel;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchPlugin;

public class TwitchChannelOffline extends Event {
  private static final HandlerList handlers = new HandlerList();
  private TwitchPlugin twitchPlugin;
  private ChannelGoOfflineEvent event;

  public TwitchChannelOffline(TwitchPlugin twitchPlugin, ChannelGoOfflineEvent event) {
    this.twitchPlugin = twitchPlugin;
    this.event = event;
  }

  public EventChannel getChannel() {
    return event.getChannel();
  }

  public TwitchPlugin getTwitchApiPlugin() {
    return twitchPlugin;
  }

  public ChannelGoOfflineEvent getEvent() {
    return event;
  }

  @Override
  public String getEventName() {
    return "TwitchBotReady";
  }

  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
