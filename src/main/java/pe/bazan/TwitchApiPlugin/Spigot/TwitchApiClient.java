package pe.bazan.TwitchApiPlugin.Spigot;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import org.bukkit.configuration.ConfigurationSection;

public class TwitchApiClient {
  private TwitchClient twitchClient;
  private TwitchPlugin twitchPlugin;
  private TwitchEventsManager eventsManager;

  public TwitchApiClient (TwitchPlugin twitchPlugin) {
    this.twitchPlugin = twitchPlugin;
    twitchPlugin.getLogger().info("Starting client...");
    initializeClient();
    eventsManager = new TwitchEventsManager(twitchPlugin, this);
    twitchPlugin.getLogger().info("Client started.");
  }

  private void initializeClient() {
    ConfigurationSection credentials = twitchPlugin.getConfig().createSection("credentials");
    OAuth2Credential oAuth2Credential = new OAuth2Credential(
            credentials.getString("identityProvider"),
            credentials.getString("accessToken")
    );
    TwitchClientBuilder builder = TwitchClientBuilder.builder()
            .withClientId(credentials.getString("clientId"))
            .withClientSecret(credentials.getString("clientSecret"))
            .withEnablePubSub(twitchPlugin.getConfig().getBoolean("widgets.pubsub"))
            .withEnableChat(twitchPlugin.getConfig().getBoolean("widgets.chat"))
            .withEnableHelix(twitchPlugin.getConfig().getBoolean("widgets.helix"))
            .withEnableKraken(twitchPlugin.getConfig().getBoolean("widgets.kraken"))
            .withEnableTMI(twitchPlugin.getConfig().getBoolean("widgets.tmi"))
            .withDefaultAuthToken(oAuth2Credential);
    if(builder.getEnableChat()) {
      builder = builder.withChatAccount(oAuth2Credential);
    }

    twitchClient = builder.build();

    twitchPlugin.getLogger().info(String.format("Enable helix: %s", twitchPlugin.getConfig().getBoolean("widgets.helix")));
    twitchPlugin.getLogger().info(String.format("Enable chat: %s", twitchPlugin.getConfig().getBoolean("widgets.chat")));
    twitchPlugin.getLogger().info(String.format("Enable kraken: %s", twitchPlugin.getConfig().getBoolean("widgets.kraken")));
    twitchPlugin.getLogger().info(String.format("Enable pubsub: %s", twitchPlugin.getConfig().getBoolean("widgets.pubsub")));
    twitchPlugin.getLogger().info(String.format("Enable tmi: %s", twitchPlugin.getConfig().getBoolean("widgets.tmi")));
  }

  public TwitchClient getTwitchClient() {
    return twitchClient;
  }

  public void joinChat(String channel) {
    try {
      twitchClient.getChat().joinChannel(channel);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void listenStream(String channel) {
    twitchClient.getClientHelper().enableStreamEventListener(channel);
  }

  public TwitchPlugin getPlugin() {
    return twitchPlugin;
  }
}
