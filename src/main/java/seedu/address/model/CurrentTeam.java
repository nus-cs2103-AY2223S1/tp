package seedu.address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import seedu.address.model.team.Team;

public class CurrentTeam {
    private Team currentTeam;
    private StringProperty nameString = new SimpleStringProperty("");

    public CurrentTeam() {
    }

    public void setTeam(Team team) {
        currentTeam = team;
        nameString.set(team.getTeamName());
    }

    public Team getTeam() {
        return currentTeam;
    }

    public StringProperty getTeamAsProperty() {
        return nameString;
    }

}
