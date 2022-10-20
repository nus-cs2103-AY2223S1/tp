package taskbook.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.model.VersionedTaskBook.INVALID_REDO_ACTION;
import static taskbook.model.VersionedTaskBook.INVALID_UNDO_ACTION;
import static taskbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import taskbook.model.person.Person;
import taskbook.model.task.Task;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.TaskBookBuilder;
import taskbook.testutil.TodoBuilder;

public class VersionedTaskBookTest {

    private static final Person P1 = new PersonBuilder().withName("Person 1").build();
    private static final Person P2 = new PersonBuilder().withName("Person 2").build();
    private static final Person P3 = new PersonBuilder().withName("Person 3").build();
    private static final Task T1 = new TodoBuilder().withDescription("Task 1").build();
    private static final Task T2 = new TodoBuilder().withDescription("Task 2").build();
    private static final Task T3 = new TodoBuilder().withDescription("Task 3").build();
    private static final TaskBook ONE = new TaskBookBuilder().withPerson(P1).build();
    private static final TaskBook TWO = new TaskBookBuilder().withPerson(P2).build();
    private static final TaskBook THREE = new TaskBookBuilder().withPerson(P3).build();
    private static final TaskBook FOUR = new TaskBookBuilder().withTask(T1).build();
    private static final TaskBook FIVE = new TaskBookBuilder().withTask(T2).build();
    private static final TaskBook SIX = new TaskBookBuilder().withTask(T3).build();

    @Test
    public void equals() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        TaskBook initialState = new TaskBookBuilder().withPerson(P1).build();
        VersionedTaskBook differentVersioned = new VersionedTaskBook(initialState);

        assertEquals(versioned, versioned);
        assertNotEquals(versioned, differentVersioned);
        assertNotEquals(versioned, null);
        assertNotEquals(versioned, 0.1);
    }

    @Test
    public void constructor_copy_same() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        VersionedTaskBook copied = new VersionedTaskBook(versioned);

        assertEquals(versioned, copied);
    }

    @Test
    public void commit_differentState_success() {
        TaskBook initialState = new TaskBook();
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        TaskBook toCommit = new TaskBook(initialState);
        toCommit.addPerson(P1);
        assertDoesNotThrow(() -> versioned.commit(toCommit));
    }

    @Test
    public void commit_sameState_noChange() {
        TaskBook initialState = new TaskBook();
        VersionedTaskBook original = new VersionedTaskBook(initialState);
        VersionedTaskBook versioned = new VersionedTaskBook(original);
        TaskBook toCommit = new TaskBook(initialState);
        toCommit.addPerson(P1);
        assertEquals(original, versioned);
    }

    @Test
    public void commit_commitPastEvenCapacity_pruneHistory() {
        VersionedTaskBook versioned = new VersionedTaskBook(2, new TaskBook());
        versioned.commit(ONE);
        versioned.commit(TWO);
        versioned.commit(THREE);
        versioned.commit(FOUR);
        assertUndoEquals(THREE, versioned);
        assertRedoEquals(FOUR, versioned);
    }

    @Test
    public void commit_commitPastOddCapacity_pruneHistory() {
        VersionedTaskBook versioned = new VersionedTaskBook(3, new TaskBook());
        versioned.commit(ONE);
        versioned.commit(TWO);
        versioned.commit(THREE);
        versioned.commit(FOUR);
        versioned.commit(FIVE);
        versioned.commit(SIX);
        assertUndoEquals(FIVE, versioned);
        assertRedoEquals(SIX, versioned);
    }

    @Test
    public void commit_commitAfterUndo_cannotRedo() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        versioned.commit(ONE);
        assertDoesNotThrow(versioned::undo);
        versioned.commit(TWO);
        assertFalse(versioned.canUndo());
    }

    @Test
    public void canUndo_canUndo_true() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        versioned.commit(ONE);
        assertTrue(versioned.canUndo());
    }

    @Test
    public void canUndo_cannotUndo_false() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        assertFalse(versioned.canUndo());
    }

    @Test
    public void undo_success() {
        TaskBook initialState = new TaskBook();
        TaskBook newState = new TaskBook(initialState);
        newState.addTask(T1);
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.commit(newState);
        assertUndoEquals(initialState, versioned);
    }

    @Test
    public void undo_noActionsLeft_throwsInvalidActionsException() {
        TaskBook initialState = new TaskBook();
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        assertThrows(VersionedTaskBook.InvalidActionException.class, INVALID_UNDO_ACTION, versioned::undo);
    }

    @Test
    public void canRedo_canRedo_true() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        versioned.commit(ONE);
        assertDoesNotThrow(versioned::undo);
        assertTrue(versioned.canRedo());
    }

    @Test
    public void canRedo_cannotRedo_false() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        assertFalse(versioned.canRedo());
    }

    @Test
    public void redo_success() {
        TaskBook initialState = new TaskBook();
        TaskBook newState = new TaskBook(initialState);
        newState.addTask(T1);
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.commit(newState);
        assertDoesNotThrow(versioned::undo);
        assertRedoEquals(newState, versioned);
    }

    @Test
    public void redo_noActionsLeft_throwsInvalidActionsException() {
        TaskBook initialState = new TaskBook();
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        assertThrows(VersionedTaskBook.InvalidActionException.class, INVALID_REDO_ACTION, versioned::redo);
    }

    private void assertUndoEquals(TaskBook expected, VersionedTaskBook versioned) {
        assertDoesNotThrow(() -> {
            TaskBook actual = versioned.undo();
            assertEquals(expected, actual);
        });
    }

    private void assertRedoEquals(TaskBook expected, VersionedTaskBook versioned) {
        assertDoesNotThrow(() -> {
            TaskBook actual = versioned.redo();
            assertEquals(expected, actual);
        });
    }
}
