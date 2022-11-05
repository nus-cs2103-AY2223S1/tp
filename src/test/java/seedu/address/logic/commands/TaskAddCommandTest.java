package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAM;
import static seedu.address.testutil.TypicalTeams.FRONTEND;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.team.Team;
import seedu.address.testutil.TaskBuilder;

public class TaskAddCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(null, null));
    }

    @Test
    public void execute_indexOutOfRange_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        Index validIndex = INDEX_SECOND_TEAM;
        TaskAddCommand taskAddCommand = new TaskAddCommand(validIndex, validTask);
        ModelStub modelStub = new ModelStubWithTeam(FRONTEND);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX, ()
                -> taskAddCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        Index validIndex = INDEX_FIRST_TEAM;
        TaskAddCommand taskAddCommand = new TaskAddCommand(validIndex, validTask);
        ModelStubWithTeam modelStub = new ModelStubWithTeam(FRONTEND);
        modelStub.addTask(validIndex, validTask);

        assertThrows(CommandException.class, TaskAddCommand.MESSAGE_DUPLICATE_TASK, ()
                -> taskAddCommand.execute(modelStub));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded(FRONTEND);
        Task validTask = new TaskBuilder().build();
        Index validIndex = INDEX_FIRST_TEAM;
        CommandResult commandResult = new TaskAddCommand(validIndex, validTask).execute(modelStub);

        assertEquals(String.format(TaskAddCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public boolean teamNameExists(seedu.address.model.team.Name name) {
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
        public boolean teamHasTask(Index index, Task task) {
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

    /**
     * A Model stub that contains a single team.
     */
    private class ModelStubWithTeam extends ModelStub {
        private final Team team;

        ModelStubWithTeam(Team team) {
            requireNonNull(team);
            this.team = team;
        }

        @Override
        public void addTask(Index index, Task task) {
            requireAllNonNull(index, task);
            team.addTask(task);
        }

        @Override
        public boolean hasTeam(Team team) {
            requireNonNull(team);
            return this.team.isSameTeam(team);
        }

        @Override
        public boolean teamHasTask(Index index, Task task) {
            requireAllNonNull(index, task);
            return team.containTask(task);
        }

        @Override
        public ObservableList<Team> getFilteredTeamList() {
            return FXCollections.observableArrayList(team);
        }
    }

    /**
     * A Model stub with one team that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStubWithTeam {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        ModelStubAcceptingTaskAdded(Team team) {
            super(team);
        }

        @Override
        public void addTask(Index index, Task task) {
            requireAllNonNull(index, task);
            tasksAdded.add(task);
        }
    }

}
