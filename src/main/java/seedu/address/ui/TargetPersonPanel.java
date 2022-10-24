package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.reminder.Reminder;

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
        targetPerson.getTagsProperty().forEach(tag ->
                tags.getChildren().add(new Label(tag.tagName)));

        // Special listener to handle Remarks
        remark.setStyle("-fx-font-style: italic");
        targetPerson.getRemarkProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                remark.setText("No remark");
                remark.setStyle("-fx-font-style: italic");
            } else {
                remark.setText(newValue);
                remark.setStyle("-fx-font-style: normal");
            }
        });

        reminderView.setItems(reminderList);
        reminderView.setCellFactory(listView -> new ReminderListViewCell());
    }
}
