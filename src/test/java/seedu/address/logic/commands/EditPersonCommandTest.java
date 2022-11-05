package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import picocli.CommandLine;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressConverter;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.logic.parser.PhoneConverter;
import seedu.address.model.*;
import seedu.address.model.person.*;
import seedu.address.model.team.Link;
import seedu.address.model.team.Team;
import seedu.address.model.team.Task;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
// TODO: Add implementation for tests
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new EditPersonCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter())
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter())
            .registerConverter(Phone.class, new PhoneConverter())
            .registerConverter(Address.class, new AddressConverter());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person validPerson = new PersonBuilder(TypicalPersons.ALLIE).build();
        commandLine.parseArgs(PersonUtil.convertEditPersonToArgs(validPerson));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, validPerson));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person validPerson = new PersonBuilder(TypicalPersons.ALLIE).build();
        commandLine.parseArgs(PersonUtil.convertEditSecondPersonPartialToArgs(validPerson));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, validPerson));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Carl"));
        model.updateFilteredPersonList(predicate);
        Person validPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(validPerson).withName(VALID_NAME_BOB).build();
        commandLine.parseArgs(PersonUtil.convertEditFirstPersonPartialToArgs(editedPerson));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_throwsCommandException() {
        Person validPerson = new PersonBuilder(TypicalPersons.ALICE).build();
        commandLine.parseArgs(PersonUtil.convertEditSecondPersonPartialToArgs(validPerson));
        assertThrows(CommandException.class, EditPersonCommand.MESSAGE_DUPLICATE_PERSON,
                () -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Person validPerson = new PersonBuilder(TypicalPersons.ALLIE).build();
        commandLine.parseArgs(PersonUtil.convertEditPersonOOBToArgs(validPerson));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                () -> commandToBeTested.execute(model));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of TruthTable
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Carl"));
        model.updateFilteredPersonList(predicate);
        Person validPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(validPerson).withName(VALID_NAME_BOB).build();
        commandLine.parseArgs(PersonUtil.convertEditSecondPersonPartialToArgs(editedPerson));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                () -> commandToBeTested.execute(model));
    }

    @Test
    public void equals() {
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
        public Path getTruthTableFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTruthTableFilePath(Path truthTableFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTruthTable(ReadOnlyTruthTable newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTruthTable getTruthTable() {
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
        public Team getTeam() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeam(Team teamToSet) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeams(List<Team> teams) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTeam(Team teamToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTeam(Team teamToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Team> getTeamList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredMemberList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMembersList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObjectProperty<Team> getTeamAsProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLink(Link target, Link editedLink) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLink(Link link) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Link> getLinkList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends EditPersonCommandTest.ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends EditPersonCommandTest.ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyTruthTable getTruthTable() {
            return TruthTable.createNewTruthTable();
        }
    }

}
