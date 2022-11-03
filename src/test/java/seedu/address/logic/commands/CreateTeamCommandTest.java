package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.team.Team;
import seedu.address.testutil.TeamBuilder;

public class CreateTeamCommandTest {
    @Test
    public void constructor_nullTeam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTeamCommand(null));
    }
    @Test
    public void execute_teamAcceptedByModel_addSuccessful() throws Exception {
        CreateTeamCommandTest.ModelStubAcceptingTeamAdded modelStub =
                new CreateTeamCommandTest.ModelStubAcceptingTeamAdded();
        Team validTeam = new TeamBuilder().build();

        CommandResult commandResult = new CreateTeamCommand(validTeam).execute(modelStub);

        assertEquals(String.format(CreateTeamCommand.MESSAGE_SUCCESS, validTeam), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTeam), modelStub.teamsAdded);
    }

    @Test
    public void execute_duplicateTeam_throwsCommandException() {
        Team validTeam = new TeamBuilder().build();
        CreateTeamCommand createTeamCommand = new CreateTeamCommand(validTeam);
        ModelStub modelStub = new ModelStubWithTeam(validTeam);

        assertThrows(CommandException.class, CreateTeamCommand.MESSAGE_DUPLICATE_TEAM,
                () -> createTeamCommand.execute(modelStub));
    }


    @Test
    public void equals() {
        Team frontend = new TeamBuilder().withName("Frontend").build();
        Team backend = new TeamBuilder().withName("Backend").build();
        CreateTeamCommand createFrontendCommand = new CreateTeamCommand(frontend);
        CreateTeamCommand createBackendCommand = new CreateTeamCommand(backend);

        // same object -> returns true
        assertTrue(createFrontendCommand.equals(createFrontendCommand));

        // same values -> returns true
        CreateTeamCommand createFrontendCommandCopy = new CreateTeamCommand(frontend);
        assertTrue(createFrontendCommand.equals(createFrontendCommandCopy));

        // different types -> returns false
        assertFalse(createFrontendCommand.equals(1));

        // null -> returns false
        assertFalse(createFrontendCommand.equals(null));

        // different team -> returns false
        assertFalse(createFrontendCommand.equals(createBackendCommand));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTeam(Team team) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTeam(Team team) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTeam(Team team) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeamName(Index targetIndex, seedu.address.model.team.Name newTeamName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonToTeam(Person person, Team team) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removePersonFromTeam(Person person, Team team) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPerson(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Name getPersonName(Index personIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonUsingIndex(Index personIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTeamList(Predicate<Team> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Team> getFilteredTeamList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Team getTeam(seedu.address.model.team.Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public seedu.address.model.team.Name getTeamName(Index teamIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Team getTeamUsingIndex(Index teamIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Team> teamsWithMember(Person p) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean teamHasMember(Index p, Index t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Index index, Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markTask(Index teamIndex, Index taskIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkTask(Index teamIndex, Index taskIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Index teamIndex, Index taskIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editTask(Index teamIndex, Index taskIndex,
                             seedu.address.model.task.Name newName, LocalDate newDeadline) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithTeam extends CreateTeamCommandTest.ModelStub {
        private final Team team;

        ModelStubWithTeam(Team team) {
            requireNonNull(team);
            this.team = team;
        }

        @Override
        public boolean hasTeam(Team team) {
            requireNonNull(team);
            return this.team.isSameTeam(team);
        }
    }

    private class ModelStubAcceptingTeamAdded extends CreateTeamCommandTest.ModelStub {
        final ArrayList<Team> teamsAdded = new ArrayList<>();

        @Override
        public boolean hasTeam(Team team) {
            requireNonNull(team);
            return teamsAdded.stream().anyMatch(team::isSameTeam);
        }

        @Override
        public void addTeam(Team team) {
            requireNonNull(team);
            teamsAdded.add(team);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
