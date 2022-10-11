package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consultation.Consultation;

import java.util.logging.Logger;

public class ConsultationListPanel  extends UiPart<Region> {
    private static final String FXML = "ConsultationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ConsultationListPanel.class);

    @javafx.fxml.FXML
    private ListView<Consultation> ConsultationListView;

    /**
     * Creates a {@code ConsultationListPanel} with the given {@code ObservableList}.
     */
    public ConsultationListPanel(ObservableList<Consultation> ConsultationList) {
        super(FXML);
        ConsultationListView.setItems(ConsultationList);
        ConsultationListView.setCellFactory(listView -> new ConsultationListPanel.ConsultationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Consultation} using a {@code ConsultationCard}.
     */
    class ConsultationListViewCell extends ListCell<Consultation> {
        @Override
        protected void updateItem(Consultation Consultation, boolean empty) {
            super.updateItem(Consultation, empty);

            if (empty || Consultation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ConsultationCard(Consultation, getIndex() + 1).getRoot());
            }
        }
    }

}
