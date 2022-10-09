package eatwhere.foodguide.ui;

import java.util.logging.Logger;

import eatwhere.foodguide.commons.core.LogsCenter;
import eatwhere.foodguide.model.eatery.Eatery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Eatery> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Eatery> eateryList) {
        super(FXML);
        personListView.setItems(eateryList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Eatery} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Eatery> {
        @Override
        protected void updateItem(Eatery eatery, boolean empty) {
            super.updateItem(eatery, empty);

            if (empty || eatery == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(eatery, getIndex() + 1).getRoot());
            }
        }
    }

}
