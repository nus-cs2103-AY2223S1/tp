package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_TEAM;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND_TEAM;

import org.junit.jupiter.api.Test;

class EditTeamCommandTest {
    @Test
    void testEquals() {
        EditTeamCommand standardCommand = new EditTeamCommand(DESC_FIRST_TEAM);

        EditTeamCommand commandWithSameValues = new EditTeamCommand(DESC_FIRST_TEAM);
        EditTeamCommand commandWithDifferentDescriptor = new EditTeamCommand(DESC_SECOND_TEAM);

        //Same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        //Same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        //Different descriptor -> returns false
        assertFalse(standardCommand.equals(commandWithDifferentDescriptor));

        //null -> returns false
        assertFalse(standardCommand.equals(null));

        //different type -> returns false
        assertFalse(standardCommand.equals(5));
    }
}
