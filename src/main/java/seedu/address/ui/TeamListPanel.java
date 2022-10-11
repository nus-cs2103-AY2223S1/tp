package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.team.Team;

public class TeamListPanel extends UiPart<Region> {
    private static final String FXML = "TeamListPanel.fxml";

    @FXML
    private ListView<Team> teamListView;

    public TeamListPanel(ObservableList<Team> teamList) {
        super(FXML);

        teamListView.setItems(teamList);
        teamListView.setCellFactory(listView -> new TeamListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Team} using a {@code TeamCard}.
     */
    class TeamListViewCell extends ListCell<Team> {
        @Override
        protected void updateItem(Team team, boolean empty) {
            super.updateItem(team, empty);

            if (empty || team == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TeamCard(team, getIndex() + 1).getRoot());
            }
        }
    }
}
