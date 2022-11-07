package seedu.condonery.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

public class SelectPropertyCommandTest {


    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SelectPropertyCommand(null));
    }

    @Test
    public void equals() {
        SelectPropertyCommand selectFirstCommand = new SelectPropertyCommand(INDEX_FIRST);
        SelectPropertyCommand selectSecondCommand = new SelectPropertyCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectPropertyCommand selectFirstCommandCopy = new SelectPropertyCommand(INDEX_FIRST);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }
}
