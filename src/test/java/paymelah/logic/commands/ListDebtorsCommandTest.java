package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.ListDebtorsCommand.MESSAGE_NO_DEBTORS;
import static paymelah.logic.commands.ListDebtorsCommand.MESSAGE_SUCCESS_GENERIC;
import static paymelah.logic.commands.ListDebtorsCommand.MESSAGE_SUCCESS_SPECIFIC;
import static paymelah.logic.parser.ParserUtil.prepareDebtGreaterEqualAmountPredicate;
import static paymelah.testutil.TypicalPersons.ALICE;
import static paymelah.testutil.TypicalPersons.BENSON;
import static paymelah.testutil.TypicalPersons.CARL;
import static paymelah.testutil.TypicalPersons.FIONA;
import static paymelah.testutil.TypicalPersons.GEORGE;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.debt.Money;
import paymelah.model.person.DebtGreaterEqualAmountPredicate;
import paymelah.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListDebtorsCommand.
 */
class ListDebtorsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DebtGreaterEqualAmountPredicate firstPredicate = new DebtGreaterEqualAmountPredicate(
                new Money("5"));
        DebtGreaterEqualAmountPredicate secondPredicate = new DebtGreaterEqualAmountPredicate(
                new Money("10.0"));

        ListDebtorsCommand firstCommand = new ListDebtorsCommand(firstPredicate);
        ListDebtorsCommand secondCommand = new ListDebtorsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DebtGreaterEqualAmountPredicate firstPredicateCopy = new DebtGreaterEqualAmountPredicate(
                new Money("$5.00"));
        ListDebtorsCommand firstCommandCopy = new ListDebtorsCommand(firstPredicateCopy);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_amountProvided_noPersonFound() {
        String testAmount = "$1000";
        try {
            DebtGreaterEqualAmountPredicate predicate = prepareDebtGreaterEqualAmountPredicate(testAmount);
            String expectedMessage = MESSAGE_NO_DEBTORS;
            ListDebtorsCommand command = new ListDebtorsCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        } catch (ParseException e) {
            fail("Invalid predicate");
        }
    }

    @Test
    public void execute_amountSpecified_personsFound() {
        String testAmount = "$20";
        try {
            DebtGreaterEqualAmountPredicate predicate = prepareDebtGreaterEqualAmountPredicate(testAmount);
            String expectedMessage = String.format(MESSAGE_SUCCESS_SPECIFIC, predicate.getAmount());
            ListDebtorsCommand command = new ListDebtorsCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredPersonList());
        } catch (ParseException e) {
            fail("Invalid predicate");
        }
    }

    @Test
    public void execute_amountUnspecified_personsFound() {
        String expectedMessage = MESSAGE_SUCCESS_GENERIC;
        ListDebtorsCommand command = new ListDebtorsCommand();
        Predicate<Person> predicate = p -> !p.getDebts().isEmpty();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, FIONA, GEORGE), model.getFilteredPersonList());
    }
}
