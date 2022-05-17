package pe.bazan.TwitchApiPlugin.Spigot;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import org.bukkit.configuration.ConfigurationSection;

public class TwitchApiClient {
  private static TwitchClient twitchClient;
  private Plugin plugin;
  private TwitchEventsManager eventsManager;
  private static TwitchApiClient instance;

  public TwitchApiClient (Plugin plugin) {
    this.plugin = plugin;
    plugin.getLogger().info("Starting client...");
    initializeClient();
    eventsManager = new TwitchEventsManager(plugin, this);
    plugin.getLogger().info("Client started.");
    instance = this;
  }

  private void initializeClient() {
    TwitchClientBuilder builder = TwitchClientBuilder.builder();
    builder.withEnableHelix(plugin.getConfig().getBoolean("widgets.helix"));
    builder.withEnableChat(plugin.getConfig().getBoolean("widgets.chat"));
    builder.withEnableKraken(plugin.getConfig().getBoolean("widgets.kraken"));
    builder.withEnablePubSub(plugin.getConfig().getBoolean("widgets.pubsub"));
    builder.withEnableTMI(plugin.getConfig().getBoolean("widgets.tmi"));

    setCredentials(builder);

    twitchClient = builder.build();
  }

  private void setCredentials(TwitchClientBuilder builder) {
    ConfigurationSection credentials = plugin.getConfig().createSection("credentials");
    builder.withClientId(credentials.getString("clientId"));
    builder.withClientSecret(credentials.getString("clientId"));
    if(plugin.getConfig().getBoolean("widgets.chat")) {
      OAuth2Credential oAuth2Credential = new OAuth2Credential(
              credentials.getString("identityProvider"),
              credentials.getString("accessToken")
      );
      builder.withDefaultAuthToken(oAuth2Credential);
      builder.withChatAccount(oAuth2Credential);
    }
  }

  public TwitchClient getTwitchClient() {
    return twitchClient;
  }

  public static void joinChat(String channel) {
    twitchClient.getChat().joinChannel(channel);
  }

  public static void listenStream(String channel) {
    twitchClient.getClientHelper().enableStreamEventListener(channel);
  }

  public Plugin getPlugin() {
    return plugin;
  }

  public static TwitchApiClient getInstance() {
    return instance;
  }
}
