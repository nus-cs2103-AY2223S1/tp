package seedu.address.testutil;

import seedu.address.model.message.Message;
import seedu.address.model.message.MessageList;

import java.util.ArrayList;
import java.util.List;

public class TypicalMessages {
    public static final String MESSAGE_HAPPY_BIRTHDAY = "Dear {name}, Happy Birthday!";
    public static final String MESSAGE_RECOMMEND_PRODUCT = "Would you be interested in trying out our new product?";

    public static final Message VALID_MESSAGE_HAPPY_BIRTHDAY = new Message(MESSAGE_HAPPY_BIRTHDAY);
    public static final Message VALID_MESSAGE_RECOMMEND_PRODUCT = new Message(MESSAGE_RECOMMEND_PRODUCT);

    public static List<Message> getTypicalMessage() {
        List<Message> messages = new ArrayList<>();
        messages.add(VALID_MESSAGE_HAPPY_BIRTHDAY);
        messages.add(VALID_MESSAGE_RECOMMEND_PRODUCT);
        return messages;
    }
}
