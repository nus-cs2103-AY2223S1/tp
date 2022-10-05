package bookface.ui;

import java.util.logging.Logger;

import bookface.commons.core.LogsCenter;
import bookface.model.person.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of persons.
 */
public class TestListPanel extends UiPart<Region> {
    private static final String FXML = "TestListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TestListPanel.class);

    @FXML
    private ListView<Person> testListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TestListPanel(ObservableList<Person> testList) {
        super(FXML);
        testListView.setItems(testList);
        testListView.setCellFactory(listView -> new TestListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TestListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
