package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;

/**
 * A list of teams that enforces uniqueness between its elements and does not allow nulls.
 * A team is considered unique solely based on the name of the team.
 * <p>
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
    public void add(Team teamToAdd) {
        requireNonNull(teamToAdd);
        if (contains(teamToAdd)) {
            throw new DuplicateTeamException();
        }
        internalTeams.add(teamToAdd);
    }

    /**
     * Removes the equivalent team from the list.
     */
    public void remove(Team teamToRemove) {
        requireNonNull(teamToRemove);
        if (!internalTeams.remove(teamToRemove)) {
            throw new TeamNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code teams}.
     */
    public void setTeams(List<Team> teams) {
        requireAllNonNull(teams);
        if (!teamsAreUnique(teams)) {
            throw new DuplicateTeamException();
        }
        internalTeams.setAll(teams);
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

    /**
     * Returns true if {@code team} contains only unique teams.
     */
    private boolean teamsAreUnique(List<Team> teams) {
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                if (teams.get(i).isSameTeam(teams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes a person from all teams, if the person exists.
     */
    public void removePersonIfExists(Person person) {
        requireNonNull(person);
        for (Team team : internalTeams) {
            if (team.hasMember(person)) {
                team.removeMember(person);
            }
        }
    }

    /**
     * Replaces {@code target} with {@code editedPerson} in all teams if {@code target} is a member.
     */
    public void setPersonIfExists(Person target, Person editedPerson) {
        requireNonNull(target);
        requireNonNull(editedPerson);
        for (Team team : internalTeams) {
            if (team.hasMember(target)) {
                team.setMember(target, editedPerson);
            }
        }
    }
}
