package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTruthTable;
import seedu.address.model.TruthTable;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * An Immutable TruthTable that is serializable to JSON format.
 */
@JsonRootName(value = "truthtable")
class JsonSerializableTruthTable {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TEAMS = "Team list contains duplicate team(s).";
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTeam> teams = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTruthTable} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTruthTable(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                      @JsonProperty("teams") List<JsonAdaptedTeam> teams) {
        this.persons.addAll(persons);
        this.teams.addAll(teams);
    }

    /**
     * Converts a given {@code ReadOnlyTruthTable} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTruthTable}.
     */
    public JsonSerializableTruthTable(ReadOnlyTruthTable source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        teams.addAll(source.getTeamList().stream().map(JsonAdaptedTeam::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TruthTable into the model's {@code TruthTable} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TruthTable toModelType() throws IllegalValueException {
        TruthTable truthTable = TruthTable.createNewTruthTable();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (truthTable.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            truthTable.addPerson(person);
        }

        int counter = 0;
        for (JsonAdaptedTeam jsonAdaptedTeam : teams) {
            Team team = jsonAdaptedTeam.toModelType();
            if (counter == 0) {
                Team defaultTeam = Team.createDefaultTeam();
                truthTable.deleteTeam(defaultTeam);
                truthTable.setTeam(team);
            }
            if (truthTable.getTeamList().contains(team)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEAMS);
            }

            truthTable.addTeam(team);
            counter++;
        }
        return truthTable;
    }

}
