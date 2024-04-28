package net.protsenko.notificationbot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.protsenko.notificationbot.bot.Bot;
import net.protsenko.notificationbot.entity.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandlerImpl implements Handler {

    KeyboardFactory keyboardFactory;
    UserService userService;

    public CommandHandlerImpl(KeyboardFactory keyboardFactory, UserService userService) {
        this.keyboardFactory = keyboardFactory;
        this.userService = userService;
    }

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        Message message = (Message) object;
        String command = message.getText();

        switch (command) {
            case "/start" -> {
                return start(message);
            }
            case "/help" -> {
                return help(message);
            }

            case "/feedback" -> {
                return feedback(message);
            }

            case "/create_notification" -> {

            }

            case "/show_all_notifications" -> {

            }

            default -> {
                return defaultAnswer(message);
            }
        }
        return null;
    }

    private BotApiMethod<?> start(Message message) {
        userService.save(
                User.builder()
                        .chatId(message.getChatId())
                        .registeredAt(LocalDateTime.now())
                        .userName(message.getFrom().getUserName())
                        .build());

        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("""
                         👋🏻 Приветствую в боте-напоминалке, инструменте для создания напоминаний.
                        \s
                         📍 Вы можете создать для себя текстовое напоминание, и бот в указанное время свяжется с вами для\s
                         проверки результата.
                        \s""")
                .replyMarkup(
                        keyboardFactory.getReplyKeyboard(
                                List.of("Создать напоминание", "Мои напоминания"),
                                List.of(2)
                        )
                )
                .build();
    }

    private BotApiMethod<?> feedback(Message message) {
        return null;
    }

    private BotApiMethod<?> help(Message message) {
        return null;
    }

    private BotApiMethod<?> defaultAnswer(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Неподдерживаемая команда")
                .build();
    }
}
