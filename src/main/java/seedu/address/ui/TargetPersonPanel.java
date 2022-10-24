package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the target person from show command.
 */
public class TargetPersonPanel extends UiPart<Region> {
    private static final String FXML = "TargetPersonPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TargetPersonPanel.class);
    private TargetPerson targetPerson;

    @FXML
    private ListView<Reminder> reminderView;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TargetPersonPanel} with the given {@code ObservableList}.
     */
    public TargetPersonPanel(TargetPerson person, ObservableList<Reminder> reminderList) {
        super(FXML);
        this.targetPerson = person;
        name.textProperty().bind(targetPerson.getNameProperty());
        phone.textProperty().bind(targetPerson.getPhoneProperty());
        address.textProperty().bind(targetPerson.getAddressProperty());
        email.textProperty().bind(targetPerson.getEmailProperty());

        // Special listener to handle tags
        targetPerson.getTagsProperty().addListener(tagSetListener());

        // Special listener to handle Remarks
        remark.setStyle("-fx-font-style: italic");
        targetPerson.getRemarkProperty().addListener(remarkListener());

        initializeReminderView(reminderList);
    }

    /**
     * Initializes the reminder list view.
    */
    private void initializeReminderView(ObservableList<Reminder> reminderList) {
        reminderView.setItems(reminderList);
        reminderView.setCellFactory(listView -> new ReminderListViewCell());
        Label reminderPlaceholder = new Label("No upcoming reminders.");
        reminderPlaceholder.getStyleClass().add("placeholder");
        reminderView.setPlaceholder(reminderPlaceholder);
    }

    /**
     * Returns a listener that updates the remark Label when targetPerson changes.
     * @return
     */
    private ChangeListener<String> remarkListener() {
        return (observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                remark.setText("No remark");
                remark.setStyle("-fx-font-style: italic");
            } else {
                remark.setText(newValue);
                remark.setStyle("-fx-font-style: normal");
            }
        };
    }

    /**
     * Returns a listener that updates the tags FlowPane when targetPerson changes.
     * @return
     */
    private SetChangeListener<Tag> tagSetListener() {
        return change -> {
            tags.getChildren().clear();
            // Same code as in PersonCard
            change.getSet().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        };
    }
}
