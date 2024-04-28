package net.protsenko.notificationbot.bot;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.protsenko.notificationbot.config.TelegramProperties;
import net.protsenko.notificationbot.service.UpdateDispatcher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Bot extends TelegramWebhookBot{

    TelegramProperties telegramProperties;
    UpdateDispatcher dispatcher;

    public Bot(TelegramProperties telegramProperties, UpdateDispatcher dispatcher) {
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
        this.dispatcher = dispatcher;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return dispatcher.distribute(update, this);
    }

    @Override
    public String getBotPath() {
        return telegramProperties.getPath();
    }

    @Override
    public String getBotUsername() {
        return telegramProperties.getUsername();
    }
}
