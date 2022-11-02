package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FLOOR_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOSPITAL_WING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_PARACETAMOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_XANAX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_NUMBER_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getMedications().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withNextOfKin(VALID_NEXT_OF_KIN_BOB)
                .withPatientType(PatientType.PatientTypes.parsePatientType(VALID_PATIENT_TYPE_BOB))
                .withMedication(VALID_MEDICATION_PARACETAMOL).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

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

        // different nok -> returns false
        editedAlice = new PersonBuilder(ALICE).withNextOfKin(VALID_NEXT_OF_KIN_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different type -> returns false
        editedAlice = new PersonBuilder(ALICE)
                .withPatientType(PatientType.PatientTypes.parsePatientType(VALID_PATIENT_TYPE_AMY))
                .withHospitalWing(VALID_HOSPITAL_WING_AMY).withFloorNumber(Integer.valueOf(VALID_FLOOR_NUMBER_AMY))
                .withWardNumber(VALID_WARD_NUMBER_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withMedication(VALID_MEDICATION_XANAX).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
