package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Student;

/**
 * Panel containing the list of tutors.
 */
public class TutorListPanel extends UiPart<Region> {
    private static final String FXML = "TutorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorListPanel.class);

    @javafx.fxml.FXML
    private ListView<Student> tutorListView;

    /**
     * Creates a {@code TutorListPanel} with the given {@code ObservableList}.
     */
    public TutorListPanel(ObservableList<Student> tutorList) {
        super(FXML);
        tutorListView.setItems(tutorList);
        tutorListView.setCellFactory(listView -> new TutorListPanel.TutorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code TutorCard}.
     */
    class TutorListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student tutor, boolean empty) {
            super.updateItem(tutor, empty);

            if (empty || tutor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TutorCard(tutor, getIndex() + 1).getRoot());
            }
        }
    }
}
