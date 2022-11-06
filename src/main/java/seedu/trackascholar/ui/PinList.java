package seedu.trackascholar.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.trackascholar.commons.core.LogsCenter;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Panel containing the list of pinned applicants.
 */
public class PinList extends UiPart<Region> {
    private static final String FXML = "PinList.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicantListPanel.class);

    @FXML
    private ListView<Applicant> pinList;

    /**
     * Creates a {@code ApplicantListPanel} with the given {@code ObservableList}.
     */
    public PinList(ObservableList<Applicant> applicantList) {
        super(FXML);
        pinList.setItems(applicantList);
        pinList.setCellFactory(listView -> new ApplicantListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Applicant} using a {@code ApplicantCard}.
     */
    class ApplicantListViewCell extends ListCell<Applicant> {
        @Override
        protected void updateItem(Applicant applicant, boolean empty) {
            super.updateItem(applicant, empty);

            if (empty || applicant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PinCard(applicant).getRoot());
            }
        }
    }

}
