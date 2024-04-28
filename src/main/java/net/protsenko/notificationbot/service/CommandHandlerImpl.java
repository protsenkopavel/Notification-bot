package net.protsenko.notificationbot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.protsenko.notificationbot.bot.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static net.protsenko.notificationbot.service.Commands.START;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandlerImpl implements Handler {
    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        var message = (Message) object;
        String command = message.getText();

        switch (command) {
            case "/start" -> {
                return start(message);
            }
            case "/help" -> {}

            case "/feedback" -> {}

            case "/notification" -> {}

            default -> {
                return defaultAnswer(message);
            }
        }


        return null;
    }

    private BotApiMethod<?> start(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                        👋🏻 Приветствую в боте-напоминалке, инструменте для создания напоминаний.
                       \s
                        📍 Вы можете создать для себя текстовое напоминание, и бот в указанное время свяжется с вами для\s
                        проверки результата.
                       \s""")
                .build();
    }

    private BotApiMethod<?> defaultAnswer(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Неподдерживаемая команда")
                .build();
    }
}
