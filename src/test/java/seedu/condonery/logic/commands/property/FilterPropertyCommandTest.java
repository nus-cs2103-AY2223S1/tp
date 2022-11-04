package seedu.condonery.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.condonery.model.property.PropertyTagContainsKeywordsPredicate;

public class FilterPropertyCommandTest {
    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterPropertyCommand(null));
    }

    @Test
    public void equals() {
        PropertyTagContainsKeywordsPredicate richPredicate =
                new PropertyTagContainsKeywordsPredicate(new ArrayList<>(List.of("RICH")));
        PropertyTagContainsKeywordsPredicate lookingPredicate =
                new PropertyTagContainsKeywordsPredicate(new ArrayList<>(List.of("LOOKING")));

        FilterPropertyCommand filterPropertyCommand = new FilterPropertyCommand(richPredicate);
        FilterPropertyCommand anotherFilterPropertyCommand = new FilterPropertyCommand(lookingPredicate);

        // same object -> returns true
        assertTrue(filterPropertyCommand.equals(filterPropertyCommand));

        // same values -> returns true
        FilterPropertyCommand selectFirstCommandCopy = new FilterPropertyCommand(richPredicate);
        assertTrue(filterPropertyCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterPropertyCommand.equals(1));

        // null -> returns false
        assertFalse(filterPropertyCommand.equals(null));

        // different person -> returns false
        assertFalse(filterPropertyCommand.equals(anotherFilterPropertyCommand));
    }

}
