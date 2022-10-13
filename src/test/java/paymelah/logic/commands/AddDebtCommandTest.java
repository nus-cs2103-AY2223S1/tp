package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static paymelah.logic.commands.CommandTestUtil.assertCommandFailure;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.CommandTestUtil.showPersonAtIndex;
import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalDebts.SUPPER;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.model.AddressBook;
import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Person;
import paymelah.testutil.DebtBuilder;
import paymelah.testutil.DebtListBuilder;
import paymelah.testutil.PersonBuilder;

public class AddDebtCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDebtCommand(null, SUPPER));
    }

    @Test
    public void constructor_nullDebt_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDebtCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index index = INDEX_FIRST_PERSON;
        Debt validDebt = new DebtBuilder().build();
        Person personToAddDebt = model.getFilteredPersonList().get(index.getZeroBased());
        AddDebtCommand addDebtCommand = new AddDebtCommand(INDEX_FIRST_PERSON, validDebt);

        DebtList expectedDebtList = new DebtListBuilder(personToAddDebt.getDebts()).withDebt(validDebt).build();
        Person expectedPerson = new PersonBuilder(personToAddDebt).withDebts(expectedDebtList).build();
        String expectedMessage = String.format(
                AddDebtCommand.MESSAGE_ADD_DEBT_SUCCESS, validDebt, expectedPerson.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        assertCommandSuccess(addDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(outOfBoundIndex, validDebt);

        assertCommandFailure(addDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index index = INDEX_FIRST_PERSON;
        Debt validDebt = new DebtBuilder().build();
        Person personToAddDebt = model.getFilteredPersonList().get(index.getZeroBased());
        AddDebtCommand addDebtCommand = new AddDebtCommand(INDEX_FIRST_PERSON, validDebt);

        DebtList expectedDebtList = new DebtListBuilder(personToAddDebt.getDebts()).withDebt(validDebt).build();
        Person expectedPerson = new PersonBuilder(personToAddDebt).withDebts(expectedDebtList).build();
        String expectedMessage = String.format(
                AddDebtCommand.MESSAGE_ADD_DEBT_SUCCESS, validDebt, expectedPerson.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        assertCommandSuccess(addDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(outOfBoundIndex, validDebt);

        assertCommandFailure(addDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Debt supper = new DebtBuilder().withDescription("supper").build();
        Debt dinner = new DebtBuilder().withDescription("dinner").build();
        AddDebtCommand addSupperToOneCommand = new AddDebtCommand(INDEX_FIRST_PERSON, supper);
        AddDebtCommand addSupperToTwoCommand = new AddDebtCommand(INDEX_SECOND_PERSON, supper);
        AddDebtCommand addDinnerToOneCommand = new AddDebtCommand(INDEX_FIRST_PERSON, dinner);

        // same object -> returns true
        assertEquals(addSupperToOneCommand, addSupperToOneCommand);

        // same values -> returns true
        AddDebtCommand addSupperToOneCopy = new AddDebtCommand(INDEX_FIRST_PERSON, supper);
        assertEquals(addSupperToOneCommand, addSupperToOneCopy);

        // different types -> returns false
        assertNotEquals(1, addSupperToOneCommand);

        // null -> returns false
        assertNotEquals(null, addSupperToOneCommand);

        // different index -> returns false
        assertNotEquals(addSupperToOneCommand, addSupperToTwoCommand);

        // different debt -> returns false
        assertNotEquals(addSupperToOneCommand, addDinnerToOneCommand);
    }
}
