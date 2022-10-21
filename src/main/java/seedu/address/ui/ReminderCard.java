package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderCard.fxml";

    public final TempReminder reminder;

    @FXML
    private Label description;
    @FXML
    private Label dateText;

    /**
     * Creates a {@code ReminderCard} with the given {@code Reminder} to display.
     * @param reminder
     */
    public ReminderCard(TempReminder reminder) {
        super(FXML);
        this.reminder = reminder;
        description.setText(reminder.getDescription());
        dateText.setText(reminder.getDateTime().toString());
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
                && dateText.getText().equals(card.dateText.getText());
    }
}
