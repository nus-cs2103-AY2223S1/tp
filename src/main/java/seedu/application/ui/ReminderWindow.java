package seedu.application.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.application.Application;

/**
 * Controller for a reminder page
 */
public class ReminderWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String FXML = "ReminderWindow.fxml";

    @FXML
    private ListView<Application> upcomingInterviewListView;

    /**
     * Creates a new ReminderWindow.
     *
     * @param root Stage to use as the root of the ReminderWindow.
     */
    public ReminderWindow(Stage root, ObservableList<Application> applicationListWithUpcomingInterview) {
        super(FXML, root);

        root.setHeight(400);
        root.setWidth(400);

        for (Application application : applicationListWithUpcomingInterview) {
            assert application.hasInterview(); //ensure every application does not contain empty interview
        }
        upcomingInterviewListView.setItems(applicationListWithUpcomingInterview);
        upcomingInterviewListView.setCellFactory(listView -> new InterviewListPanel.InterviewListViewCell());
    }

    /**
     * Creates a {@code ReminderWindow} with the given {@code ObservableList}.
     */
    public ReminderWindow(ObservableList<Application> applicationListWithUpcomingInterview) {
        this(new Stage(), applicationListWithUpcomingInterview);
    }

    /**
     * Shows the reminder window.
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
        logger.fine("Showing reminder page for upcoming interviews happening 1 week from now.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the reminder window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the reminder window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the reminder window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
