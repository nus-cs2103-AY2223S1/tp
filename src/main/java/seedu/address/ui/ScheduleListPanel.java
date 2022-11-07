package seedu.address.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Student> scheduleListView;

    @FXML
    private Label classDate;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public ScheduleListPanel(ObservableList<Student> scheduleList) {
        super(FXML);
        String currDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yyyy"));

        classDate.setText(currDate);
        scheduleListView.setItems(scheduleList);
        scheduleListView.setCellFactory(listView -> new ScheduleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class ScheduleListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
