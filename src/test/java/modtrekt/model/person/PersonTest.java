package modtrekt.model.person;

import static modtrekt.testutil.TypicalPersons.BOB;
import static modtrekt.testutil.TypicalPersons.TASK_1;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.CommandTestUtil;
import modtrekt.model.task.Task;
import modtrekt.testutil.TaskBuilder;

public class PersonTest {

    //    @Test
    //    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //        Task t = new TaskBuilder().build();
    //        assertThrows(UnsupportedOperationException.class, () -> person.remove(0));
    //   }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_1.isSameTask(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.isSameTask(null));

        // different name, all other attributes same -> returns false
        Task editedTask1 = new TaskBuilder(TASK_1).withDescription(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TASK_1.isSameTask(editedTask1));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_BOB + " ";
        Task editedBob = new TaskBuilder(BOB).withDescription(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        //        Task copy = new TaskBuilder(TASK_1).build();
        //        assertTrue(TASK_1.equals(copy));

        // same object -> returns true
        assertTrue(TASK_1.equals(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.equals(null));

        // different type -> returns false
        assertFalse(TASK_1.equals(5));

        // different person -> returns false
        assertFalse(TASK_1.equals(BOB));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(TASK_1).withDescription(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TASK_1.equals(editedAlice));
    }
}
