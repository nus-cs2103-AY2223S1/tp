package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class JobTest {

    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(ALICE.getJob().isSameJob(ALICE.getJob()));

        // null -> returns false
        assertFalse(ALICE.getJob().isSameJob(null));

        // same job ID, different job title -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withTitle(VALID_JOB_TITLE_BOB).build();
        Job editedAliceJob = editedAlice.getJob();
        assertTrue(ALICE.getJob().isSameJob(editedAliceJob));

        // different job ID, same job title -> returns false
        editedAlice = new PersonBuilder(ALICE).withId(VALID_JOB_ID_BOB).build();
        editedAliceJob = editedAlice.getJob();
        assertFalse(ALICE.getJob().isSameJob(editedAliceJob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.getJob().equals(aliceCopy.getJob()));

        // same object -> returns true
        assertTrue(ALICE.getJob().equals(ALICE.getJob()));

        // null -> returns false
        assertFalse(ALICE.getJob().equals(null));

        // different type -> returns false
        assertFalse(ALICE.getJob().equals(5));

        // different job -> returns false
        assertFalse(ALICE.getJob().equals(BOB.getJob()));

        // different job ID -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_JOB_ID_BOB).build();
        assertFalse(ALICE.getJob().equals(editedAlice.getJob()));

        // different job title -> returns false
        editedAlice = new PersonBuilder(ALICE).withTitle(VALID_JOB_TITLE_BOB).build();
        assertFalse(ALICE.getJob().equals(editedAlice.getJob()));
    }
}
