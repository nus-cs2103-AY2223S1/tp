package seedu.address.model.person.nextofkin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN1;
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN2;
import static seedu.address.testutil.TypicalStudents.STUDENT1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.NextOfKinBuilder;

public class NextOfKinTest {

    @Test
    public void equals() {
        // same values -> returns true
        NextOfKin nextOfKin1Copy = new NextOfKinBuilder(NEXTOFKIN1).build();
        assertTrue(NEXTOFKIN1.equals(nextOfKin1Copy));

        // same object -> returns true
        assertTrue(NEXTOFKIN1.equals(NEXTOFKIN1));

        // null -> returns false
        assertFalse(NEXTOFKIN1.equals(null));

        // different type -> returns false
        assertFalse(NEXTOFKIN1.equals(5));

        // different next of kin -> returns false
        assertFalse(NEXTOFKIN1.equals(NEXTOFKIN2));

        // different name -> returns false
        NextOfKin editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withName(VALID_NAME_BOB).build();
        assertFalse(NEXTOFKIN1.equals(editedNextOfKin1));

        // different phone -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withPhone(VALID_PHONE_BOB).build();
        assertFalse(NEXTOFKIN1.equals(editedNextOfKin1));

        // different email -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(NEXTOFKIN1.equals(editedNextOfKin1));

        // different address -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(NEXTOFKIN1.equals(editedNextOfKin1));

        // different tags -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(NEXTOFKIN1.equals(editedNextOfKin1));

        // different relationship -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withRelationship("Guardian").build();
        assertFalse(NEXTOFKIN1.equals(editedNextOfKin1));
    }

    @Test
    public void toString_success() {
        String str = "Alice Pauline" + "; Phone: " + "94351253" + "; Email: " + "alice@example.com"
                + "; Address: " + "123, Jurong West Ave 6, #08-111"
                + "; Tags: " + "[friends]"
                + "; Relationship: " + "Father";
        assertEquals(str, NEXTOFKIN1.toString());
    }

    @Test
    public void isSamePerson() {
        // same values -> returns true
        NextOfKin nextOfKin1Copy = new NextOfKinBuilder(NEXTOFKIN1).build();
        assertTrue(NEXTOFKIN1.isSamePerson(nextOfKin1Copy));

        // same object -> returns true
        assertTrue(NEXTOFKIN1.isSamePerson(NEXTOFKIN1));

        // null -> returns false
        assertFalse(NEXTOFKIN1.isSamePerson(null));

        // different type -> returns false
        assertFalse(NEXTOFKIN1.isSamePerson(STUDENT1));

        // different next of kin -> returns false
        assertFalse(NEXTOFKIN1.isSamePerson(NEXTOFKIN2));

        // different name -> returns false
        NextOfKin editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withName(VALID_NAME_BOB).build();
        assertFalse(NEXTOFKIN1.isSamePerson(editedNextOfKin1));

        // different phone -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withPhone(VALID_PHONE_BOB).build();
        assertFalse(NEXTOFKIN1.isSamePerson(editedNextOfKin1));

        // different email -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(NEXTOFKIN1.isSamePerson(editedNextOfKin1));

        // different address -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(NEXTOFKIN1.isSamePerson(editedNextOfKin1));

        // different relationship -> returns false
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withRelationship("Guardian").build();
        assertFalse(NEXTOFKIN1.isSamePerson(editedNextOfKin1));

        // different tags -> returns true
        editedNextOfKin1 = new NextOfKinBuilder(NEXTOFKIN1).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(NEXTOFKIN1.isSamePerson(editedNextOfKin1));
    }


}
