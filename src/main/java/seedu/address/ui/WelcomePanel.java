package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.reminder.Reminder;

/**
 * A UI component that displays a welcome message and upcoming {@code Reminder}s.
 */
public class WelcomePanel extends UiPart<Region> {
    private static final String FXML = "WelcomePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label welcomeMessage;
    @FXML
    private ListView<Reminder> reminderView;

    /**
     * Creates a {@code WelcomePanel}
     */
    public WelcomePanel(ObservableList<Reminder> reminderList) {
        super(FXML);
        reminderView.setItems(reminderList);
        reminderView.setCellFactory(listView -> new ReminderListViewCell());

        Label reminderPlaceholder = new Label("No upcoming reminders.");
        reminderPlaceholder.getStyleClass().add("placeholder");
        reminderView.setPlaceholder(reminderPlaceholder);
    }

    public void setWelcomeMessage(String message) {
        welcomeMessage.setText(message);
    }
}

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Reminder} using a {@code ReminderCard}.
 */
class ReminderListViewCell extends ListCell<Reminder> {
    private final StackPane pane;

    // code adapted from https://ciruman.wordpress.com/2015/04/08/javafx-listview-without-horizontal-scroll/
    public ReminderListViewCell() {
        pane = new StackPane();
        pane.setMinWidth(0);
        pane.setPrefWidth(1);
        pane.setMinHeight(0);
    }

    @Override
    protected void updateItem(Reminder reminder, boolean empty) {
        super.updateItem(reminder, empty);

        if (empty || reminder == null) {
            setGraphic(null);
            setText(null);
        } else {
            pane.getChildren().clear();
            pane.getChildren().add(new ReminderCard(reminder).getRoot());
            setGraphic(pane);
        }
    }
}
