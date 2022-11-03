package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deletePersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(Optional.of(INDEX_FIRST_PERSON), Optional.empty(),
                Optional.empty(), Optional.empty());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deletePersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(Optional.of(outOfBoundIndex), Optional.empty(),
                Optional.empty(), Optional.empty());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(Optional.of(INDEX_FIRST_PERSON), Optional.empty(),
                Optional.empty(), Optional.empty());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.commitAddressBook();
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(Optional.of(outOfBoundIndex), Optional.empty(),
                Optional.empty(), Optional.empty());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validRaceUnfilteredList_success() {
        Race race = new Race("White American");
        DeleteCommand deleteCommand = new DeleteCommand(Optional.empty(), Optional.of(race), Optional.empty(),
                Optional.empty());
        model.updateFilteredPersonList(p -> p.getRace().equals(race));
        List<Person> personList = new ArrayList<>();
        personList.addAll(model.getFilteredPersonList());
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personList.toString());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deletePersons(expectedModel, p -> p.getRace().equals(race));
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidRaceUnfilteredList_success() {
        Race race = new Race("asdoasmdsomda");
        DeleteCommand deleteCommand = new DeleteCommand(Optional.empty(), Optional.of(race), Optional.empty(),
                Optional.empty());

        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_validReligionUnfilteredList_success() {
        Religion religion = new Religion("Christian");
        DeleteCommand deleteCommand = new DeleteCommand(Optional.empty(), Optional.empty(), Optional.of(religion),
                Optional.empty());
        model.updateFilteredPersonList(p -> p.getReligion().equals(religion));
        List<Person> personList = new ArrayList<>();
        personList.addAll(model.getFilteredPersonList());
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personList.toString());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deletePersons(expectedModel, p -> p.getReligion().equals(religion));
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidReligionUnfilteredList_success() {
        Religion religion = new Religion("qweqeqeqe");
        DeleteCommand deleteCommand = new DeleteCommand(Optional.empty(), Optional.empty(), Optional.of(religion),
                Optional.empty());

        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_validSurveyUnfilteredList_success() {
        Survey survey = new Survey("Shopping Survey");
        Set<Survey> setSurvey = new HashSet<Survey>();
        setSurvey.add(survey);
        DeleteCommand deleteCommand = new DeleteCommand(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of(survey));
        model.updateFilteredPersonList(p -> p.getSurveys().equals(setSurvey));
        List<Person> personList = new ArrayList<>();
        personList.addAll(model.getFilteredPersonList());
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personList.toString());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        deletePersons(expectedModel, p -> p.getSurveys().equals(setSurvey));
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSurveyUnfilteredList_success() {
        Survey survey = new Survey("zxcasdb");
        DeleteCommand deleteCommand = new DeleteCommand(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.of(survey));

        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(Optional.of(INDEX_FIRST_PERSON), Optional.empty(),
                Optional.empty(), Optional.empty());
        DeleteCommand deleteSecondCommand = new DeleteCommand(Optional.of(INDEX_SECOND_PERSON), Optional.empty(),
                Optional.empty(), Optional.empty());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(Optional.of(INDEX_FIRST_PERSON), Optional.empty(),
                Optional.empty(), Optional.empty());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    private void deletePersons(Model model, Predicate<Person> predicate) {
        model.updateFilteredPersonList(predicate);
        List<Person> personList = new ArrayList<>();
        personList.addAll(model.getFilteredPersonList());
        for (Person p : personList) {
            model.deletePerson(p);
        }
    }
}
