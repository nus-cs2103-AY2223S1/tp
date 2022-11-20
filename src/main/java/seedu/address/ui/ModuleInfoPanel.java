package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Panel containing the module information.
 */
public class ModuleInfoPanel extends UiPart<Region> {

    private static final String FXML = "ModuleInfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    @javafx.fxml.FXML
    private ListView<Person> moduleInfoView;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModuleInfoPanel(ObservableList<Person> studentList) {
        super(FXML);
        moduleInfoView.setItems(studentList);
        moduleInfoView.setCellFactory(listView -> new ModuleInfoViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class ModuleInfoViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (person instanceof Student) {
                    setGraphic(new StudentCard((Student) person, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                }

            }
        }
    }
}
