package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_BILL_NEGATIVE;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_BILL_POSITIVE;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_BILL_ZERO;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.logic.commands.CommandTestUtil.showGuestAtIndex;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;
import static seedu.guest.testutil.TypicalIndexes.INDEX_SECOND_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.core.Messages;
import seedu.guest.commons.core.index.Index;
import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.Guest;
import seedu.guest.testutil.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code BillCommand}.
 */
public class BillCommandTest {

    private static final String MAX_BILL = "999999999999.99";

    private Model model = new ModelManager(getTypicalGuestBook(), new UserPrefs());

    @Test
    public void execute_addBillUnfilteredList_success() {
        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 0
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_POSITIVE)); // bill + 1 = 1

        GuestBuilder guestInList = new GuestBuilder(guestToBill);
        Guest billedGuest = guestInList.withBill(VALID_BILL_POSITIVE).build();

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, billedGuest);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestToBill, billedGuest);

        assertCommandSuccess(billCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_subtractBillUnfilteredList_success() {
        Guest guestToBill = model.getFilteredGuestList().get(INDEX_SECOND_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_SECOND_GUEST, new Bill(VALID_BILL_NEGATIVE)); // bill - 1 = 0

        GuestBuilder guestInList = new GuestBuilder(guestToBill);
        Guest billedGuest = guestInList.withBill(VALID_BILL_ZERO).build();

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, billedGuest);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestToBill, billedGuest);

        assertCommandSuccess(billCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unchangedBillUnfilteredList_success() {
        Guest guestToBill = model.getFilteredGuestList().get(INDEX_SECOND_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_SECOND_GUEST, new Bill()); // bill + 0 = 1

        GuestBuilder guestInList = new GuestBuilder(guestToBill);
        Guest billedGuest = guestInList.withBill(VALID_BILL_POSITIVE).build();

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, billedGuest);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestToBill, billedGuest);

        assertCommandSuccess(billCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addBillFilteredList_success() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);

        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 0
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_POSITIVE)); // bill + 1 = 1

        GuestBuilder guestInList = new GuestBuilder(guestToBill);
        Guest billedGuest = guestInList.withBill(VALID_BILL_POSITIVE).build();

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, billedGuest);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestToBill, billedGuest);

        assertCommandSuccess(billCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_subtractBillFilteredList_success() {
        showGuestAtIndex(model, INDEX_SECOND_GUEST);

        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_NEGATIVE)); // bill - 1 = 0

        GuestBuilder guestInList = new GuestBuilder(guestToBill);
        Guest billedGuest = guestInList.withBill(VALID_BILL_ZERO).build();

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, billedGuest);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestToBill, billedGuest);

        assertCommandSuccess(billCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unchangedBillFilteredList_success() {
        showGuestAtIndex(model, INDEX_SECOND_GUEST);

        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill()); // bill + 0 = 1

        GuestBuilder guestInList = new GuestBuilder(guestToBill);
        Guest billedGuest = guestInList.withBill(VALID_BILL_POSITIVE).build();

        String expectedMessage = String.format(BillCommand.MESSAGE_SUCCESS, billedGuest);

        ModelManager expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
        expectedModel.setGuest(guestToBill, billedGuest);

        assertCommandSuccess(billCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_negativeBillUnfilteredList_failure() {
        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 0
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_NEGATIVE)); // bill - 1 = -1

        assertCommandFailure(billCommand, model, BillCommand.MESSAGE_NEGATIVE_BILL);
    }

    @Test
    public void execute_negativeBillFilteredList_failure() {
        showGuestAtIndex(model, INDEX_SECOND_GUEST);

        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill("-1.01")); // bill - 1.01 = -0.01

        assertCommandFailure(billCommand, model, BillCommand.MESSAGE_NEGATIVE_BILL);
    }

    @Test
    public void execute_exceedBillUnfilteredList_failure() {
        Guest guestToBill = model.getFilteredGuestList().get(INDEX_SECOND_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_SECOND_GUEST, new Bill(MAX_BILL)); // bill + MAX

        assertCommandFailure(billCommand, model, BillCommand.MESSAGE_EXCEED_BILL);
    }

    @Test
    public void execute_exceedBillFilteredList_failure() {
        showGuestAtIndex(model, INDEX_SECOND_GUEST);

        Guest guestToBill = model.getFilteredGuestList().get(INDEX_FIRST_GUEST.getZeroBased()); // bill = 1
        BillCommand billCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill(MAX_BILL)); // bill + MAX

        assertCommandFailure(billCommand, model, BillCommand.MESSAGE_EXCEED_BILL);
    }

    @Test
    public void execute_invalidGuestIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGuestList().size() + 1);

        BillCommand billCommand = new BillCommand(outOfBoundIndex, new Bill());

        assertCommandFailure(billCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    /**
     * Bill from filtered list where index is larger than size of filtered list,
     * but smaller than size of guest book
     */
    @Test
    public void execute_invalidGuestIndexFilteredList_failure() {
        showGuestAtIndex(model, INDEX_FIRST_GUEST);
        Index outOfBoundIndex = INDEX_SECOND_GUEST;
        // ensures that outOfBoundIndex is still in bounds of guest book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getGuestBook().getGuestList().size());

        BillCommand billCommand = new BillCommand(outOfBoundIndex, new Bill());

        assertCommandFailure(billCommand, model, Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final BillCommand standardCommand = new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_POSITIVE));

        // same values -> returns true
        BillCommand commandWithSameValues = new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_POSITIVE));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new BillCommand(INDEX_SECOND_GUEST, new Bill(VALID_BILL_POSITIVE)));

        // different value -> returns false
        assertNotEquals(standardCommand, new BillCommand(INDEX_FIRST_GUEST, new Bill(VALID_BILL_NEGATIVE)));
    }

}
