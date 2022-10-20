package seedu.phu.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.Internship;

import java.nio.file.Paths;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class ReminderBarFooter extends UiPart<Region> {

    private static final String FXML = "ReminderBarFooter.fxml";

    @FXML
    private Label reminderStatus;

    private final static String DEFAULT_REMINDER_TEXT = "In the next 7 days, you have: ";
    private int counter = 0;

    /**
     * Creates a {@code ReminderBarFooter}
     */
    public ReminderBarFooter(ReadOnlyInternshipBook book) {
        super(FXML);
        setReminderText(book);
    }

    public void setReminderText(ReadOnlyInternshipBook book) {
        reminderStatus.setText(String.valueOf(counter++));
    }

}