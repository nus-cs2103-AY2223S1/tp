package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.clinkedin.testutil.Assert.assertThrows;
import static seedu.clinkedin.testutil.TypicalPersons.ALICE;
import static seedu.clinkedin.testutil.TypicalPersons.BOB;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.testutil.PersonBuilder;



public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        // might want to change
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().clear());
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
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
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different status -> returns false
        editedAlice = new PersonBuilder(ALICE).withStatus(VALID_STATUS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different note -> returns false
        editedAlice = new PersonBuilder(ALICE).withNote(VALID_NOTE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void equalityTest() {
        Person person1 = new PersonBuilder(ALICE).build();
        Person person2 = new PersonBuilder(ALICE).build();
        Person person3 = new PersonBuilder(BOB).build();
        String person1Str = person1.getDetailsAsString();
        String person2Str = person2.getDetailsAsString();
        String person3Str = person3.getDetailsAsString();

        assertTrue(person1.equals(person1));
        assertTrue(person1.equals(person2));
        assertFalse(person1.equals(person3));
        assertFalse(person1.equals(null));
        assertFalse(person1.equals(5));
        assertEquals(person1Str, person2Str);
        assertFalse(person1Str.equals(person3Str));
        assertEquals(person1.toString(), person2.toString());

    }

    @Test
    public void getDetailsAsArrayTest() {
        Person person1 = new PersonBuilder(ALICE).build();
        Person person2 = new PersonBuilder(ALICE).build();
        Person person3 = new PersonBuilder(BOB).build();

        List<String[]> list1 = person1.getDetailsAsArray();
        List<String[]> list2 = person2.getDetailsAsArray();
        List<String[]> list3 = person3.getDetailsAsArray();

        assertTrue(list1.size() == list2.size());
        assertFalse(list1.size() == list3.size());

        for (int i = 0; i < list1.size(); i++) {
            int length1 = list1.get(i).length;
            int length2 = list2.get(i).length;
            if (length1 == 2 && length2 == 2) {
                assertEquals(list1.get(i)[1], list2.get(i)[1]);
            }
        }

        assertEquals(person1.getName().toString(), list1.get(0)[1]);
        assertEquals(person1.getPhone().toString(), list1.get(1)[1]);
        assertEquals(person1.getEmail().toString(), list1.get(2)[1]);
        assertEquals(person1.getAddress().toString(), list1.get(3)[1]);
    }

}
