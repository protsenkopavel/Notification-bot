package net.protsenko.notificationbot.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.protsenko.notificationbot.bot.Bot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {

    Bot bot;

    public Controller(Bot bot) {
        this.bot = bot;
    }

    @PostMapping("/")
    public BotApiMethod<?> listener(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }

}
