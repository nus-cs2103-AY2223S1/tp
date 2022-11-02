package seedu.condonery.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.condonery.model.client.ClientTagContainsKeywordsPredicate;

public class FilterClientCommandTest {
    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterClientCommand(null));
    }

    @Test
    public void equals() {
        ClientTagContainsKeywordsPredicate richPredicate =
                new ClientTagContainsKeywordsPredicate(new ArrayList<>(List.of("RICH")));
        ClientTagContainsKeywordsPredicate lookingPredicate =
                new ClientTagContainsKeywordsPredicate(new ArrayList<>(List.of("LOOKING")));

        FilterClientCommand filterClientCommand = new FilterClientCommand(richPredicate);
        FilterClientCommand anotherFilterClientCommand = new FilterClientCommand(lookingPredicate);

        // same object -> returns true
        assertTrue(filterClientCommand.equals(filterClientCommand));

        // same values -> returns true
        FilterClientCommand selectFirstCommandCopy = new FilterClientCommand(richPredicate);
        assertTrue(filterClientCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterClientCommand.equals(1));

        // null -> returns false
        assertFalse(filterClientCommand.equals(null));

        // different person -> returns false
        assertFalse(filterClientCommand.equals(anotherFilterClientCommand));
    }

}
