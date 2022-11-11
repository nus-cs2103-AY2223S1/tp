package seedu.intrack.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.model.internship.Internship;

/**
 * Panel containing the list of internships.
 */
public class SelectedInternshipPanel extends UiPart<Region> {
    private static final String FXML = "SelectedInternshipPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipListPanel.class);

    @FXML
    private ListView<Internship> internshipListView;

    /**
     * Creates a {@code InternshipListPanel} with the given {@code ObservableList}.
     */
    public SelectedInternshipPanel(ObservableList<Internship> internshipList) {
        super(FXML);
        internshipListView.setItems(internshipList);
        internshipListView.setCellFactory(listView -> new InternshipListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Internship} using a {@code InternshipCard}.
     */
    class InternshipListViewCell extends ListCell<Internship> {
        @Override
        protected void updateItem(Internship internship, boolean isEmpty) {
            super.updateItem(internship, isEmpty);

            if (isEmpty || internship == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SelectedInternshipCard(internship).getRoot());
            }
        }
    }

}
