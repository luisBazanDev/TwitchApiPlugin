package pe.bazan.TwitchApiPlugin.Spigot;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import org.bukkit.configuration.ConfigurationSection;

public class TwitchApiClient {
  private static TwitchClient twitchClient;
  private TwitchPlugin twitchPlugin;
  private TwitchEventsManager eventsManager;

  public TwitchApiClient (TwitchPlugin twitchPlugin) {
    this.twitchPlugin = twitchPlugin;
    twitchPlugin.getLogger().info("Starting client...");
    initializeClient();
    eventsManager = new TwitchEventsManager(twitchPlugin, this);
    joinChat("sintcraft");
    twitchPlugin.getLogger().info("Client started.");
  }

  private void initializeClient() {
    TwitchClientBuilder builder = TwitchClientBuilder.builder();
    builder.withEnableHelix(twitchPlugin.getConfig().getBoolean("widgets.helix"));
    builder.withEnableChat(twitchPlugin.getConfig().getBoolean("widgets.chat"));
    builder.withEnableKraken(twitchPlugin.getConfig().getBoolean("widgets.kraken"));
    builder.withEnablePubSub(twitchPlugin.getConfig().getBoolean("widgets.pubsub"));
    builder.withEnableTMI(twitchPlugin.getConfig().getBoolean("widgets.tmi"));

    twitchPlugin.getLogger().info(String.format("Enable helix: %s", twitchPlugin.getConfig().getBoolean("widgets.helix")));
    twitchPlugin.getLogger().info(String.format("Enable chat: %s", twitchPlugin.getConfig().getBoolean("widgets.chat")));
    twitchPlugin.getLogger().info(String.format("Enable kraken: %s", twitchPlugin.getConfig().getBoolean("widgets.kraken")));
    twitchPlugin.getLogger().info(String.format("Enable pubsub: %s", twitchPlugin.getConfig().getBoolean("widgets.pubsub")));
    twitchPlugin.getLogger().info(String.format("Enable tmi: %s", twitchPlugin.getConfig().getBoolean("widgets.tmi")));

    builder = setCredentials(builder);

    twitchClient = builder.build();
  }

  private TwitchClientBuilder setCredentials(TwitchClientBuilder builder) {
    ConfigurationSection credentials = twitchPlugin.getConfig().createSection("credentials");
    builder.withClientId(credentials.getString("clientId"));
    builder.withClientSecret(credentials.getString("clientId"));
    if(twitchPlugin.getConfig().getBoolean("widgets.chat")) {
      twitchPlugin.getLogger().info("Chat credentials setting...");
      OAuth2Credential oAuth2Credential = new OAuth2Credential(
              credentials.getString("identityProvider"),
              credentials.getString("accessToken")
      );
      builder.withDefaultAuthToken(oAuth2Credential);
      builder.withChatAccount(oAuth2Credential);
      twitchPlugin.getLogger().info("Chat credentials set.");
    }
    return builder;
  }

  public TwitchClient getTwitchClient() {
    return twitchClient;
  }

  public static void joinChat(String channel) {
    try {
      twitchClient.getChat().joinChannel(channel);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void listenStream(String channel) {
    twitchClient.getClientHelper().enableStreamEventListener(channel);
  }

  public TwitchPlugin getPlugin() {
    return twitchPlugin;
  }
}
