package seedu.uninurse.ui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.uninurse.model.person.Patient;

/**
 * Panel containing the list of all patients with a truncated task list.
 */
public class TruncatedTaskListPanel extends UiPart<Region> {
    private static final String FXML = "TruncatedTaskListPanel.fxml";

    @FXML
    private ListView<Patient> truncatedTaskListView;
    @FXML
    private Label header;

    /**
     * Creates a {@code TruncatedTaskListPanel} with the given list of {@code patients}.
     */
    public TruncatedTaskListPanel(List<Patient> patients) {
        super(FXML);
        this.truncatedTaskListView.setItems(FXCollections.observableList(patients));
        this.truncatedTaskListView.setCellFactory(listview -> new TruncatedTaskListViewCell());

        this.header.setText("All Patient's Tasks:");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code TruncatedTaskListCard}.
     */
    class TruncatedTaskListViewCell extends ListCell<Patient> {
        TruncatedTaskListViewCell() {
            super();
            setStyle("-fx-padding: 0 5 0 0");
            prefWidthProperty().bind(truncatedTaskListView.widthProperty().subtract(20.0));
        }

        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);
            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TruncatedTaskListCard(patient).getRoot());
            }
        }
    }
}
