package seedu.phu.ui;

import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.phu.commons.util.StringUtil;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.Internship;


/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class ReminderBarFooter extends UiPart<Region> {

    private static final String FXML = "ReminderBarFooter.fxml";

    private static final String DEFAULT_REMINDER_TEXT = "In the next 7 days, you have: ";
    private static final String PROCESS_ASSESSMENT = "ASSESSMENT";
    private static final String PROCESS_INTERVIEW = "INTERVIEW";

    @FXML
    private Label reminderStatus;

    /**
     * Creates a {@code ReminderBarFooter}
     */
    public ReminderBarFooter(ReadOnlyInternshipBook book) {
        super(FXML);
        setReminderText(book);
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
                return LocalDate.now().isBefore(internship.getDate().value)
                        && internship.getDate().value.isBefore(LocalDate.now().plusDays(7))
                        && StringUtil.containsWordIgnoreCase(
                                String.valueOf(internship.getApplicationProcess().value), ap);
            }
        }).size();
    }

}
