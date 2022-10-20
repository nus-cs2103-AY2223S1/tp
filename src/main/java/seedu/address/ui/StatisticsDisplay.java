package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.StatisticsCalculator;

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
     * Displays the statistics of the TeachersPet to the user.
     */
    public void showStatisticsToUser(int numberOfStudents, String amountOwed, String amountCollected) {
        statisticsDisplay.setText(String.format("Number of students: %d\n"
                + "Total amount owed: %s\n"
                + "Total amount collected: %s", numberOfStudents, amountOwed, amountCollected));
    }

    /**
     * Displays the statistics of the TeachersPet to the user.
     *
     * @param statisticsCalculator the calculator to calculate statistical values.
     */
    public void showStatisticsToUser(StatisticsCalculator statisticsCalculator) {
        int numberOfStudents = statisticsCalculator.getSize();
        String amountOwed = statisticsCalculator.getAmountOwed();
        String amountCollected = statisticsCalculator.getAmountPaid();
        statisticsDisplay.setText(String.format("Number of students: %d\n"
                + "Total amount owed: %s\n"
                + "Total amount collected: %s", numberOfStudents, amountOwed, amountCollected));
    }
}
