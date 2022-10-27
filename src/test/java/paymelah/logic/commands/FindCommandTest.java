package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.testutil.TypicalPersons.BENSON;
import static paymelah.testutil.TypicalPersons.CARL;
import static paymelah.testutil.TypicalPersons.DANIEL;
import static paymelah.testutil.TypicalPersons.FIONA;
import static paymelah.testutil.TypicalPersons.GEORGE;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.person.PersonMatchesDescriptorPredicate;
import paymelah.testutil.DebtsDescriptorBuilder;
import paymelah.testutil.PersonDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonMatchesDescriptorPredicate firstPredicate = new PersonMatchesDescriptorPredicate(
                new PersonDescriptorBuilder().withName("first").build());
        PersonMatchesDescriptorPredicate secondPredicate = new PersonMatchesDescriptorPredicate(
                new PersonDescriptorBuilder().withName("second").build());

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new PersonDescriptorBuilder().withName("NonExistentName").build());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new PersonDescriptorBuilder().withName("meier").build());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_findAboveMiddlingAmount_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new DebtsDescriptorBuilder().withAbove("20").build());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_findBelowLowAmount_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new DebtsDescriptorBuilder().withBelow("4.5").build()); // boundary value (lowest debt value)
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_findBeforeMiddlingDate_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new DebtsDescriptorBuilder().withBefore("2022-10-1").build());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_findAfterLateDate_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new DebtsDescriptorBuilder().withAfter("2022-10-12").build()); // boundary value (latest date)
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());
    }
}
