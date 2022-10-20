package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public ReminderListPanel(ObservableList<Person> personList) {
        super(FXML);
        SortedList<Pair<Person, Reminder>> sorted = sort(personList);
        reminderListView.setItems(sorted);
        reminderListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Updates the items in the {@code ReminderListPanel} with the given {@code ObservableList}.
     */
    public void updateItems(ObservableList<Person> personList) {
        SortedList<Pair<Person, Reminder>> sorted = sort(personList);
        reminderListView.setItems(sorted);
    }

    /**
     * Sorts the {@code ObservableList} and returns a {@code SortedList}
     * @param personList The {@code ObservableList} to be sorted
     * @return The sorted {@code SortedList}
     */
    private SortedList<Pair<Person, Reminder>> sort(ObservableList<Person> personList) {
        ArrayList<Pair<Person, Reminder>> total = new ArrayList<>();
        for (Person person : personList) {
            total.addAll(person.getReminders().stream().map(reminder -> new Pair<Person, Reminder>(
                    person, reminder
            )).collect(Collectors.toList()));
        }
        SortedList<Pair<Person, Reminder>> sorted = new SortedList<>(FXCollections.observableArrayList(total));
        sorted.setComparator(Comparator.comparing(x -> x.getKey().getName().fullName));
        sorted.setComparator(Comparator.comparing(x -> x.getValue().date));
        return sorted;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code ReminderCard}.
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
