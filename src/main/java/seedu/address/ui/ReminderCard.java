package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.reminder.Reminder;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderCard.fxml";
    private static final String EXPIRED_STYLE_CLASS = "expired";

    public final Reminder reminder;

    @FXML
    private Label description;
    @FXML
    private Label dateText;
    @FXML
    private Label name;
    @FXML
    private Label phone;

    /**
     * Creates a {@code ReminderCard} with the given {@code Reminder} to display.
     * @param reminder
     */
    public ReminderCard(Reminder reminder, int displayedIndex) {
        super(FXML);
        this.reminder = reminder;
        description.setText(String.valueOf(displayedIndex) + ". " + reminder.getDescription());
        dateText.setText(reminder.getDateTimeString());
        name.setText(reminder.getNameString());
        phone.setText(reminder.getPhoneString());
        if (reminder.isExpired()) {
            this.getRoot().getStyleClass().add(EXPIRED_STYLE_CLASS);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return description.getText().equals(card.description.getText())
                && dateText.getText().equals(card.dateText.getText())
                && name.getText().equals(card.name.getText())
                && phone.getText().equals(card.phone.getText());
    }
}
