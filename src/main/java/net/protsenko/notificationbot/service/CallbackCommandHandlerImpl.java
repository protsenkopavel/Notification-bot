package net.protsenko.notificationbot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.protsenko.notificationbot.bot.Bot;
import net.protsenko.notificationbot.entity.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackCommandHandlerImpl implements Handler{
    KeyboardFactory keyboardFactory;
    UserService userService;

    public CallbackCommandHandlerImpl(KeyboardFactory keyboardFactory, UserService userService) {
        this.keyboardFactory = keyboardFactory;
        this.userService = userService;
    }

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        var query = (CallbackQuery) object;
        String command = query.getData();

        switch (Commands.valueOf(command)) {
            case START -> {
                return start(query);
            }
            case HELP -> {
                return help(query);
            }

            case FEEDBACK -> {
                return feedback(query);
            }

            case CREATE_NOTIFICATION -> {

            }

            case SHOW_ALL_NOTIFICATIONS -> {

            }

            default -> {
                return defaultAnswer(query);
            }
        }
        return null;
    }

    private BotApiMethod<?> start(CallbackQuery query) {
        userService.checkUser(
                User.builder()
                        .chatId(query.getMessage().getChatId())
                        .registeredAt(LocalDateTime.now())
                        .userName(query.getFrom().getUserName())
                        .build());

        return SendMessage.builder()
                .chatId(query.getMessage().getChatId())
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

    private BotApiMethod<?> feedback(CallbackQuery query) {
        return null;
    }

    private BotApiMethod<?> help(CallbackQuery query) {
        return null;
    }

    private BotApiMethod<?> defaultAnswer(CallbackQuery query) {
        return SendMessage.builder()
                .chatId(query.getMessage().getChatId())
                .text("Неподдерживаемая команда")
                .build();
    }
}
