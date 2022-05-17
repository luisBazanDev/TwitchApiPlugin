package pe.bazan.TwitchApiPlugin.Spigot.CustomEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TwitchBotReady extends Event {
  private static final HandlerList handlers = new HandlerList();

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
