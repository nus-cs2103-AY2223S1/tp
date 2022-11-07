package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of stalls.
 */
public class StallListPanel extends UiPart<Region> {
    private static final String FXML = "StallListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StallListPanel.class);

    @FXML
    private ListView<Stall> stallListView;

    /**
     * Creates a {@code StallListPanel} with the given {@code ObservableList}.
     */
    public StallListPanel(ObservableList<Stall> stallList) {
        super(FXML);
        logger.info("Populating stalls from storage...");
        stallListView.setItems(stallList);
        stallListView.setCellFactory(listView -> new StallListViewCell());
    }

    //@@author hikoya-reused
    /**
     * Handles the activity when the user clicks on the stall in the list.
     *
     * Adapted code from https://stackoverflow.com/questions/20635192/how-to-create-popup-menu.
     */
    @FXML
    public void handleMouseClicked() {
        final Stall selectedStall = stallListView.getSelectionModel().getSelectedItem();

        if (selectedStall != null) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ContextMenu contextMenu = new ContextMenu();
            final ObservableList<MenuItem> contextMenuItems = contextMenu.getItems();


            CopyMenuItem<Stall> copyStallName = new CopyMenuItem<>("Copy Name",
                    selectedStall, clipboard, CopyMenuItem.Action.FIELDS_NAME);
            CopyMenuItem<Stall> copyStallAddress = new CopyMenuItem<>("Copy Address",
                    selectedStall, clipboard, CopyMenuItem.Action.FIELDS_ADDRESS);

            contextMenuItems.addAll(copyStallName, copyStallAddress);

            if (!selectedStall.getTags().isEmpty()) {
                CopyMenuItem<Stall> copyStallTag = new CopyMenuItem<>("Copy Tag",
                        selectedStall, clipboard, CopyMenuItem.Action.FIELDS_TAG);
                contextMenuItems.add(copyStallTag);
            }

            stallListView.setContextMenu(contextMenu);
        }
    }
    //@@author hikoya

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stall} using a {@code StallCard}.
     */
    class StallListViewCell extends ListCell<Stall> {
        @Override
        protected void updateItem(Stall stall, boolean empty) {
            super.updateItem(stall, empty);

            if (empty || stall == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StallCard(stall, getIndex() + 1).getRoot());
            }
        }
    }

}
