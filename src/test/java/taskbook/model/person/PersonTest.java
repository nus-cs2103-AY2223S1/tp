package taskbook.model.person;

import static taskbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static taskbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.testutil.Assert;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.TypicalTaskBook;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        Assertions.assertTrue(TypicalTaskBook.ALICE.isSamePerson(TypicalTaskBook.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalTaskBook.ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(TypicalTaskBook.ALICE)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalTaskBook.ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalTaskBook.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalTaskBook.ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(TypicalTaskBook.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertFalse(TypicalTaskBook.BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalTaskBook.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(TypicalTaskBook.BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(TypicalTaskBook.ALICE).build();
        Assertions.assertTrue(TypicalTaskBook.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalTaskBook.ALICE.equals(TypicalTaskBook.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(5));

        // different person -> returns false
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(TypicalTaskBook.BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(TypicalTaskBook.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalTaskBook.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(TypicalTaskBook.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(TypicalTaskBook.ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalTaskBook.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalTaskBook.ALICE.equals(editedAlice));
    }
}
