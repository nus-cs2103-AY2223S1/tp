package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.team.Team;

public class TeamDetailsCard extends UiPart<Region> {
    private static final String FXML = "TeamDetailsCard.fxml";

    private static final String CURRENT_TEAM_HEADER = "Currently Selected Team: ";

    @FXML
    private HBox cardPane;
    @FXML
    private Label teamNameDisplay;

    public TeamDetailsCard(Team team) {
        super(FXML);
        setTeamName(CURRENT_TEAM_HEADER + team.getTeamName());
    }

    public void setTeamName(String teamName) {
        requireNonNull(teamName);
        teamNameDisplay.setText(teamName);
    }
}
