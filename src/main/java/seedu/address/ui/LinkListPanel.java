package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.team.Link;

/**
 * Panel containing the list of links.
 */
public class LinkListPanel extends UiPart<Region> {

    private static final String FXML = "LinkListPanel.fxml";
    private static ResultDisplay resultDisplay;
    private final Logger logger = LogsCenter.getLogger(LinkListPanel.class);

    @javafx.fxml.FXML
    private ListView<Link> linkListView;


    /**
     * Creates a {@code LinkListPanel} with the given {@code ObservableList}.
     */
    public LinkListPanel(ObservableList<Link> linkList, ResultDisplay resultDisplay) {
        super(FXML);
        linkListView.setItems(linkList);
        linkListView.setCellFactory(listView -> new LinkListViewCell());
        LinkListPanel.resultDisplay = resultDisplay;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Link} using a {@code LinkCard}.
     */
    private static class LinkListViewCell extends ListCell<Link> {
        @Override
        protected void updateItem(Link link, boolean isEmpty) {
            super.updateItem(link, isEmpty);

            if (isEmpty || link == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LinkCard(link, getIndex() + 1, LinkListPanel.resultDisplay).getRoot());
            }
        }
    }

}
