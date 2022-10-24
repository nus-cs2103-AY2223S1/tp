/*
package seedu.uninurse.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

// TODO: Implement this

public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "SchedulePanel.fxml";


    //@FXML
    //private ListView<PatientTaskPair> scheduleListView;
    @FXML
    private Label label;

    public SchedulePanel(ObservableList<PatientTaskPair> patientTaskPairs) {
        super(FXML);
        scheduleListView.setItems(patientTaskPairs);
        scheduleListView.setCellFactory(listview -> new ScheduleListViewCell());
        label.setText("Tasks for 06-06-2022:");
    }

    public void handleSchedule()

    class ScheduleListViewCell extends ListCell<Task> {

        ScheduleListViewCell() {
            super();
        }

        @Override
        protected void updateItem(PatientTaskPair patientTaskPair, boolean empty) {
            super.updateItem(patientTaskPair, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScheduleCard(patientTaskPair.getPatient(), patientTaskPair.getTask()).getRoot());
            }

        }

    }

}
*/
