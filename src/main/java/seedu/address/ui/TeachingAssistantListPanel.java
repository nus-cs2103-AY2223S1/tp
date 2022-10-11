package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ta.TeachingAssistant;

import java.util.logging.Logger;

public class TeachingAssistantListPanel extends UiPart<Region> {

    private static final String FXML = "TeachingAssistantListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TeachingAssistantListPanel.class);

    @FXML
    private ListView<TeachingAssistant> teachingAssistantListView;

    /**
     * Creates a {@code TeachingAssistantListPanel} with the given {@code ObservableList}.
     */
    public TeachingAssistantListPanel(ObservableList<TeachingAssistant> teachingAssistantList) {
        super(FXML);
        teachingAssistantListView.setItems(teachingAssistantList);
        teachingAssistantListView.setCellFactory(listView -> new TeachingAssistantListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TeachingAssistant} using a {@code TeachingAssistantCard}.
     */
    class TeachingAssistantListViewCell extends ListCell<TeachingAssistant> {
        @Override
        protected void updateItem(TeachingAssistant ta, boolean empty) {
            super.updateItem(ta, empty);

            if (empty || ta == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TeachingAssistantCard(ta, getIndex() + 1).getRoot());
            }
        }
    }
}
