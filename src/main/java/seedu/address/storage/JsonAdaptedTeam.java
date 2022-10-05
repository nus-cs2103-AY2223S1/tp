package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.team.Name;
import seedu.address.model.team.Team;

/**
 * Jackson-friendly version of {@link Team}.
 */
public class JsonAdaptedTeam {

    private final String teamName;

    @JsonCreator
    public JsonAdaptedTeam(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTeam(Team source) {
        teamName = source.getName().fullName;
    }

    @JsonValue
    public String getTeamName() {
        return teamName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Team toModelType() throws IllegalValueException {
        return new Team(new Name(teamName));
    }
}
