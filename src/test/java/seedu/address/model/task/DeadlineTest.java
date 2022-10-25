package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeadlines.FIRST;
import static seedu.address.testutil.TypicalDeadlines.SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeadlineBuilder;

public class DeadlineTest {

    private final TaskTitle validTaskTitle = new TaskTitle(VALID_TASK_TITLE);
    private final TaskDescription validTaskDescription = new TaskDescription(VALID_TASK_DESCRIPTION);
    private final FormatDate validFormatDate = new FormatDate(VALID_DEADLINE_DATE);

    @Test
    public void constructor_nullValues_throwsNullPointerException() {
        // all null
        assertThrows(NullPointerException.class, () -> new Deadline(null, null, null));

        // title null
        assertThrows(NullPointerException.class, () -> new Deadline(null, validTaskDescription, validFormatDate));

        // description null
        assertThrows(NullPointerException.class, () -> new Deadline(validTaskTitle, null, validFormatDate));

        // formatdate null
        assertThrows(NullPointerException.class, () -> new Deadline(validTaskTitle, validTaskDescription, null));
    }

    @Test
    public void getters_defaultDeadline_getsCorrectFields() {
        Deadline defaultDeadline = new Deadline(validTaskTitle, validTaskDescription, validFormatDate);

        assertEquals(validTaskTitle, defaultDeadline.getTitle());
        assertEquals(validTaskDescription, defaultDeadline.getDescription());
        assertEquals(validFormatDate, defaultDeadline.getDate());
    }

    @Test
    public void isSameDeadline() {
        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deadline firstCopy = new DeadlineBuilder(FIRST).build();
        assertTrue(FIRST.equals(firstCopy));

        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));

        // different type -> returns false
        assertFalse(FIRST.equals(5));

        // different task -> returns false
        assertFalse(FIRST.equals(SECOND));
    }
}
