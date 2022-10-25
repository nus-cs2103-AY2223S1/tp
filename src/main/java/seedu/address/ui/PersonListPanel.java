package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.buyer.Buyer;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Buyer> personListView;

    private ObservableList<Buyer> currentlyDisplayedBuyerList;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Buyer> filteredBuyerList) {
        super(FXML);
        personListView.setItems(filteredBuyerList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        currentlyDisplayedBuyerList = filteredBuyerList;
    }

    /**
     * Updates the current PersonListPanel with a new source list
     */
    public void setNewList(ObservableList<Buyer> newList) {
        if (currentlyDisplayedBuyerList.equals(newList)) {
            return;
        }
        personListView.setItems(newList);
        currentlyDisplayedBuyerList = newList;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Buyer} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Buyer> {
        @Override
        protected void updateItem(Buyer buyer, boolean empty) {
            super.updateItem(buyer, empty);

            if (empty || buyer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(buyer, getIndex() + 1).getRoot());
            }
        }
    }

}
