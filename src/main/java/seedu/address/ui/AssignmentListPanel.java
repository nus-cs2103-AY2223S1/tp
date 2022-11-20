package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class AssignmentListPanel extends UiPart<Region> {
    private static final String FXML = "AssignmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssignmentListPanel.class);

    @FXML
    private ListView<Person> assignmentListView;

    /**
     * Creates a {@code AssignmentListPanel} with the given {@code ObservableList}.
     */
    public AssignmentListPanel(ObservableList<Person> assignmentList) {
        super(FXML);
        assignmentListView.setItems(assignmentList);
        assignmentListView.setCellFactory(listView -> new AssignmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class AssignmentListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AssignmentCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
