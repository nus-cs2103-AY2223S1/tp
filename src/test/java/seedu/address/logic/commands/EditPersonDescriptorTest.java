package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.PROFESSOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TEACHING_ASSISTANT_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODES_SET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_HOUR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        //student fields
        EditPersonDescriptor studentDescriptorWithSameValues = new EditPersonDescriptor(STUDENT_AMY);
        assertTrue(STUDENT_AMY.equals(studentDescriptorWithSameValues));
        //professor fields
        EditPersonDescriptor professorDescriptorWithSameValues = new EditPersonDescriptor(PROFESSOR_BOB);
        assertTrue(PROFESSOR_BOB.equals(professorDescriptorWithSameValues));
        //teaching assistant fields
        EditPersonDescriptor taDescriptorWithSameValues = new EditPersonDescriptor(TEACHING_ASSISTANT_CABE);
        assertTrue(TEACHING_ASSISTANT_CABE.equals(taDescriptorWithSameValues));

        // same object -> returns true
        assertTrue(STUDENT_AMY.equals(STUDENT_AMY));

        // null -> returns false
        assertFalse(STUDENT_AMY.equals(null));

        // different types -> returns false
        assertFalse(STUDENT_AMY.equals(5));

        // different values -> returns false
        assertFalse(STUDENT_AMY.equals(PROFESSOR_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        // different gender -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withGender(VALID_GENDER_BOB).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        // different Username -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withUsername(VALID_USERNAME).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        //different year -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withYear("2").build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        //different module codes set set -> returns false
        editedAmy = new EditPersonDescriptorBuilder(STUDENT_AMY).withModuleCodeSet(VALID_MODULE_CODES_SET).build();
        assertFalse(STUDENT_AMY.equals(editedAmy));

        //different module code
        //professor
        EditPersonDescriptor editedBob =
                new EditPersonDescriptorBuilder(PROFESSOR_BOB).withModuleCode(VALID_MODULE_CODE_CABE).build();
        assertFalse(PROFESSOR_BOB.equals(editedBob));
        //teaching assistant
        EditPersonDescriptor editedCabe =
                new EditPersonDescriptorBuilder(TEACHING_ASSISTANT_CABE).withModuleCode(VALID_MODULE_CODE_BOB).build();
        assertFalse(TEACHING_ASSISTANT_CABE.equals(editedCabe));

        //different specialisation -> returns false
        editedBob = new EditPersonDescriptorBuilder(PROFESSOR_BOB).withSpecialisation(VALID_SPECIALISATION).build();
        assertFalse(PROFESSOR_BOB.equals(editedBob));

        //different office hours -> returns false
        editedBob = new EditPersonDescriptorBuilder(PROFESSOR_BOB).withOfficeHour(VALID_OFFICE_HOUR).build();
        assertFalse(PROFESSOR_BOB.equals(editedBob));

        //different rating -> returns false
        editedBob = new EditPersonDescriptorBuilder(PROFESSOR_BOB).withRating(VALID_RATING_CABE).build();
        assertFalse(PROFESSOR_BOB.equals(editedBob));
        editedCabe = new EditPersonDescriptorBuilder(TEACHING_ASSISTANT_CABE).withRating(VALID_RATING_BOB).build();
        assertFalse(TEACHING_ASSISTANT_CABE.equals(editedCabe));
    }
}
