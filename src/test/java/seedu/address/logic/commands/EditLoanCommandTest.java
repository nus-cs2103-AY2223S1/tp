package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.EditLoanCommand.EditLoanDescriptor;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reason;
import seedu.address.model.person.exceptions.LoanOutOfBoundsException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditLoanCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        Loan initialLoan = model.getAddressBook().getPersonList().get(0).getLoan();
        Loan changedLoan = new Loan(initialLoan.getAmount() + 5.55);

        LoanHistory addedLoan = new LoanHistory(
                new Loan(5.55),
                new Reason("Test")
        );

        EditLoanDescriptor descriptor =
                new EditLoanDescriptor(changedLoan, addedLoan);

        EditLoanCommand editLoanCommand =
                new EditLoanCommand(INDEX_FIRST_PERSON, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person initialPerson = model.getFilteredPersonList().get(0);
        List<LoanHistory> newHistory = new ArrayList<>(initialPerson.getHistory());
        descriptor.getHistory().ifPresent(newHistory::add);

        Person editedPerson = new Person(
                initialPerson.getName(),
                initialPerson.getPhone(),
                initialPerson.getEmail(),
                initialPerson.getAddress(),
                initialPerson.getBirthday(),
                initialPerson.getTags(),
                descriptor.getLoan().orElseThrow(() -> new NullPointerException("No loan found!")),
                newHistory);

        expectedModel.setPerson(initialPerson, editedPerson);

        CommandResult expectedResult = new CommandResult(
                String.format(EditLoanCommand.MESSAGE_EDIT_LOAN_SUCCESS,
                        expectedModel.getAddressBook().getPersonList().get(0)),
                CommandResult.UiState.Inspect, "1");

        assertCommandSuccess(editLoanCommand, model, expectedResult, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Loan initialLoan = personInFilteredList.getLoan();
        Loan incrementLoan = new Loan("-$31.66");
        Loan changedLoan;

        try {
            changedLoan = initialLoan.addBy(incrementLoan);
        } catch (LoanOutOfBoundsException e) {
            fail();
            return;
        }

        EditLoanDescriptor editLoanDescriptor = new EditLoanDescriptor(changedLoan,
                new LoanHistory(incrementLoan, new Reason("Testing")));

        List<LoanHistory> loanHistoryList = personInFilteredList.getHistory();
        editLoanDescriptor.getHistory().ifPresent(loanHistoryList::add);


        Person editedPerson = new Person(
                personInFilteredList.getName(), personInFilteredList.getPhone(),
                personInFilteredList.getEmail(), personInFilteredList.getAddress(),
                personInFilteredList.getBirthday(), personInFilteredList.getTags(),
                editLoanDescriptor.getLoan().orElseThrow(() ->
                        new NullPointerException("There was no loan")),
                loanHistoryList
        );

        EditLoanCommand editLoanCommand = new EditLoanCommand(INDEX_FIRST_PERSON, editLoanDescriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setPerson(personInFilteredList, editedPerson);

        CommandResult expectedResult = new CommandResult(
                String.format(EditLoanCommand.MESSAGE_EDIT_LOAN_SUCCESS,
                        expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())),
                CommandResult.UiState.Inspect, "1");

        assertCommandSuccess(editLoanCommand, model, expectedResult, expectedModel);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditLoanDescriptor descriptor = new EditLoanDescriptor(new Loan(0),
                new LoanHistory(new Loan(0), new Reason("This does not matter")));
        EditLoanCommand editCommand = new EditLoanCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Checks that a value too large causes an LoanOutOfBounds exception caught
     */
    @Test
    public void execute_loanTooLarge_throwsOutOfBounds() {
        Loan incrementLoan = new Loan("500000000000.01");

        LoanHistory loanHistory = new LoanHistory(
                incrementLoan,
                new Reason("Test")
        );

        EditLoanDescriptor descriptor =
                new EditLoanDescriptor(incrementLoan, loanHistory);

        EditLoanCommand editLoanCommand =
                new EditLoanCommand(INDEX_FIRST_PERSON, descriptor);

        try {
            editLoanCommand.execute(model);
        } catch (CommandException e) {
            fail();
        }

        assertCommandFailure(editLoanCommand, model, EditLoanCommand.OUT_OF_BOUNDS_NOTIFICATION);
    }

    /**
     * Checks that a value too small causes an LoanOutOfBounds exception caught
     */
    @Test
    public void execute_loanTooSmall_throwsOutOfBounds() {
        Loan incrementLoan = new Loan("-500000000000.01");

        LoanHistory loanHistory = new LoanHistory(
                incrementLoan,
                new Reason("Test")
        );

        EditLoanDescriptor descriptor =
                new EditLoanDescriptor(incrementLoan, loanHistory);

        EditLoanCommand editLoanCommand =
                new EditLoanCommand(INDEX_FIRST_PERSON, descriptor);

        try {
            editLoanCommand.execute(model);
        } catch (CommandException e) {
            fail();
        }

        assertCommandFailure(editLoanCommand, model, EditLoanCommand.OUT_OF_BOUNDS_NOTIFICATION);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditLoanCommand editLoanCommand = new EditLoanCommand(outOfBoundIndex,
                new EditLoanDescriptor(new Loan(0),
                        new LoanHistory(new Loan(0), new Reason("This does not matter"))));

        assertCommandFailure(editLoanCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditLoanDescriptor editLoanDescriptor = new EditLoanDescriptor(new Loan(355.13),
                new LoanHistory(new Loan(55.13), new Reason("Special")));
        final EditLoanCommand standardCommand = new EditLoanCommand(INDEX_FIRST_PERSON,
                editLoanDescriptor);

        // same values -> returns true
        EditLoanDescriptor copyDescriptor = new EditLoanDescriptor(editLoanDescriptor);
        EditLoanCommand commandWithSameValues = new EditLoanCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditLoanCommand(INDEX_SECOND_PERSON, editLoanDescriptor));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditLoanCommand(INDEX_FIRST_PERSON,
                new EditLoanDescriptor(new Loan("50"),
                        new LoanHistory(new Loan("1"), new Reason("Different descriptor")
                        )
                ))
        );
    }

}
