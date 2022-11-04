package seedu.address.testutil;

import seedu.address.logic.commands.consultation.EditConsultationCommand.EditConsultDescriptor;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.commons.Venue;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.datetime.DatetimeRange;

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
    public EditConsultationDescriptorBuilder(Consultation Consultation) {
        descriptor = new EditConsultDescriptor();
        descriptor.setName(Consultation.getName());
        descriptor.setModule(Consultation.getModule());
        descriptor.setVenue(Consultation.getVenue());
        descriptor.setTimeSlot(Consultation.getTimeslot());
        descriptor.setDescription(Consultation.getDescription());
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
