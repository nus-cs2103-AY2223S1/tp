package taskbook.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertEquals(THREE, versioned.undo());
        assertEquals(THREE, versioned.undo());
        assertEquals(FOUR, versioned.redo());
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
        assertEquals(FIVE, versioned.undo());
        assertEquals(SIX, versioned.redo());
    }

    @Test
    public void undo_success() {
        TaskBook initialState = new TaskBook();
        TaskBook newState = new TaskBook(initialState);
        newState.addTask(T1);
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.commit(newState);
        TaskBook undone = versioned.undo();
        assertEquals(undone, initialState);
    }

    @Test
    public void undo_pastFirstCommit_returnsFirst() {
        TaskBook initialState = new TaskBook();
        TaskBook newState = new TaskBook(initialState);
        newState.addTask(T1);
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.commit(newState);
        assertEquals(versioned.undo(), initialState);
        assertEquals(versioned.undo(), initialState);
        assertEquals(versioned.undo(), initialState);
    }

    @Test
    public void redo_success() {
        TaskBook initialState = new TaskBook();
        TaskBook newState = new TaskBook(initialState);
        newState.addTask(T1);
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.commit(newState);
        TaskBook undone = versioned.undo();
        TaskBook redone = versioned.redo();
        assertNotEquals(undone, redone);
        assertEquals(newState, redone);
    }

    @Test
    public void redo_pastLatCommit_returnsLastCommit() {
        TaskBook initialState = new TaskBook();
        TaskBook newState = new TaskBook(initialState);
        newState.addTask(T1);
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.commit(newState);
        versioned.undo();
        assertEquals(newState, versioned.redo());
        assertEquals(newState, versioned.redo());
        assertEquals(newState, versioned.redo());
    }
}
