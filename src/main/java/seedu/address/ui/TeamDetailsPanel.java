package seedu.address.ui;


import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.team.Team;

/**
 * Placeholder UI panel to show the full details of team.
 */
public class TeamDetailsPanel extends UiPart<Region> {

    private static final String FXML = "TeamDetailsPanel.fxml";

    private ObjectProperty<Team> teamProperty;
    @FXML
    private StackPane teamDetailsView;

    /**
     * Creates a {@code TeamDetailsPanel} with the given {@code ObjectProperty}.
     */
    public TeamDetailsPanel(ObjectProperty<Team> currentTeam) {
        super(FXML);
        teamProperty = currentTeam;
        TeamDetailsCard newCard = new TeamDetailsCard(currentTeam.getValue());
        teamDetailsView.getChildren().add(newCard.getRoot());
        teamProperty.addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observable, Team oldValue, Team newValue) {
                TeamDetailsCard newCard = new TeamDetailsCard(newValue);
                teamDetailsView.getChildren().set(0, newCard.getRoot());
            }
        });
    }

}
