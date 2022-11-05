package seedu.modquik.logic.commands.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.logic.commands.CommandTestUtil.DESC_CONSULTATION1;
import static seedu.modquik.logic.commands.CommandTestUtil.DESC_CONSULTATION2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CONSULT2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_MODULE_CONSULT2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_CONSULT2;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TIMESLOT_CONSULT2_END;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TIMESLOT_CONSULT2_START;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_VENUE_CONSULT2;

import org.junit.jupiter.api.Test;

import seedu.modquik.testutil.EditConsultationDescriptorBuilder;

public class EditConsultDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditConsultationCommand.EditConsultDescriptor descriptorWithSameValues =
                new EditConsultationCommand.EditConsultDescriptor(DESC_CONSULTATION1);

        assertTrue(DESC_CONSULTATION1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CONSULTATION1.equals(DESC_CONSULTATION1));

        // null -> returns false
        assertFalse(DESC_CONSULTATION1.equals(null));

        // different types -> returns false
        assertFalse(DESC_CONSULTATION1.equals(5));

        // different values -> returns false
        assertFalse(DESC_CONSULTATION1.equals(DESC_CONSULTATION2));

        // different name -> returns false
        EditConsultationCommand.EditConsultDescriptor editedConsultation1 =
                new EditConsultationDescriptorBuilder(DESC_CONSULTATION1).withName(VALID_NAME_CONSULT2).build();

        assertFalse(DESC_CONSULTATION1.equals(editedConsultation1));

        // different module -> returns false
        editedConsultation1 = new EditConsultationDescriptorBuilder(DESC_CONSULTATION1)
                .withModuleCode(VALID_MODULE_CONSULT2).build();
        assertFalse(DESC_CONSULTATION1.equals(editedConsultation1));

        // different venue -> returns false
        editedConsultation1 = new EditConsultationDescriptorBuilder(DESC_CONSULTATION1)
                .withVenue(VALID_VENUE_CONSULT2).build();
        assertFalse(DESC_CONSULTATION1.equals(editedConsultation1));

        // different timeslot -> returns false
        editedConsultation1 = new EditConsultationDescriptorBuilder(DESC_CONSULTATION1)
                .withTimeSlot(VALID_TIMESLOT_CONSULT2_START, VALID_TIMESLOT_CONSULT2_END).build();
        assertFalse(DESC_CONSULTATION1.equals(editedConsultation1));

        // different description -> returns false
        editedConsultation1 = new EditConsultationDescriptorBuilder(DESC_CONSULTATION1)
                .withDescription(VALID_DESCRIPTION_CONSULT2).build();
        assertFalse(DESC_CONSULTATION1.equals(editedConsultation1));
    }
}
