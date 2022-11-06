package jeryl.fyp.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class CompletedStudentListPanel extends UiPart<Region> {
    private static final String FXML = "CompletedStudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CompletedStudentListPanel.class);

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public CompletedStudentListPanel(ObservableList<Student> studentList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                StudentCard studentCard = new StudentCard(student, getIndex() + 1);
                // Resolve border issue
                if (getIndex() == 0) {
                    this.setStyle("-fx-border-width: 0px 0px 1px 0px;");
                }
                setGraphic(studentCard.getRoot());
            }
        }
    }

}
