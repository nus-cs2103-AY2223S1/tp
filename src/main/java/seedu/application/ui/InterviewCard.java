package seedu.application.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;

/**
 * A UI component that displays information of an {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Interview interview;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label companyAndPosition;
    @FXML
    private Label round;
    @FXML
    private Label interviewDate;
    @FXML
    private Label interviewTime;
    @FXML
    private Label interviewLocation;

    /**
     * Creates an {@code InterviewCard} with the given {@code Application} and displayedIndex to display.
     */
    public InterviewCard(Application application, int displayedIndex) {
        super(FXML);
        assert application.hasInterview();
        this.interview = application.getInterview().get();
        id.setText("i" + displayedIndex + ". ");
        companyAndPosition.setText(application.getCompany().company + ", " + application.getPosition().value);
        round.setText(interview.getRound().value);
        interviewDate.setText(interview.getInterviewDate().toString());
        interviewTime.setText(interview.getInterviewTime().toString());
        interviewLocation.setText(interview.getLocation().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewCard)) {
            return false;
        }

        // state check
        InterviewCard card = (InterviewCard) other;
        return interview.equals(card.interview);
    }
}
