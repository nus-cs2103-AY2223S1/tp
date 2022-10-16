package foodwhere.ui;

import java.util.logging.Logger;

import foodwhere.commons.core.LogsCenter;
import foodwhere.model.stall.Stall;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
        stallListView.setItems(stallList);
        stallListView.setCellFactory(listView -> new StallListViewCell());
    }

    /**
     * Handles the activity when the user clicks on the stall in the list
     *
     * Adapted code from https://stackoverflow.com/questions/20635192/how-to-create-popup-menu
     */
    @FXML
    public void handleMouseClicked() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();

        final ContextMenu contextMenu = new ContextMenu();
        MenuItem copyStallName = new MenuItem("Copy Name");
        MenuItem copyStallAddress = new MenuItem("Copy Address");
        MenuItem copyStallTag = new MenuItem("Copy Tag");
        contextMenu.getItems().addAll(copyStallName, copyStallAddress, copyStallTag);

        stallListView.setContextMenu(contextMenu);

        copyStallName.setOnAction((ActionEvent actionEvent) -> {
            content.putString(stallListView.getSelectionModel().getSelectedItem().getName().toString());
            clipboard.setContent(content);
        });

        copyStallAddress.setOnAction((ActionEvent actionEvent) -> {
            content.putString(stallListView.getSelectionModel().getSelectedItem().getAddress().toString());
            clipboard.setContent(content);
        });

        copyStallTag.setOnAction((ActionEvent actionEvent) -> {
            content.putString(stallListView.getSelectionModel().getSelectedItem().getTags().toString());
            clipboard.setContent(content);
        });
    }

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
