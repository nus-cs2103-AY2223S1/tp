package seedu.clinkedin.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static final String DEFAULT_INIT_DISPLAY = "Welcome to CLInkedIn!\n\n"
            + "CLInkedIn is a desktop contact management application for Recruiters to "
            + "manage their contact list of candidates.\n"
            + "1. Keep track of the candidates/contacts you have met through events or recruitment applications.\n"
            + "2. Tag candidates by their skills, experience and stage in the recruitment process.\n"
            + "3. Filter candidates according to a specific skill, experience or stage in the recruitment process.\n"
            + "4. Edit/remove details of the candidates/contacts.\n\n"
            + "To get started:\n"
            + "- Type 'help' to see the list of commands available.\n"
            + "- Type 'help COMMAND' to see the usage of a specific command.\n"
            + "- Type 'exit' to exit the application.\n";

    @FXML
    private TextArea resultDisplay;

    /**
     * Creates a {@code ResultDisplay} to display the result of a command execution.
     * We initialize the result display with a welcome message for users.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplay.setText(DEFAULT_INIT_DISPLAY);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
