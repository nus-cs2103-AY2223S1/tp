package seedu.taassist.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.model.student.StudentView;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<StudentView> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<StudentView> studentList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code StudentView} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<StudentView> {
        @Override
        protected void updateItem(StudentView studentView, boolean empty) {
            super.updateItem(studentView, empty);

            if (empty || studentView == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(studentView, getIndex() + 1).getRoot());
            }
        }
    }

}
