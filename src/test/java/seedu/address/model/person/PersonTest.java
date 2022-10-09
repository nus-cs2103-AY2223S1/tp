package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BETA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name and module, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different isDone, all other attributes same -> returns true
        editedAlice = new PersonBuilder(ALICE).withIsDone(!ALICE.isDone()).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_TASK_BETA).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different module, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withModule(VALID_MODULE_BETA).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BETA).withName(VALID_NAME_TASK_BETA.toLowerCase()).build();
        assertFalse(BETA.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_TASK_BETA + " ";
        editedBob = new PersonBuilder(BETA).withName(nameWithTrailingSpaces).build();
        assertFalse(BETA.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BETA));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_TASK_BETA).build();
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
}
