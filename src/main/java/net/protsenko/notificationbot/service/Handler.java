package net.protsenko.notificationbot.service;

import net.protsenko.notificationbot.bot.Bot;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface Handler {

    public BotApiMethod<?> answer(BotApiObject object, Bot bot);

}
