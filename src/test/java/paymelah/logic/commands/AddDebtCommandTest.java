package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static paymelah.logic.commands.AddDebtCommand.MESSAGE_DUPLICATE_DEBT;
import static paymelah.logic.commands.CommandTestUtil.FIRST_VALID_MULTI_INDEX;
import static paymelah.logic.commands.CommandTestUtil.MULTI_VALID_INDEX_SET;
import static paymelah.logic.commands.CommandTestUtil.SECOND_VALID_MULTI_INDEX;
import static paymelah.logic.commands.CommandTestUtil.SINGLE_VALID_INDEX;
import static paymelah.logic.commands.CommandTestUtil.SINGLE_VALID_INDEX_SET;
import static paymelah.logic.commands.CommandTestUtil.THIRD_VALID_MULTI_INDEX;
import static paymelah.logic.commands.CommandTestUtil.THIRD_VALID_MULTI_INDEX_SET;
import static paymelah.logic.commands.CommandTestUtil.assertCommandFailure;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.CommandTestUtil.showDebtors;
import static paymelah.logic.commands.CommandTestUtil.showPersonAtIndex;
import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalDebts.SUPPER;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.Messages;
import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
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
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
    private final Set<Index> outOfBoundIndexSet = new HashSet<>(List.of(outOfBoundIndex));
    private final Set<Index> singleInvalidIndexSet = new HashSet<>(List.of(FIRST_VALID_MULTI_INDEX, outOfBoundIndex));

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDebtCommand(null, SUPPER));
    }

    @Test
    public void constructor_nullDebt_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDebtCommand(SINGLE_VALID_INDEX_SET, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Debt validDebt = new DebtBuilder().build();

        Person personToAddDebt = model.getFilteredPersonList().get(SINGLE_VALID_INDEX.getZeroBased());
        AddDebtCommand addDebtCommand = new AddDebtCommand(SINGLE_VALID_INDEX_SET, validDebt);

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
        Set<Index> outOfBoundIndexSet = new HashSet<>();
        outOfBoundIndexSet.add(outOfBoundIndex);
        Debt validDebt = new DebtBuilder().build();

        AddDebtCommand addDebtCommand = new AddDebtCommand(outOfBoundIndexSet, validDebt);
        assertCommandFailure(addDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, SINGLE_VALID_INDEX);

        Debt validDebt = new DebtBuilder().build();
        Person personToAddDebt = model.getFilteredPersonList().get(SINGLE_VALID_INDEX.getZeroBased());
        AddDebtCommand addDebtCommand = new AddDebtCommand(SINGLE_VALID_INDEX_SET, validDebt);

        DebtList expectedDebtList = new DebtListBuilder(personToAddDebt.getDebts()).withDebt(validDebt).build();
        Person expectedPerson = new PersonBuilder(personToAddDebt).withDebts(expectedDebtList).build();
        String expectedMessage = String.format(
                AddDebtCommand.MESSAGE_ADD_DEBT_SUCCESS, validDebt, expectedPerson.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, SINGLE_VALID_INDEX);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        assertCommandSuccess(addDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(outOfBoundIndexSet, validDebt);

        assertCommandFailure(addDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_singleInvalidIndexUnfilteredList_throwsCommandException() {
        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(singleInvalidIndexSet, validDebt);

        assertCommandFailure(addDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_singleInvalidIndexFilteredList_throwsCommandException() {
        showDebtors(model);

        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(singleInvalidIndexSet, validDebt);

        assertCommandFailure(addDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(MULTI_VALID_INDEX_SET, validDebt);

        Person personToEditOne = model.getFilteredPersonList().get(FIRST_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtListOne = new DebtListBuilder(personToEditOne.getDebts()).build().addDebt(validDebt);
        Person editedPersonOne = new PersonBuilder(personToEditOne).withDebts(expectedDebtListOne).build();

        Person personToEditTwo = model.getFilteredPersonList().get(SECOND_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtListTwo = new DebtListBuilder(personToEditTwo.getDebts()).build().addDebt(validDebt);
        Person editedPersonTwo = new PersonBuilder(personToEditTwo).withDebts(expectedDebtListTwo).build();

        Person personToEditThree = model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtListThree = new DebtListBuilder(personToEditThree.getDebts()).build().addDebt(validDebt);
        Person editedPersonThree = new PersonBuilder(personToEditThree).withDebts(expectedDebtListThree).build();

        StringBuilder nameList = new StringBuilder().append(editedPersonOne.getName().toString()).append(", ")
                .append(editedPersonTwo.getName().toString()).append(" and ")
                .append(editedPersonThree.getName().toString());
        String expectedMessage = String.format(AddDebtCommand.MESSAGE_ADD_DEBT_SUCCESS, validDebt, nameList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(FIRST_VALID_MULTI_INDEX.getZeroBased()),
                editedPersonOne);
        expectedModel.setPerson(model.getFilteredPersonList().get(SECOND_VALID_MULTI_INDEX.getZeroBased()),
                editedPersonTwo);
        expectedModel.setPerson(model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased()),
                editedPersonThree);

        assertCommandSuccess(addDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesFilteredList_success() {
        showDebtors(model);

        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand addDebtCommand = new AddDebtCommand(MULTI_VALID_INDEX_SET, validDebt);

        Person personToEditOne = model.getFilteredPersonList().get(FIRST_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtListOne = new DebtListBuilder(personToEditOne.getDebts()).build().addDebt(validDebt);
        Person editedPersonOne = new PersonBuilder(personToEditOne).withDebts(expectedDebtListOne).build();

        Person personToEditTwo = model.getFilteredPersonList().get(SECOND_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtListTwo = new DebtListBuilder(personToEditTwo.getDebts()).build().addDebt(validDebt);
        Person editedPersonTwo = new PersonBuilder(personToEditTwo).withDebts(expectedDebtListTwo).build();

        Person personToEditThree = model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtListThree = new DebtListBuilder(personToEditThree.getDebts()).build().addDebt(validDebt);
        Person editedPersonThree = new PersonBuilder(personToEditThree).withDebts(expectedDebtListThree).build();

        StringBuilder nameList = new StringBuilder().append(editedPersonOne.getName().toString()).append(", ")
                .append(editedPersonTwo.getName().toString()).append(" and ")
                .append(editedPersonThree.getName().toString());
        String expectedMessage = String.format(AddDebtCommand.MESSAGE_ADD_DEBT_SUCCESS, validDebt, nameList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showDebtors(expectedModel);

        expectedModel.setPerson(model.getFilteredPersonList().get(FIRST_VALID_MULTI_INDEX.getZeroBased()),
                editedPersonOne);
        expectedModel.setPerson(model.getFilteredPersonList().get(SECOND_VALID_MULTI_INDEX.getZeroBased()),
                editedPersonTwo);
        expectedModel.setPerson(model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased()),
                editedPersonThree);

        assertCommandSuccess(addDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDebtUnfilteredList_throwsCommandException() {
        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand firstAddDebtCommand = new AddDebtCommand(THIRD_VALID_MULTI_INDEX_SET, validDebt);

        Person expectedPerson = model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(expectedPerson.getDebts()).build().addDebt(validDebt);
        Person editedPerson = new PersonBuilder(expectedPerson).withDebts(expectedDebtList).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased()),
                editedPerson);

        // 1st insertion of debt
        try {
            firstAddDebtCommand.execute(model);
        } catch (CommandException e) {
            fail();
        }

        // 2nd insertion of debt
        AddDebtCommand secondAddDebtCommand = new AddDebtCommand(MULTI_VALID_INDEX_SET, validDebt);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_DEBT, validDebt, expectedPerson.getName());
        assertCommandFailure(secondAddDebtCommand, model, expectedMessage);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_duplicateDebtFilteredList_throwsCommandException() {
        showDebtors(model);

        Debt validDebt = new DebtBuilder().build();
        AddDebtCommand firstAddDebtCommand = new AddDebtCommand(THIRD_VALID_MULTI_INDEX_SET, validDebt);
        AddDebtCommand secondAddDebtCommand = new AddDebtCommand(MULTI_VALID_INDEX_SET, validDebt);

        Person expectedPerson = model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(expectedPerson.getDebts()).build().addDebt(validDebt);
        Person editedPerson = new PersonBuilder(expectedPerson).withDebts(expectedDebtList).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showDebtors(expectedModel);
        expectedModel.setPerson(model.getFilteredPersonList().get(THIRD_VALID_MULTI_INDEX.getZeroBased()),
                editedPerson);

        // 1st insertion of debt
        try {
            firstAddDebtCommand.execute(model);
        } catch (CommandException e) {
            fail();
        }

        // 2nd insertion of debt
        String expectedMessage = String.format(MESSAGE_DUPLICATE_DEBT, validDebt, expectedPerson.getName());
        assertCommandFailure(secondAddDebtCommand, model, expectedMessage);
        assertEquals(expectedModel, model);
    }

    @Test
    public void equals() {
        Debt supper = new DebtBuilder().withDescription("supper").build();
        Debt dinner = new DebtBuilder().withDescription("dinner").build();
        Set<Index> indexSetOne = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
        Set<Index> indexSetTwo = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON));

        AddDebtCommand addSupperToOneCommand = new AddDebtCommand(indexSetOne, supper);
        AddDebtCommand addSupperToTwoCommand = new AddDebtCommand(indexSetTwo, supper);
        AddDebtCommand addDinnerToOneCommand = new AddDebtCommand(indexSetOne, dinner);

        // same object -> returns true
        assertEquals(addSupperToOneCommand, addSupperToOneCommand);

        // same values -> returns true
        AddDebtCommand addSupperToOneCopy = new AddDebtCommand(indexSetOne, supper);
        assertEquals(addSupperToOneCommand, addSupperToOneCopy);

        // different types -> returns false
        assertNotEquals(1, addSupperToOneCommand);

        // null -> returns false
        assertNotEquals(null, addSupperToOneCommand);

        // different indices -> returns false
        assertNotEquals(addSupperToOneCommand, addSupperToTwoCommand);

        // different debt -> returns false
        assertNotEquals(addSupperToOneCommand, addDinnerToOneCommand);
    }
}
