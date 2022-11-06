package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.testutil.TypicalTags;

public class TaskProgressCommandTest {
    private static Set<Tag> firstTagList = new HashSet<>();
    private static Set<Tag> secondTagList = Set.copyOf(TypicalTags.getTypicalTags());

    private static final TaskContainsKeywordsPredicate firstPredicate =
            new TaskContainsKeywordsPredicate(firstTagList);
    private static final TaskContainsKeywordsPredicate secondPredicate =
            new TaskContainsKeywordsPredicate(secondTagList);

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskProgressCommand(null));
    }

    @Test
    public void equals() {
        final TaskProgressCommand firstProgressCommand = new TaskProgressCommand(firstPredicate);
        final TaskProgressCommand secondProgressCommand = new TaskProgressCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstProgressCommand.equals(firstProgressCommand));

        // same values -> returns true
        TaskProgressCommand firstProgressCommandCopy = new TaskProgressCommand(firstPredicate);
        assertTrue(firstProgressCommand.equals(firstProgressCommandCopy));

        // different types -> returns false
        assertFalse(firstProgressCommand.equals(1));

        // null -> returns false
        assertFalse(firstProgressCommand.equals(null));

        // different tag list -> returns false
        assertFalse(firstProgressCommand.equals(secondProgressCommand));
    }
}
