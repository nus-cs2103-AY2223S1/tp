package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;

/**
 * Panel containing the list of teams.
 */
public class GroupListPanel extends UiPart<Region> {
    private static final String FXML = "TeamListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupListPanel.class);

    @FXML
    private ListView<Group> teamListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public GroupListPanel(ObservableList<Group> teamList) {
        super(FXML);
        teamListView.setItems(teamList);
        teamListView.setCellFactory(listView -> new TeamListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class TeamListViewCell extends ListCell<Group> {
        @Override
        protected void updateItem(Group person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TeamCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
