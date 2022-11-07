package seedu.condonery.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

public class SelectClientCommandTest {

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SelectClientCommand(null));
    }

    @Test
    public void equals() {
        SelectClientCommand selectFirstCommand = new SelectClientCommand(INDEX_FIRST);
        SelectClientCommand selectSecondCommand = new SelectClientCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectClientCommand selectFirstCommandCopy = new SelectClientCommand(INDEX_FIRST);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }
}
