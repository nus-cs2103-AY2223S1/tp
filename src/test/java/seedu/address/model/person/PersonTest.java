package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILEPATH_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

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

        // different phone and email -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_AMY).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, same email, different attributes -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_AMY).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_AMY).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
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

        // different file path -> returns false
        editedAlice = new PersonBuilder(ALICE).withFilePath(VALID_FILEPATH_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void syncMeetingTimes() {

        //No meetings
        Person lazyGuy = new PersonBuilder().withMeetingTimes().build();
        Person.syncMeetingTimes(lazyGuy);
        assertEquals(lazyGuy, new PersonBuilder().withMeetingTimes().build());

        //Meeting yet to come
        Person futureGuy = new PersonBuilder().withMeetingTimes("19-11-2024-02:00").build();
        Person.syncMeetingTimes(futureGuy);
        assertEquals(futureGuy, new PersonBuilder().withMeetingTimes("19-11-2024-02:00").build());

        //Meetings already passed
        Person deadGuy = new PersonBuilder().withMeetingTimes("28-07-2020-15:00", "15-06-2022-17:00").build();
        Person.syncMeetingTimes(deadGuy);
        assertEquals(deadGuy, new PersonBuilder().withMeetingTimes().build());

        //Multiple meetings
        Person busyGuy = new PersonBuilder().withMeetingTimes("14-03-2023-16:00", "28-07-2020-15:00",
                "15-06-2022-17:00", "19-11-2024-02:00").build();
        Person.syncMeetingTimes(busyGuy);
        assertEquals(busyGuy, new PersonBuilder().withMeetingTimes("14-03-2023-16:00", "19-11-2024-02:00").build());
    }
}
