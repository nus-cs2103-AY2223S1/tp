package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons to display reminders about the persons.
 */
public class ReminderListPanel extends UiPart<Region> {
    private static final String FXML = "ReminderListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReminderListPanel.class);

    @FXML
    private ListView<Person> reminderListView;

    /**
     * Creates a {@code ReminderListPanel} with the given {@code ObservableList}.
     */
    public ReminderListPanel(ObservableList<Person> personList) {
        super(FXML);
        SortedList<Person> sorted = sort(personList);
        reminderListView.setItems(sorted);
        reminderListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Sorts the {@code ObservableList} and returns a {@code SortedList}
     * @param personList The {@code ObservableList} to be sorted
     * @return The sorted {@code SortedList}
     */
    private SortedList<Person> sort(ObservableList<Person> personList) {
        SortedList<Person> sorted = new SortedList<>(personList);
        sorted.setComparator(Comparator.comparing(x -> x.getBirthday().toString()));
        return sorted;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code ReminderCard}.
     */
    class ReminderListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
