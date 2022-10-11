package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.team.Team;

public class TeamDetailsCard extends UiPart<Region> {
    private static final String FXML = "TeamDetailsCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label teamNameDisplay;

    public TeamDetailsCard(StringProperty teamName) {
        super(FXML);
        teamNameDisplay.textProperty().bindBidirectional(teamName);
        setTeamName(teamNameDisplay.getText());
    }

    public void setTeamName(String teamName) {
        requireNonNull(teamName);
        teamNameDisplay.setText(teamName);
    }
}
