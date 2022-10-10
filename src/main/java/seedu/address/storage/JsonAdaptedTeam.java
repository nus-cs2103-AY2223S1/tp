package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.team.Team;

/**
 * Jackson-friendly version of {@link Team}
 */
public class JsonAdaptedTeam {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Team's %s field is missing!";

    private final String teamName;

    /**
     * Constructs a {@code JsonAdaptedTeam} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTeam(@JsonProperty("teamName") String teamName) {
        this.teamName = teamName;
    }

    /**
     * Converts a given {@code Team} into this class for Jackson use.
     */
    public JsonAdaptedTeam(Team source) {
        teamName = source.getTeamName();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Team} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Team toModelType() throws IllegalValueException {

        if (teamName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Team.class.getSimpleName()));
        }
        return new Team(teamName, new ArrayList<>(), new ArrayList<>());
    }



}
