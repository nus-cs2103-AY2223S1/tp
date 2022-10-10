package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.poc.Poc;

/**
 * Panel containing the list of pocs.
 */
public class PocListPanel extends UiPart<Region> {
    private static final String FXML = "PocListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PocListPanel.class);

    @FXML
    private ListView<Poc> pocListView;

    /**
     * Creates a {@code PocListPanel} with an empty {@code ObservableList}.
     */
    public PocListPanel() {
        super(FXML);
    }

    /**
     * Sets the {@code PocListPanel} with the pocs from {@code ObservableList}.
     */
    public void setPocList(ObservableList<Poc> pocList) {
        requireNonNull(pocList);

        pocListView.setItems(pocList);
        pocListView.setCellFactory(listView -> new PocListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Poc} using a {@code PocCard}.
     */
    class PocListViewCell extends ListCell<Poc> {
        @Override
        protected void updateItem(Poc poc, boolean empty) {
            super.updateItem(poc, empty);

            if (empty || poc == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PocCard(poc, getIndex() + 1).getRoot());
            }
        }
    }

}
