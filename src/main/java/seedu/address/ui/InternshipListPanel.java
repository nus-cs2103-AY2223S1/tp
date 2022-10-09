package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.internship.Internship;

/**
 * Panel containing the list of internship.
 */
public class InternshipListPanel extends UiPart<Region> {
    private static final String FXML = "InternshipListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InternshipListPanel.class);
    private ReadOnlyAddressBook addressBook;

    @FXML
    private ListView<Internship> internshipListView;

    /**
     * Creates a {@code InternshipListPanel} with the given {@code ObservableList}.
     */
    public InternshipListPanel(ObservableList<Internship> internshipList, ReadOnlyAddressBook addressBook) {
        super(FXML);
        this.addressBook = addressBook;
        internshipListView.setItems(internshipList);
        internshipListView.setCellFactory(listView -> new InternshipListViewCell());
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
                //create internship card
                InternshipCard newInternshipCard = new InternshipCard(internship, getIndex() + 1);
                newInternshipCard.setContactPerson(
                        addressBook.findPersonNameById(newInternshipCard.getContactPersonId()));

                setGraphic(newInternshipCard.getRoot());
            }
        }
    }

}
