package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A UI for the statistics display that is displayed at the top right of the application.
 */
public class StatisticsDisplay extends UiPart<Region> {

    private static final String FXML = "StatisticsDisplay.fxml";

    @FXML
    private TextArea statisticsDisplay;

    public StatisticsDisplay() {
        super(FXML);
    }

    /**
     * Displays the statistics of the AddressBook to the user.
     */
    public void showStatisticsToUser(int numberOfStudents, String amountOwed, String amountCollected) {
        statisticsDisplay.setText(String.format("Number of students: %d\n"
                + "Total amount owed: %s\n"
                + "Total amount collected: %s", numberOfStudents, amountOwed, amountCollected));
    }

}
