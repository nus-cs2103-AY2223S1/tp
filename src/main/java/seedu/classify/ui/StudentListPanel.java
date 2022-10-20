package seedu.classify.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.classify.commons.core.LogsCenter;
import seedu.classify.model.FilteredStudents;
import seedu.classify.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(FilteredStudents filteredStudents) {
        super(FXML);
        ObservableList<Student> studentList = filteredStudents.getFilteredStudentList();
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell(filteredStudents));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        private FilteredStudents filteredStudents;

        public StudentListViewCell(FilteredStudents filteredStudents) {
            this.filteredStudents = filteredStudents;
        }

        @Override
        protected void updateItem(Student person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(person, getIndex() + 1,
                        this.filteredStudents.hasConciseInfo()).getRoot());
            }
        }
    }

}
