package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;

/**
 * Panel containing the list of persons to display reminders about the persons.
 */
public class ReminderListPanel extends UiPart<Region> {
    private static final String FXML = "ReminderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReminderListPanel.class);

    @FXML
    private ListView<Pair<Person, Reminder>> reminderListView;

    /**
     * Creates a {@code ReminderListPanel} with the given {@code ObservableList}.
     */
    public ReminderListPanel(SortedList<Pair<Person, Reminder>> personList) {
        super(FXML);
        reminderListView.setItems(personList);
        reminderListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pair<Person, Reminder>}
     * using a {@code ReminderCard}.
     */
    class ReminderListViewCell extends ListCell<Pair<Person, Reminder>> {
        @Override
        protected void updateItem(Pair<Person, Reminder> personReminderPair, boolean empty) {
            super.updateItem(personReminderPair, empty);

            if (empty || personReminderPair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(personReminderPair.getKey(),
                        personReminderPair.getValue(), getIndex() + 1).getRoot());
            }
        }
    }

}
