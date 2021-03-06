package pe.bazan.TwitchApiPlugin.Spigot.CustomEvents;

import com.github.twitch4j.common.events.domain.EventChannel;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.helix.domain.Stream;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pe.bazan.TwitchApiPlugin.Spigot.TwitchPlugin;

public class TwitchChannelLive extends Event {
  private static final HandlerList handlers = new HandlerList();
  private TwitchPlugin twitchPlugin;
  private ChannelGoLiveEvent event;

  public TwitchChannelLive(TwitchPlugin twitchPlugin, ChannelGoLiveEvent event) {
    this.twitchPlugin = twitchPlugin;
    this.event = event;
  }

  public boolean titleCointains(String title) {
    return event.getStream().getTitle().contains(title);
  }

  public Stream getStream() {
    return event.getStream();
  }

  public EventChannel getChannel() {
    return event.getChannel();
  }

  public TwitchPlugin getTwitchApiPlugin() {
    return twitchPlugin;
  }

  public ChannelGoLiveEvent getEvent() {
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
