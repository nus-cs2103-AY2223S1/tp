package seedu.modquik.testutil;

import seedu.modquik.logic.commands.consultation.EditConsultationCommand.EditConsultDescriptor;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.consultation.Consultation;
import seedu.modquik.model.consultation.ConsultationDescription;
import seedu.modquik.model.consultation.ConsultationName;
import seedu.modquik.model.datetime.DatetimeRange;

/**
 * A utility class to help with building EditConsultationDescriptor objects.
 */
public class EditConsultationDescriptorBuilder {
    private EditConsultDescriptor descriptor;

    public EditConsultationDescriptorBuilder() {
        descriptor = new EditConsultDescriptor();
    }

    public EditConsultationDescriptorBuilder(EditConsultDescriptor descriptor) {
        this.descriptor = new EditConsultDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditConsultationDescriptor} with fields containing {@code Consultation}'s details
     */
    public EditConsultationDescriptorBuilder(Consultation consultation) {
        descriptor = new EditConsultDescriptor();
        descriptor.setName(consultation.getName());
        descriptor.setModule(consultation.getModule());
        descriptor.setVenue(consultation.getVenue());
        descriptor.setTimeSlot(consultation.getTimeslot());
        descriptor.setDescription(consultation.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditConsultationDescriptor} that we are building.
     */
    public EditConsultationDescriptorBuilder withName(String name) {
        descriptor.setName(new ConsultationName(name));
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditConsultationDescriptor} that we are building.
     */
    public EditConsultationDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModule(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code EditConsultationDescriptor} that we are building.
     */
    public EditConsultationDescriptorBuilder withVenue(String venue) {
        descriptor.setVenue(new Venue(venue));
        return this;
    }

    /**
     * Sets the {@code DatetimeRange} of the {@code EditConsultationDescriptor} that we are building.
     */
    public EditConsultationDescriptorBuilder withTimeSlot(String startTime, String endTime) {
        descriptor.setTimeSlot(DatetimeRange.fromFormattedString(startTime, endTime));
        return this;
    }

    /**
     * Sets the {@code ConsultationDescription} of the {@code EditConsultationDescriptor} that we are building.
     */
    public EditConsultationDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new ConsultationDescription(description));
        return this;
    }

    public EditConsultDescriptor build() {
        return descriptor;
    }
}
