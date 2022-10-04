package gim.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import gim.commons.core.LogsCenter;
import gim.model.person.Exercise;

/**
 * Panel containing the list of persons.
 */
public class ExerciseListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ExerciseListPanel.class);

    @FXML
    private ListView<Exercise> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ExerciseListPanel(ObservableList<Exercise> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new ExerciseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ExerciseListViewCell extends ListCell<Exercise> {
        @Override
        protected void updateItem(Exercise person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ExerciseCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
