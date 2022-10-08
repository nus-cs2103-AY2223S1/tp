package seedu.address.model.team;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;

/**
 * A list of teams that enforces uniqueness between its elements and does not allow nulls.
 * A team is considered unique solely based on the name of the team.
 *
 * Supports a minimal set of list operations.
 **/


public class UniqueTeamList implements Iterable<Team> {

    private final ObservableList<Team> internalTeams = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains a team with the same name as the given argument.
     */
    public boolean contains(Team teamToCheck) {
        requireNonNull(teamToCheck);
        return internalTeams.stream().anyMatch(teamToCheck::isSameTeam);
    }

    /**
     * Adds a team to the list.
     * That team must not already exist in the list.
     */
    public void add (Team teamToAdd) {
        requireNonNull(teamToAdd);
        if (contains(teamToAdd)) {
            throw new DuplicateTeamException();
        }
        internalTeams.add(teamToAdd);
    }

    /**
     * Removes the equivalent team from the list.
     */
    public void remove (Team teamToRemove) {
        requireNonNull(teamToRemove);
        if (!internalTeams.remove(teamToRemove)) {
            throw new TeamNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Team> asUnmodifiableObservableList() {
        return this.internalTeams;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Team> iterator() {
        return internalTeams.iterator();
    }
}
