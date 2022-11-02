package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * Unmodifiable view of a TruthTable
 */
public interface ReadOnlyTruthTable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns the team.
     * The team will not contain any duplicate members.
     */
    Team getTeam();

    /**
     * Returns the list of all existing teams.
     * The list will not contain any duplicate teams.
     */

    ObservableList<Team> getTeamList();
}
