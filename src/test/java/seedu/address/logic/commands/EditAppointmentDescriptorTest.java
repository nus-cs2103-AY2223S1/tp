package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPT_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_23_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_WESTMALL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.EditAppointmentDescriptor;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(DESC_APPT_1);
        assertTrue(DESC_APPT_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPT_1.equals(DESC_APPT_1));

        // null -> returns false
        assertFalse(DESC_APPT_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPT_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPT_1.equals(DESC_APPT_2));

        // different datetime -> returns false
        EditAppointmentDescriptor editedAppt = new EditAppointmentDescriptorBuilder()
                .withDateTime(VALID_DATETIME_23_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build();
        assertFalse(DESC_APPT_1.equals(editedAppt));

        // different location -> returns false
        editedAppt = new EditAppointmentDescriptorBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_WESTMALL).build();
        assertFalse(DESC_APPT_1.equals(editedAppt));
    }
}
