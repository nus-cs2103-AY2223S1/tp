package seedu.address.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> scheduleListView;

    @FXML
    private Label title;

    @FXML
    private Label classDate;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ScheduleListPanel(ObservableList<Person> scheduleList) {
        super(FXML);
        LocalDate now = LocalDate.now();
        String currDate = now.format(DateTimeFormatter.ofPattern("d MMM yyyy"));

        title.setText("Schedule for");
        classDate.setText(currDate);
        scheduleListView.setItems(scheduleList);
        scheduleListView.setCellFactory(listView -> new ScheduleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ScheduleListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
