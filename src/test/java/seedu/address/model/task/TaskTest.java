package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.util.DateUtil.getLocalDate;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, getLocalDate(VALID_DEADLINE), new HashSet<>()));
        assertThrows(NullPointerException.class, () -> new Task("", null, new HashSet<>()));
        assertThrows(NullPointerException.class, () -> new Task("", getLocalDate(VALID_DEADLINE), null));
    }

    @Test
    public void constructor_validArgument_createCorrectTask() {
        Task a = new Task("Purchase goods", getLocalDate("2022-10-14"), new HashSet<>());
        assertEquals("Purchase goods", a.getTitle());
        assertEquals(getLocalDate("2022-10-14"), a.getDeadline());
        assertFalse(a.getStatus());
        assertEquals("Not Done", a.getParsedStatus());
        assertEquals(Collections.emptySet(), a.getTags());
        assertEquals("Title: Purchase goods; Deadline: 2022-10-14, Status: Not Done", a.toString());
    }

    @Test
    public void equals() {
        Task a = new Task("Purchase goods", getLocalDate("2022-10-14"), new HashSet<>());
        Task b = new Task("Purchase goods", getLocalDate("2022-10-14"), new HashSet<>());
        assertEquals(a, b);
    }
}
