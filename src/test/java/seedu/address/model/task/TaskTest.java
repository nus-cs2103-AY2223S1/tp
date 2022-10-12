package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;

public class TaskTest {
    private final Task sampleA = new Task(
            new TaskName("A"),
            new Module("B"),
            new Deadline("2006-01-15 15:04"),
            new Status(false)
    );

    private final Task sampleB = new Task(
            new TaskName("A"),
            new Module("B"),
            new Deadline("2006-01-15 15:04"),
            new Status(false)
    );

    private final Task sampleC = new Task(
            new TaskName("A"),
            new Module("B"),
            new Deadline("2010-04-15 08:30"),
            new Status(false)
    );

    private final Task sampleD = new Task(
            new TaskName("Dugong"),
            new Module("B"),
            new Deadline("2006-01-15 15:04"),
            new Status(false)
    );

    private final Task sampleE = new Task(
            new TaskName("a"),
            new Module("B"),
            new Deadline("2006-01-15 15:04"),
            new Status(false)
    );

    private final Task sampleF = new Task(
            new TaskName("A "),
            new Module("B"),
            new Deadline("2006-01-15 15:04"),
            new Status(false)
    );

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(sampleA.isSameTask(sampleA));
        // null -> returns false
        assertFalse(sampleA.isSameTask(null));
        // Same attributes -> returns true
        assertTrue(sampleA.isSameTask(sampleB));
        // Same attributes except date -> returns true
        assertTrue(sampleA.isSameTask(sampleC));
        // different name -> returns false
        assertFalse(sampleA.isSameTask(sampleD));
        // different name casing -> returns false
        assertFalse(sampleA.isSameTask(sampleE));
        // trailing space in name -> returns false
        assertFalse(sampleA.isSameTask(sampleF));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(sampleA, sampleA);
        // null -> returns false
        assertNotEquals(sampleA, null);
        // Same attributes -> returns true
        assertEquals(sampleA, sampleB);
        // Same attributes except date -> returns true
        assertNotEquals(sampleA, sampleC);
        // different name -> returns false
        assertNotEquals(sampleA, sampleD);
        // different name casing -> returns false
        assertNotEquals(sampleA, sampleE);
        // trailing space in name -> returns false
        assertNotEquals(sampleA, sampleF);
    }

    @Test
    public void withCompletion_smokeTest() {
        Task a = sampleA.withCompletion(true);
        Task b = sampleB.withCompletion(false);
        Task c = sampleA.withCompletion(true).withCompletion(false);
        assertTrue(a.getStatus().getIsComplete());
        assertFalse(b.getStatus().getIsComplete());
        assertFalse(c.getStatus().getIsComplete());
    }

}
