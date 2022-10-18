package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_DATE_RANGE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_IS_ROOM_CLEAN_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_NUMBER_OF_GUESTS_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_ROOM_BOB;

import org.junit.jupiter.api.Test;

import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.testutil.EditGuestDescriptorBuilder;

public class EditGuestDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditGuestDescriptor descriptorWithSameValues = new EditCommand.EditGuestDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditGuestDescriptor editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different room -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withRoom(VALID_ROOM_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different date range -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withDateRange(VALID_DATE_RANGE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different number of guests -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withNumberOfGuests(VALID_NUMBER_OF_GUESTS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different is room clean -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_AMY).withIsRoomClean(VALID_IS_ROOM_CLEAN_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
