package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Panel containing the list of tuitionClasss.
 */
public class TuitionClassListPanel extends UiPart<Region> {
    private static final String FXML = "TuitionClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TuitionClassListPanel.class);

    @FXML
    private ListView<TuitionClass> tuitionClassListView;

    /**
     * Creates a {@code TuitionClassListPanel} with the given {@code ObservableList}.
     */
    public TuitionClassListPanel(ObservableList<TuitionClass> tuitionClassList) {
        super(FXML);
        tuitionClassListView.setItems(tuitionClassList);
        tuitionClassListView.setCellFactory(listView -> new TuitionClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TuitionClass} using a {@code TuitionClassCard}.
     */
    class TuitionClassListViewCell extends ListCell<TuitionClass> {
        @Override
        protected void updateItem(TuitionClass tuitionClass, boolean empty) {
            super.updateItem(tuitionClass, empty);

            if (empty || tuitionClass == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TuitionClassCard(tuitionClass, getIndex() + 1).getRoot());
            }
        }
    }

}
