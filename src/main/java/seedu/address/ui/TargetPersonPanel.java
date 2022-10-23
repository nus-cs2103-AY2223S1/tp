package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Panel containing the target person from show command.
 */
public class TargetPersonPanel extends UiPart<Region> {
    private static final String FXML = "TargetPersonPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TargetPersonPanel.class);

    @FXML
    private ListView<Person> targetPersonView;
    @FXML
    private ListView<Reminder> reminderView;

    /**
     * Creates a {@code TargetPersonPanel} with the given {@code ObservableList}.
     */
    public TargetPersonPanel(ObservableList<Person> personList, ObservableList<Reminder> reminderList) {
        super(FXML);
        targetPersonView.setItems(personList);
        targetPersonView.setCellFactory(listView -> new TargetPersonListViewCell());
        reminderView.setItems(reminderList);
        reminderView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TargetPersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TargetPersonCard(person).getRoot());
            }
        }
    }

}
