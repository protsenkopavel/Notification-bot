package net.protsenko.notificationbot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.protsenko.notificationbot.bot.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {

    CommandHandlerImpl commandHandler;
    CallbackCommandHandlerImpl callbackCommandHandler;

    public UpdateDispatcher(CommandHandlerImpl commandHandler, CallbackCommandHandlerImpl callbackCommandHandler) {
        this.commandHandler = commandHandler;
        this.callbackCommandHandler = callbackCommandHandler;
    }

    public BotApiMethod<?> distribute(Update update, Bot bot) {

        try {
            if (update.hasMessage() && update.getMessage().getText().startsWith("/")) return commandHandler.answer(update, bot);
            if (update.hasCallbackQuery() && update.getMessage().getText().startsWith("/")) return callbackCommandHandler.answer(update, bot);
            log.info("Неподдерживаемая команда: {}", update);
        } catch (Exception e) {
            //TelegramApiException e
            log.error(e.getMessage());
        }
        return null;
    }

}
