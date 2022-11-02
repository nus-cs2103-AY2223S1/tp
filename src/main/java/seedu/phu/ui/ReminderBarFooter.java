package seedu.phu.ui;

import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Internship;


/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class ReminderBarFooter extends UiPart<Region> {

    protected static final String DEFAULT_REMINDER_TEXT = "Upcoming (in the next 7 days):  ";
    private static final String FXML = "ReminderBarFooter.fxml";
    private static final String REMINDER_ICON_PATH = "/images/reminder_icon.png";
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

    /**
     * Updates the reminder text
     */
    public void setReminderText(ReadOnlyInternshipBook book) {
        String upcomingAssessments = getStatusCount(ApplicationProcess.ApplicationProcessState.ASSESSMENT, book)
                + " " + ApplicationProcess.ApplicationProcessState.ASSESSMENT + " ";
        String upcomingInterviews = getStatusCount(ApplicationProcess.ApplicationProcessState.INTERVIEW, book)
                + " " + ApplicationProcess.ApplicationProcessState.INTERVIEW + " ";
        String pendingOffers = getStatusCount(ApplicationProcess.ApplicationProcessState.OFFER, book)
                + " " + ApplicationProcess.ApplicationProcessState.OFFER + " ";
        reminderStatus.setText(DEFAULT_REMINDER_TEXT + upcomingAssessments + upcomingInterviews + pendingOffers);
    }

    protected int getStatusCount(ApplicationProcess.ApplicationProcessState ap, ReadOnlyInternshipBook book) {
        return book.getInternshipList().filtered(new Predicate<Internship>() {
            @Override
            public boolean test(Internship internship) {
                return LocalDate.now().plusDays(-1).isBefore(internship.getDate().value)
                        && internship.getDate().value.isBefore(LocalDate.now().plusDays(UPCOMING_DAYS))
                        && internship.getApplicationProcess().value.equals(ap);
            }
        }).size();
    }

}
