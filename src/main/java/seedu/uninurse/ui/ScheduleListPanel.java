package seedu.uninurse.ui;

import static seedu.uninurse.ui.UiUtil.LIST_VIEW_OFFSET;

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
     * Creates a ScheduleListPanel with the given Schedule.
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
     * Custom ListCell that displays the graphics of a Schedule using a ScheduleListCard.
     */
    class ScheduleListViewCell extends ListCell<PatientTaskListPair> {
        ScheduleListViewCell() {
            super();
            setStyle("-fx-padding: 0 5 0 0");
            prefWidthProperty().bind(scheduleListView.widthProperty().subtract(LIST_VIEW_OFFSET));
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
