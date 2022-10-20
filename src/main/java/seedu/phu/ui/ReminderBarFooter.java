package seedu.phu.ui;

import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.phu.commons.util.StringUtil;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.Internship;


/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class ReminderBarFooter extends UiPart<Region> {

    private static final String FXML = "ReminderBarFooter.fxml";
    private static final String REMINDER_ICON_PATH = "/images/reminder_icon.png";
    private static final String DEFAULT_REMINDER_TEXT = "Upcoming (in the next 7 days):  ";
    private static final String PROCESS_ASSESSMENT = "ASSESSMENT";
    private static final String PROCESS_INTERVIEW = "INTERVIEW";
    private static final int UPCOMING_DAYS = 7;

    @FXML
    private Label reminderStatus;

    @FXML
    private ImageView reminderIcon;

    /**
     * Creates a {@code ReminderBarFooter}
     */
    public ReminderBarFooter(ReadOnlyInternshipBook book) {
        super(FXML);
        setReminderText(book);
        reminderIcon.setImage(new Image(getClass().getResourceAsStream(REMINDER_ICON_PATH)));
    }

    public void setReminderText(ReadOnlyInternshipBook book) {
        String upcomingAssessments = getStatusCount(PROCESS_ASSESSMENT, book) + " " + PROCESS_ASSESSMENT + " ";
        String upcomingInterviews = getStatusCount(PROCESS_INTERVIEW, book) + " " + PROCESS_INTERVIEW + " ";
        reminderStatus.setText(DEFAULT_REMINDER_TEXT + upcomingAssessments + upcomingInterviews);
    }

    private int getStatusCount(String ap, ReadOnlyInternshipBook book) {
        return book.getInternshipList().filtered(new Predicate<Internship>() {
            @Override
            public boolean test(Internship internship) {
                return LocalDate.now().plusDays(-1).isBefore(internship.getDate().value)
                        && internship.getDate().value.isBefore(LocalDate.now().plusDays(UPCOMING_DAYS))
                        && StringUtil.containsWordIgnoreCase(
                                String.valueOf(internship.getApplicationProcess().value), ap);
            }
        }).size();
    }

}
