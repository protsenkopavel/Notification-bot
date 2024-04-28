package net.protsenko.notificationbot.service;

public enum Commands {

    START("/start"),
    HELP("/help"),
    FEEDBACK("/feedback"),
    CREATE_NOTIFICATION("/create_notification"),
    DELETE_NOTIFICATION("/delete_notification"),
    SHOW_ALL_NOTIFICATIONS("/show_all_notifications"),;

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
