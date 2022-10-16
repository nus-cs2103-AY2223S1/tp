package seedu.address.testutil;

import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentCommand.EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentCommand.EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setName(appointment.getName());
        descriptor.setDoctor(appointment.getDoctor());
        descriptor.setMedicalTest(appointment.getMedicalTest());
        descriptor.setSlot(appointment.getSlot());
    }

    /**
     * Sets the {@code Name} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code doctor} of the {@code EditAppointmentDescriptorBuilder} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDoctor(String doctor) {
        descriptor.setDoctor(new Doctor(doctor));
        return this;
    }

    /**
     * Sets the {@code medicalTest} of the {@code EditAppointmentDescriptorBuilder} that we are building.
     */
    public EditAppointmentDescriptorBuilder withMedicalTest(String medicalTest) {
        descriptor.setMedicalTest(new MedicalTest(medicalTest));
        return this;
    }

    /**
     * Sets the {@code slot} of the {@code EditAppointmentDescriptorBuilder} that we are building.
     */
    public EditAppointmentDescriptorBuilder withSlot(String slot) {
        descriptor.setSlot(new Slot(slot));
        return this;
    }

    public EditAppointmentCommand.EditAppointmentDescriptor build() {
        return descriptor;
    }
}
