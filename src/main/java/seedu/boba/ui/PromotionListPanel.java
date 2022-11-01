package seedu.boba.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import seedu.boba.commons.core.LogsCenter;

/**
 * Panel containing the list of promotions.
 */
public class PromotionListPanel extends UiPart<Region> {
    private static final String FXML = "PromotionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PromotionListPanel.class);

    @FXML
    private ListView<Image> promotionListView;

    /**
     * Creates a {@code PromotionListPanel} with the given {@code ObservableList}.
     */
    public PromotionListPanel(ObservableList<Image> promotionList) {
        super(FXML);
        promotionListView.setItems(promotionList);
        promotionListView.setCellFactory(listView -> new PromotionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Image} using a {@code PromotionCard}.
     */
    class PromotionListViewCell extends ListCell<Image> {
        @Override
        protected void updateItem(Image image, boolean empty) {
            super.updateItem(image, empty);

            if (empty || image == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PromotionCard(image, getIndex() + 1).getRoot());
            }
        }
    }

}
