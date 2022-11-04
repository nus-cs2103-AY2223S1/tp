package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.model.Schedule;
import seedu.uninurse.model.Schedule.PatientTaskListPair;

/**
 * Panel containing the list of schedules.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleListPanel.fxml";

    @FXML
    private ListView<PatientTaskListPair> scheduleListView;
    @FXML
    private Label header;

    /**
     * Creates a {@code ScheduleListPanel} with the given {@code Patient}.
     */
    public ScheduleListPanel(Schedule schedule) {
        super(FXML);
        this.scheduleListView.setItems(schedule.getObservableList());
        this.scheduleListView.setCellFactory(listview -> new ScheduleListViewCell());

        if (schedule.isToday()) {
            this.header.setText(String.format("Tasks for Today (%s):", schedule.getDate()));
        } else {
            this.header.setText(String.format("Tasks for %s:", schedule.getDate()));
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Schedule} using a {@code ScheduleListCard}.
     */
    class ScheduleListViewCell extends ListCell<PatientTaskListPair> {
        ScheduleListViewCell() {
            super();
            setStyle("-fx-padding: 0 5 0 0");
            prefWidthProperty().bind(scheduleListView.widthProperty().subtract(20.0));
        }

        @Override
        protected void updateItem(PatientTaskListPair patientTaskPair, boolean empty) {
            super.updateItem(patientTaskPair, empty);
            if (empty || patientTaskPair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleListCard(patientTaskPair.getPatient(), patientTaskPair.getTaskList()).getRoot());
            }
        }
    }
}
