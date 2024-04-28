package net.protsenko.notificationbot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramProperties {

    String token;
    String path;
    String username;

}
