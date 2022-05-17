package pe.bazan.TwitchApiPlugin.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
  private static TwitchApiClient twitchApiClient;
  @Override
  public void onLoad() {
    twitchApiClient = new TwitchApiClient(this);
  }

  @Override
  public void onEnable() {
  }

  @Override
  public void onDisable() {
    twitchApiClient.getTwitchClient().close();
  }
}
