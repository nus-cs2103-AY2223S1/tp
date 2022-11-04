package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BETA;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // same name and module, all other attributes different -> returns true
        Task editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // different isDone, all other attributes same -> returns true
        editedAlice = new PersonBuilder(ALICE).withIsDone(!ALICE.isDone()).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_TASK_BETA).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // different module, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withModule(VALID_MODULE_BETA).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Task editedBob = new PersonBuilder(BETA).withName(VALID_NAME_TASK_BETA.toLowerCase()).build();
        assertTrue(BETA.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_TASK_BETA + " ";
        editedBob = new PersonBuilder(BETA).withName(nameWithTrailingSpaces).build();
        assertFalse(BETA.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different task -> returns false
        assertFalse(ALICE.equals(BETA));

        // different name -> returns false
        Task editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_TASK_BETA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withModule(VALID_MODULE_BETA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HIGH_PRIORITY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different isDone -> returns false
        editedAlice = new PersonBuilder(ALICE).withIsDone(!ALICE.isDone()).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void compareTo() {
        // Identity
        assertEquals(ALICE.compareTo(ALICE), 0);

        // Comparison by Deadline
        assertTrue(ALICE.compareTo(ELLE) < 0);
        assertFalse(ELLE.compareTo(FIONA) >= 0);

        // Comparison by Module
        Task temp = new PersonBuilder().withDeadline("2022-09-09").build();
        Task temp2 = new PersonBuilder().withDeadline("2022-09-09").withModule("ACC1234").build();
        assertTrue(ELLE.compareTo(temp) > 0);
        assertTrue(ELLE.compareTo(temp2) > 0);
        assertTrue(temp2.compareTo(temp) < 0);

        // Comparison by Name
        Task temp3 = new PersonBuilder(FIONA).withName("Tutorial 1").build();
        Task temp4 = new PersonBuilder(FIONA).withName("Project Work").build();
        assertTrue(FIONA.compareTo(temp3) > 0);
        assertTrue(FIONA.compareTo(temp4) > 0);
        assertTrue(temp4.compareTo(temp3) < 0);
    }
}
