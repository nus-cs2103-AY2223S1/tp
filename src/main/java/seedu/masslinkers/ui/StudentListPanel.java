package seedu.masslinkers.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);
    private final ModListPanel modListPanel;

    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList, ModListPanel studentModList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
        this.modListPanel = studentModList;
    }

    public void setStudentPanel(ObservableList<Student> studentList) {
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }

    /**
     * Displays ListView item clicked on the ListView on the {@code testPanel}
     */
    public void handleStudentClick() {
        studentListView.setOnMouseClicked(event -> {
            Student student = studentListView.getSelectionModel().getSelectedItem();
            if (student != null) {
                modListPanel.setStudentModList(student);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    static class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean isEmpty) {
            super.updateItem(student, isEmpty);

            if (isEmpty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
