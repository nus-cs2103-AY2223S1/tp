package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.message.Message.NAME_PLACEHOLDER;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.message.Message;

/**
 * An UI component that displays a single {@code Message} template.
 */
public class MessageTemplateCard extends UiPart<Region> {

    private static final String FXML = "MessageTemplateCard.fxml";
    private static final String NAME_PLACEHOLDER_STYLE_CLASS = "message-name-placeholder";
    private static final String NAME_PLACEHOLDER_REGEX = NAME_PLACEHOLDER
            .replace("{", "\\{")
            .replace("}", "\\}");
    private Message message;

    @FXML
    private TextFlow messageText;

    /**
     * Creates a {@code MessageTemplateCard} with the given {@code Message} to display.
     * @param message
     */
    public MessageTemplateCard(int index, Message message) {
        super(FXML);
        requireAllNonNull(message, message.getMessage());
        this.message = message;
        messageText.getChildren().add(new Text(Integer.toString(index) + ". "));

        // Italise all instances of "{name}"
        String[] messageParts = message.getMessage().split(NAME_PLACEHOLDER_REGEX);
        for (String part : messageParts) {
            messageText.getChildren().add(new Text(part));
            if (part != messageParts[messageParts.length - 1]) {
                Text namePlaceholderText = new Text(NAME_PLACEHOLDER);
                namePlaceholderText.getStyleClass().add(NAME_PLACEHOLDER_STYLE_CLASS);
                messageText.getChildren().add(namePlaceholderText);
            }
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MessageTemplateCard)) {
            return false;
        }

        // state check
        MessageTemplateCard card = (MessageTemplateCard) other;
        return message.equals(card.message);
    }
}
