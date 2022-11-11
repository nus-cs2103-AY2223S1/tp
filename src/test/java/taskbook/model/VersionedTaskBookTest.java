package taskbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    private static final Person P4 = new PersonBuilder().withName("Person 4").build();
    private static final Task T1 = new TodoBuilder().withDescription("Task 1").build();
    private static final Task T2 = new TodoBuilder().withDescription("Task 2").build();
    private static final Task T3 = new TodoBuilder().withDescription("Task 3").build();

    @Test
    public void isEquivalentTo() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        TaskBook initialState = new TaskBookBuilder().withPerson(P1).build();
        VersionedTaskBook differentVersioned = new VersionedTaskBook(initialState);

        assertTrue(versioned.isEquivalentTo(versioned));
        assertFalse(versioned.isEquivalentTo(differentVersioned));
    }

    @Test
    public void constructor_copy_same() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        VersionedTaskBook copied = new VersionedTaskBook(versioned);

        assertTrue(versioned.isEquivalentTo(copied));
    }

    @Test
    public void commit_differentState_success() {
        VersionedTaskBook initialState = new VersionedTaskBook();
        VersionedTaskBook newState = new VersionedTaskBook(initialState);
        newState.addPerson(P2);
        newState.commit();

        assertFalse(newState.isEquivalentTo(initialState));
    }

    @Test
    public void commit_sameState_noChange() {
        VersionedTaskBook initialState = new VersionedTaskBook();
        VersionedTaskBook newState = new VersionedTaskBook(initialState);
        // Commit with no changes should not add to history.
        newState.commit();

        assertTrue(newState.isEquivalentTo(initialState));
    }

    @Test
    public void commit_commitPastEvenCapacity_pruneHistory() {
        TaskBook initialState = new TaskBook();
        VersionedTaskBook versioned = new VersionedTaskBook(2, initialState);
        versioned.addPerson(P1);
        versioned.commit();
        versioned.addPerson(P2);
        versioned.commit();
        versioned.addPerson(P3);
        versioned.commit();
        versioned.addPerson(P4);
        versioned.commit();

        TaskBook halfwayState = new TaskBook(initialState);
        halfwayState.addPerson(P1);
        halfwayState.addPerson(P2);
        halfwayState.addPerson(P3);
        VersionedTaskBook expected = new VersionedTaskBook(halfwayState);
        expected.addPerson(P4);
        expected.commit();

        // Should be equivalent to another VersionedTaskBook that only has 2 commits in it.
        // Note that there is already a commit in the VersionedTaskBook on instantiation.
        assertTrue(expected.isEquivalentTo(versioned));
    }

    @Test
    public void commit_commitPastOddCapacity_pruneHistory() {
        TaskBook initialState = new TaskBook();
        VersionedTaskBook versioned = new VersionedTaskBook(3, initialState);
        versioned.addPerson(P1);
        versioned.commit();
        versioned.addPerson(P2);
        versioned.commit();
        versioned.addPerson(P3);
        versioned.commit();
        versioned.addTask(T1);
        versioned.commit();
        versioned.addTask(T2);
        versioned.commit();
        versioned.addTask(T3);
        versioned.commit();

        TaskBook halfwayState = new TaskBook(initialState);
        halfwayState.addPerson(P1);
        halfwayState.addPerson(P2);
        halfwayState.addPerson(P3);
        halfwayState.addTask(T1);
        VersionedTaskBook expected = new VersionedTaskBook(halfwayState);
        expected.addTask(T2);
        expected.commit();
        expected.addTask(T3);
        expected.commit();

        // Should be equivalent to another VersionedTaskBook that only has 3 commits in it.
        // Note that there is already a commit in the VersionedTaskBook on instantiation.
        assertTrue(expected.isEquivalentTo(versioned));
    }

    @Test
    public void commit_commitAfterUndo_cannotRedo() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        versioned.addTask(T1);
        versioned.commit();
        versioned.undo();
        assertTrue(versioned.canRedo());
        versioned.addTask(T1);
        versioned.commit();
        // Commit blocks redo because future history is removed.
        assertFalse(versioned.canRedo());
    }

    @Test
    public void canUndo_canUndo_true() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        versioned.addTask(T1);
        versioned.commit();
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
        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.addTask(T1);
        versioned.commit();
        versioned.undo();

        assertEquals(initialState, versioned);
    }

    @Test
    public void canRedo_canRedo_true() {
        VersionedTaskBook versioned = new VersionedTaskBook();
        versioned.addTask(T3);
        versioned.commit();
        versioned.undo();
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

        TaskBook expected = new TaskBook(initialState);
        expected.addTask(T1);

        VersionedTaskBook versioned = new VersionedTaskBook(initialState);
        versioned.addTask(T1);
        versioned.commit();
        versioned.undo();
        versioned.redo();

        assertEquals(expected, versioned);
    }

    @Test
    public void undoRedoCommit() {
        TaskBook initial = new TaskBook();

        TaskBook expected = new TaskBook(initial);
        expected.addPerson(P2);

        VersionedTaskBook versioned = new VersionedTaskBook(initial);
        versioned.addPerson(P1);
        versioned.commit();
        versioned.undo();
        versioned.addPerson(P2);
        versioned.commit();
        versioned.undo();
        versioned.redo();

        assertEquals(expected, versioned);

        versioned.undo();
        assertEquals(initial, versioned);
    }
}
