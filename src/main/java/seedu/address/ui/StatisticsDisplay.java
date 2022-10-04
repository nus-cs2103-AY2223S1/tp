package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import static java.util.Objects.requireNonNull;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class StatisticsDisplay extends UiPart<Region> {

    private static final String FXML = "StatisticsDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    public StatisticsDisplay() {
        super(FXML);
    }

    public void showStatisticsToUser(int numberOfStudents, int amountOwed, int amountCollected) {
        resultDisplay.setText(String.format("Number of students: %d\n" +
                "Total amount owed: $%d\n" +
                "Total amount collected: $%d", numberOfStudents, amountOwed, amountCollected));
    }

}
