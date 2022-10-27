package seedu.travelr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.travelr.model.SummaryVariables;

/**
 * Represents a Summary Card.
 */
public class SummaryCard extends UiPart<Region> {

    private static final String FXML = "SummaryCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label noTripsCompleted;
    @FXML
    private Label noEventsCompleted;
    @FXML
    private Label tripsProgress;
    @FXML
    private Label eventsProgress;
    @FXML
    private ProgressIndicator tripsProgressIndicator;
    @FXML
    private ProgressIndicator eventsProgressIndicator;
    @FXML
    private Label totalUniqueLocations;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SummaryCard(SummaryVariables summaryVariables) {
        super(FXML);
        noTripsCompleted.textProperty().bind(summaryVariables.getTotalTripsCompleted());
        noEventsCompleted.textProperty().bind(summaryVariables.getTotalEventsCompleted());
        tripsProgress.textProperty().bind(summaryVariables.getTripsProgress());
        eventsProgress.textProperty().bind(summaryVariables.getEventsProgress());
        tripsProgressIndicator.progressProperty().bind(summaryVariables.getTripProgressPercent());
        eventsProgressIndicator.progressProperty().bind(summaryVariables.getEventProgressPercent());
        totalUniqueLocations.textProperty().bind(summaryVariables.getTotalUniqueLocations().asString());

    }
}
