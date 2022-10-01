package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAddressBook;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Controller for a statistics page
 */
public class StatisticsWindow extends UiPart<Stage> {

    private static final int INDEX_OF_STUDENT_COUNT = 0;
    private static final int INDEX_OF_MONEY_COLLECTED = 1;
    private static final int INDEX_OF_MONEY_OWED = 2;
    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";
    private final ReadOnlyAddressBook addressBook;

    @FXML
    private Button refreshButton;
    @FXML
    private Label statisticsMessage;

    /**
     * Creates a new StatisticsWindow.
     *
     * @param root Stage to use as the root of the StatisticsWindow.
     * @param addressBook AddressBook used to calculate the statistics.
     */
    public StatisticsWindow(Stage root, ReadOnlyAddressBook addressBook) {
        super(FXML, root);
        this.addressBook = addressBook;
    }

    /**
     * Creates a new StatisticsWindow.
     */
    public StatisticsWindow(ReadOnlyAddressBook addressBook) {
        this(new Stage(), addressBook);
    }

    /**
     * Shows the statistics window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing statistics page about the application.");
        updateStatisticsMessage();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the statistics window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the statistics window.
     */
    public void focus() {
        updateStatisticsMessage();
        getRoot().requestFocus();
    }

    /**
     * Updates the message to show the latest statistical values.
     */
    public void updateStatisticsMessage() {
        int[] statistics = addressBook.getStatistics();

        int studentCount = statistics[INDEX_OF_STUDENT_COUNT];
        int moneyCollected = statistics[INDEX_OF_MONEY_COLLECTED];
        int moneyOwed = statistics[INDEX_OF_MONEY_OWED];

        String dateAndTime = getDateAndTime();

        statisticsMessage.setText(String.format("Your Teaching Statistics:\n\n"
                + "Total number of students: %d\n" + "Total Revenue: $%d\n" + "Total amount owed: $%d\n" +
                        "________________________________________\n" +
                        "Status correct as of: %s",
                studentCount, moneyCollected, moneyOwed, dateAndTime));
    }

    /**
     * Returns current date and time.
     */
    public String getDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Refreshes the statistical values.
     */
    @FXML
    private void refreshStatistics() {
        updateStatisticsMessage();
    }
}
