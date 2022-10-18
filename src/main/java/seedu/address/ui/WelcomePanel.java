package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**
 * A UI component that displays a welcome message and upcoming {@code Reminder}s.
 */
public class WelcomePanel extends UiPart<Region> {
    private static final String FXML = "WelcomePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label welcomeMessage;
    @FXML
    private ListView<TempReminder> reminderView;

    /**
     * Creates a {@code WelcomePanel}
     */
    public WelcomePanel() {
        super(FXML);
        ObservableList<TempReminder> remindersPlaceholder = FXCollections.observableArrayList(
            new TempReminder("Reminder 1", LocalDateTime.now()),
            new TempReminder("Reminder 2", LocalDateTime.now().plusDays(1)),
            new TempReminder("Reminder 3 which is a very long reminder just to test"
                    + " whether the text UI component will warp.", LocalDateTime.now().plusDays(4)),
            new TempReminder("Reminder 4", LocalDateTime.now().plusDays(3)),
            new TempReminder("Reminder 5", LocalDateTime.now().plusDays(2))
        );
        reminderView.setItems(remindersPlaceholder);
        reminderView.setCellFactory(listView -> new TempReminderListViewCell());
        // TODO update constructor to initialise with ObservableList<Reminder>
    }

    public void setWelcomeMessage(String message) {
        welcomeMessage.setText(message);
    }
}

// TODO remove the following temporary code once Reminder model is finalised
class TempReminder {
    private String description;
    private LocalDateTime dateTime;

    public TempReminder(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Reminder} using a {@code ReminderCard}.
 */
class TempReminderListViewCell extends ListCell<TempReminder> {
    private static final String PLACEHOLDER_TEXT = "PLACEHOLDER REMINDER";
    private final StackPane pane;

    // code adapted from https://ciruman.wordpress.com/2015/04/08/javafx-listview-without-horizontal-scroll/
    public TempReminderListViewCell() {
        pane = new StackPane();
        pane.setMinWidth(0);
        pane.setPrefWidth(1);
        pane.setMinHeight(0);
        pane.getChildren().add(new ReminderCard(
                new TempReminder(PLACEHOLDER_TEXT, LocalDateTime.now())).getRoot());
    }

    @Override
    protected void updateItem(TempReminder reminder, boolean empty) {
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
