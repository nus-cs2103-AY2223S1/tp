package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Controller for a statistics page
 */
public class StatisticsWindow extends UiPart<Stage> {

    // To be updated when attributes of students are added
    public static final String HEADER = "Your Teaching Statistics:                            \n\n"
            + "Total number of students: XX\n" + "Total Revenue: $XX\n" + "Total amount owed: $XX";

    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);
    private static final String FXML = "StatisticsWindow.fxml";

    private final ObservableList<Person> personList;


    @FXML
    private Label statisticsMessage;

    /**
     * Creates a new StatisticsWindow.
     *
     * @param root Stage to use as the root of the StatisticsWindow.
     * @param personList ObservableList to use as the list of students in the system.
     */
    public StatisticsWindow(Stage root, ObservableList<Person> personList) {
        super(FXML, root);
        this.personList = personList;
    }

    /**
     * Creates a new StatisticsWindow.
     */
    public StatisticsWindow(ObservableList<Person> personList) {
        this(new Stage(), personList);
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        statisticsMessage.setText(String.format("Your Teaching Statistics:                            \n\n"
                + "Total number of students: %d\n" + "Total Revenue: $%d\n" + "Total amount owed: $%d\n" +
                        "________________________________________\n" +
                        "Status correct as of: %s",
                personList.size(), 2, 3, dtf.format(now)));
    }
}
