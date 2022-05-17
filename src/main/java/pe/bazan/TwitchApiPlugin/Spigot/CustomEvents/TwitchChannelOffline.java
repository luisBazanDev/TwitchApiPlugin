package pe.bazan.TwitchApiPlugin.Spigot.CustomEvents;

import com.github.twitch4j.common.events.domain.EventChannel;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.events.ChannelGoOfflineEvent;
import com.github.twitch4j.helix.domain.Stream;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pe.bazan.TwitchApiPlugin.Spigot.Plugin;

public class TwitchChannelOffline extends Event {
  private static final HandlerList handlers = new HandlerList();
  private Plugin plugin;
  private ChannelGoOfflineEvent event;

  public TwitchChannelOffline(Plugin plugin, ChannelGoOfflineEvent event) {
    this.plugin = plugin;
    this.event = event;
  }

  public EventChannel getChannel() {
    return event.getChannel();
  }

  public Plugin getTwitchApiPlugin() {
    return plugin;
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
