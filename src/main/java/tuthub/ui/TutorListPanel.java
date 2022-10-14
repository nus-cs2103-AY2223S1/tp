package tuthub.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tuthub.commons.core.LogsCenter;
import tuthub.model.tutor.Tutor;

/**
 * Panel containing the list of tutors.
 */
public class TutorListPanel extends UiPart<Region> {
    private static final String FXML = "TutorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorListPanel.class);

    @FXML
    private ListView<Tutor> tutorListView;

    /**
     * Creates a {@code TutorListPanel} with the given {@code ObservableList}.
     */
    public TutorListPanel(ObservableList<Tutor> tutorList) {
        super(FXML);
        tutorListView.setItems(tutorList);
        tutorListView.setCellFactory(listView -> new TutorListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tutor} using a {@code TutorListCard}.
     */
    class TutorListViewCell extends ListCell<Tutor> {
        @Override
        protected void updateItem(Tutor tutor, boolean empty) {
            super.updateItem(tutor, empty);

            if (empty || tutor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TutorListCard(tutor, getIndex() + 1).getRoot());
            }
        }
    }

}
