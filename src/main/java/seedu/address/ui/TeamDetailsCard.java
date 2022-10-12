package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.team.Team;
/**
 * A UI Component that displays the full information of a {@code Team}.
 */
public class TeamDetailsCard extends UiPart<Region> {

    private static final String FXML = "TeamDetailsCard.fxml";

    private Team team;

    @FXML
    private HBox cardPane;
    @FXML
    private Label teamNameDisplay;

    /**
     * Creates a {@code TeamDetailsCard} with the given {@code Team}.
     */
    public TeamDetailsCard(Team team) {
        super(FXML);
        teamNameDisplay.setText(team.getTeamName());
    }
}
