package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.logic.commands.CommandTestUtil.assertCommandFailure;
import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.CommandTestUtil.showDebtors;
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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SplitDebtCommand}.
 */
public class SplitDebtCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexFollowedByValidDebtOnUnfilteredList_success() {
        Debt validDebt = new DebtBuilder().build();
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON));
        SplitDebtCommand splitDebtCommand = new SplitDebtCommand(personIndexSet, validDebt);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(personToEdit.getDebts()).build().addDebt(validDebt);
        Person editedPerson = new PersonBuilder(personToEdit).withDebts(expectedDebtList).build();

        String nameList = editedPerson.getName().toString();
        String expectedMessage = String.format(SplitDebtCommand.MESSAGE_SPLIT_DEBT_SUCCESS, validDebt, nameList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(splitDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFollowedByValidDebtOnFilteredList_success() {
        showDebtors(model);

        Debt validDebt = new DebtBuilder().build();
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON));
        SplitDebtCommand splitDebtCommand = new SplitDebtCommand(personIndexSet, validDebt);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DebtList expectedDebtList = new DebtListBuilder(personToEdit.getDebts()).build().addDebt(validDebt);
        Person editedPerson = new PersonBuilder(personToEdit).withDebts(expectedDebtList).build();

        String nameList = editedPerson.getName().toString();
        String expectedMessage = String.format(SplitDebtCommand.MESSAGE_SPLIT_DEBT_SUCCESS, validDebt, nameList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showDebtors(expectedModel);

        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(splitDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesFollowedByValidDebtOnUnfilteredList_success() {
        Debt validDebt = new DebtBuilder().build();
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_SECOND_PERSON, INDEX_THIRD_PERSON,
                INDEX_FIRST_PERSON));
        SplitDebtCommand splitDebtCommand = new SplitDebtCommand(personIndexSet, validDebt);

        Person personToEditOne = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DebtList expectedDebtListOne = new DebtListBuilder(personToEditOne.getDebts()).build().addDebt(validDebt);
        Person editedPersonOne = new PersonBuilder(personToEditOne).withDebts(expectedDebtListOne).build();

        Person personToEditTwo = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DebtList expectedDebtListTwo = new DebtListBuilder(personToEditTwo.getDebts()).build().addDebt(validDebt);
        Person editedPersonTwo = new PersonBuilder(personToEditTwo).withDebts(expectedDebtListTwo).build();

        Person personToEditThree = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        DebtList expectedDebtListThree = new DebtListBuilder(personToEditThree.getDebts()).build().addDebt(validDebt);
        Person editedPersonThree = new PersonBuilder(personToEditThree).withDebts(expectedDebtListThree).build();

        StringBuilder nameList = new StringBuilder().append(editedPersonOne.getName().toString()).append(", ")
                .append(editedPersonTwo.getName().toString()).append(" and ")
                .append(editedPersonThree.getName().toString());
        String expectedMessage = String.format(SplitDebtCommand.MESSAGE_SPLIT_DEBT_SUCCESS, validDebt, nameList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                editedPersonOne);
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                editedPersonTwo);
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                editedPersonThree);

        assertCommandSuccess(splitDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesFollowedByValidDebtOnFilteredList_success() {
        showDebtors(model);

        Debt validDebt = new DebtBuilder().build();
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_SECOND_PERSON, INDEX_THIRD_PERSON,
                INDEX_FIRST_PERSON));
        SplitDebtCommand splitDebtCommand = new SplitDebtCommand(personIndexSet, validDebt);

        Person personToEditOne = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DebtList expectedDebtListOne = new DebtListBuilder(personToEditOne.getDebts()).build().addDebt(validDebt);
        Person editedPersonOne = new PersonBuilder(personToEditOne).withDebts(expectedDebtListOne).build();

        Person personToEditTwo = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DebtList expectedDebtListTwo = new DebtListBuilder(personToEditTwo.getDebts()).build().addDebt(validDebt);
        Person editedPersonTwo = new PersonBuilder(personToEditTwo).withDebts(expectedDebtListTwo).build();

        Person personToEditThree = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        DebtList expectedDebtListThree = new DebtListBuilder(personToEditThree.getDebts()).build().addDebt(validDebt);
        Person editedPersonThree = new PersonBuilder(personToEditThree).withDebts(expectedDebtListThree).build();

        StringBuilder nameList = new StringBuilder().append(editedPersonOne.getName().toString()).append(", ")
                .append(editedPersonTwo.getName().toString()).append(" and ")
                .append(editedPersonThree.getName().toString());
        String expectedMessage = String.format(SplitDebtCommand.MESSAGE_SPLIT_DEBT_SUCCESS, validDebt, nameList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showDebtors(expectedModel);

        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                editedPersonOne);
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                editedPersonTwo);
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                editedPersonThree);

        assertCommandSuccess(splitDebtCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexOnUnfilteredList_throwsCommandException() {
        Debt validDebt = new DebtBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> personIndexSet = new HashSet<>(List.of(outOfBoundIndex, INDEX_THIRD_PERSON,
                INDEX_FIRST_PERSON));
        SplitDebtCommand splitDebtCommand = new SplitDebtCommand(personIndexSet, validDebt);

        assertCommandFailure(splitDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexOnFilteredList_throwsCommandException() {
        showDebtors(model);

        Debt validDebt = new DebtBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> personIndexSet = new HashSet<>(List.of(outOfBoundIndex, INDEX_THIRD_PERSON,
                INDEX_FIRST_PERSON));
        SplitDebtCommand splitDebtCommand = new SplitDebtCommand(personIndexSet, validDebt);

        assertCommandFailure(splitDebtCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Index> indexSetFirst = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
        Set<Index> indexSetSecond = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON));

        Debt firstDebt = new DebtBuilder().build();
        Debt secondDebt = new DebtBuilder().withDescription("different").build();

        SplitDebtCommand splitDebtFirstCommand = new SplitDebtCommand(indexSetFirst, firstDebt);
        SplitDebtCommand splitDebtSecondCommand = new SplitDebtCommand(indexSetFirst, secondDebt);
        SplitDebtCommand splitDebtThirdCommand = new SplitDebtCommand(indexSetSecond, firstDebt);
        // same object -> returns true
        assertTrue(splitDebtFirstCommand.equals(splitDebtFirstCommand));
        // same values -> returns true
        SplitDebtCommand splitDebtFirstCommandCopy = new SplitDebtCommand(indexSetFirst, firstDebt);
        assertTrue(splitDebtFirstCommand.equals(splitDebtFirstCommandCopy));
        // different types -> returns false
        assertFalse(splitDebtFirstCommand.equals(1));
        // null -> returns false
        assertFalse(splitDebtFirstCommand.equals(null));
        // different Debt -> returns false
        assertFalse(splitDebtFirstCommand.equals(splitDebtSecondCommand));
        // different set of personIndexes -> returns false
        assertFalse(splitDebtFirstCommand.equals(splitDebtThirdCommand));
    }
}
