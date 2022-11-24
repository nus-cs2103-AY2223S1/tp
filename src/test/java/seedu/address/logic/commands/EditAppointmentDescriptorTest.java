package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REASON_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NOSE;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.person.Appointment;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(DESC_APPT_AMY);
        assertTrue(DESC_APPT_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPT_AMY.equals(DESC_APPT_AMY));

        // null -> returns false
        assertFalse(DESC_APPT_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPT_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPT_AMY.equals(DESC_APPT_BOB));

        // different reason -> returns false
        EditAppointmentDescriptor editedAmyAppt = new EditAppointmentDescriptorBuilder(DESC_APPT_AMY)
                .withReason(VALID_REASON_BOB).build();
        assertFalse(DESC_APPT_AMY.equals(editedAmyAppt));

        // different dateTime -> returns false
        editedAmyAppt = new EditAppointmentDescriptorBuilder(DESC_APPT_AMY)
                .withDateTime(LocalDateTime.parse(VALID_DATE_BOB, Appointment.DATE_FORMATTER)).build();
        assertFalse(DESC_APPT_AMY.equals(editedAmyAppt));

        // different timePeriod -> returns false
        editedAmyAppt = new EditAppointmentDescriptorBuilder(DESC_APPT_AMY).withTimePeriod(List.of(0, 0, 0)).build();
        assertFalse(DESC_APPT_AMY.equals(editedAmyAppt));

        // different tags -> returns false
        editedAmyAppt = new EditAppointmentDescriptorBuilder(DESC_APPT_AMY).withTags(VALID_TAG_NOSE).build();
        assertFalse(DESC_APPT_AMY.equals(editedAmyAppt));
    }
}
