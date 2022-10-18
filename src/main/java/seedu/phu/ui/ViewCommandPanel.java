package seedu.phu.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.phu.commons.core.LogsCenter;
import seedu.phu.model.internship.Internship;

/**
 * Panel containing an internship with additional details.
 */
public class ViewCommandPanel extends UiPart<Region> {
    private static final String FXML = "ViewCommandPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipListPanel.class);

    @FXML
    private ListView<Internship> viewCommandItem;

    /**
     * Creates a {@code InternshipListPanel} with the given {@code ObservableList}.
     */
    public ViewCommandPanel(ObservableList<Internship> internshipList) {
        super(FXML);
        viewCommandItem.setItems(internshipList);
        viewCommandItem.setCellFactory(listView -> new InternshipListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Internship} using a {@code InternshipCard}.
     */
    class InternshipListViewCell extends ListCell<Internship> {
        @Override
        protected void updateItem(Internship internship, boolean empty) {
            super.updateItem(internship, empty);

            if (empty || internship == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ViewCommandCard(internship, getIndex() + 1).getRoot());
            }
        }
    }

}
