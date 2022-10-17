package taskbook.model.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.model.task.enums.Assignment;
import taskbook.testutil.EventBuilder;
import taskbook.testutil.TypicalTaskBook;

public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        Assertions.assertTrue(TypicalTaskBook.PARTYING.isSameTask(TypicalTaskBook.PARTYING));

        // null -> returns false
        Assertions.assertFalse(TypicalTaskBook.PARTYING.isSameTask(null));

        // same person, same assignment, same description, same deadline, all other attributes different
        // -> returns true
        Event editedEating = new EventBuilder().withPersonName(TypicalTaskBook.CARL)
                .withAssignment(Assignment.TO).withDescription("party at kevin's house").withIsDone(true)
                .withEventDate(LocalDate.of(2022, 10, 11)).build();
        Assertions.assertTrue(TypicalTaskBook.PARTYING.isSameTask(editedEating));

        // different person, all other attributes same
        // -> returns false
        editedEating = new EventBuilder().withPersonName(TypicalTaskBook.GEORGE)
                .withAssignment(Assignment.TO).withDescription("party at kevin's house").withIsDone(true)
                .withEventDate(LocalDate.of(2022, 10, 11)).build();
        Assertions.assertFalse(TypicalTaskBook.PARTYING.isSameTask(editedEating));
    }
}
