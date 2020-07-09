package by.kharitonov.day6.view;

import by.kharitonov.day6.model.type.BookTag;
import by.kharitonov.day6.model.service.io.UserCommunicationService;

public abstract class ConsoleView {
    protected String[] inputBookConsole() {
        UserCommunicationService communicator = new UserCommunicationService();
        BookTag[] bookTags = BookTag.values();
        String[] tagValues = new String[bookTags.length];
        tagValues[0] = "";
        for (int i = 1; i < tagValues.length; i++) {
            tagValues[i] = communicator.requestEnterTag(bookTags[i]);
        }
        return tagValues;
    }

    public abstract void startView();
}
