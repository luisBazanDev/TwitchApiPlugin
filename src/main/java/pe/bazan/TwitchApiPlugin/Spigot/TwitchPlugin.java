package pe.bazan.TwitchApiPlugin.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class TwitchPlugin extends JavaPlugin {
  private static TwitchApiClient twitchApiClient;
  @Override
  public void onLoad() {
    saveDefaultConfig();
    twitchApiClient = new TwitchApiClient(this);
  }

  @Override
  public void onEnable() {
  }

  @Override
  public void onDisable() {
    twitchApiClient.getTwitchClient().close();
  }

  public static TwitchApiClient getTwitchApiClient() {
    return twitchApiClient;
  }
}
