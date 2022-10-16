package seedu.uninurse.ui;

import java.util.function.Supplier;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.model.person.Patient;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Patient> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Patient> personList, Supplier<Boolean> taskListFlagSupplier) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell(taskListFlagSupplier));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Patient> {
        private final Supplier<Boolean> taskListFlagSupplier;

        PersonListViewCell(Supplier<Boolean> taskListFlagSupplier) {
            super();
            this.taskListFlagSupplier = taskListFlagSupplier;
        }

        @Override
        protected void updateItem(Patient person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1, taskListFlagSupplier.get()).getRoot());
            }
        }
    }

}
