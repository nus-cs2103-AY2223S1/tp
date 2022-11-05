package seedu.modquik.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.modquik.commons.core.LogsCenter;
import seedu.modquik.model.consultation.Consultation;

/**
 * Panel containing the list of consultations.
 */
public class ConsultationListPanel extends UiPart<Region> {
    private static final String FXML = "ConsultationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ConsultationListPanel.class);

    @FXML
    private ListView<Consultation> consultationListView;

    /**
     * Creates a {@code ConsultationListPanel} with the given {@code ObservableList}.
     */
    public ConsultationListPanel(ObservableList<Consultation> consultationList) {
        super(FXML);
        consultationListView.setItems(consultationList);
        consultationListView.setCellFactory(listView -> new ConsultationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Consultation} using a {@code ConsultationCard}.
     */
    class ConsultationListViewCell extends ListCell<Consultation> {
        @Override
        protected void updateItem(Consultation consultation, boolean empty) {
            super.updateItem(consultation, empty);

            if (empty || consultation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ConsultationCard(consultation, getIndex() + 1).getRoot());
            }
        }
    }
}
