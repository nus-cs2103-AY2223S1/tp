package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

public class TaskTest {

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, "", new HashSet<>()));
        assertThrows(NullPointerException.class, () -> new Task("", null, new HashSet<>()));
        assertThrows(NullPointerException.class, () -> new Task("", "", null));
    }

    @Test
    public void constructor_validArgument_createCorrectTask() {
        Task a = new Task("Purchase goods", "14 Oct 2022", new HashSet<>());
        assertEquals("Purchase goods", a.getTitle());
        assertEquals("14 Oct 2022", a.getDeadline());
        assertFalse(a.getStatus());
        assertEquals("Not Done", a.getParsedStatus());
        assertEquals(Collections.emptySet(), a.getTags());
        assertEquals("Title: Purchase goods; Deadline: 14 Oct 2022, Status: Not Done", a.toString());
    }

    @Test
    public void equals() {
        Task a = new Task("Purchase goods", "14 Oct 2022", new HashSet<>());
        Task b = new Task("Purchase goods", "14 Oct 2022", new HashSet<>());
        assertEquals(a, b);
    }
}
