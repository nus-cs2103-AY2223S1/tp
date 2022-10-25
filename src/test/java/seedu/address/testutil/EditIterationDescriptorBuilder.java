package seedu.address.testutil;

import java.nio.file.Path;
import java.time.LocalDate;

import seedu.address.logic.commands.iteration.EditIterationCommand.EditIterationDescriptor;
import seedu.address.model.iteration.Date;
import seedu.address.model.iteration.Feedback;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.IterationDescription;

/**
 * A utility class to help with building EditIterationDescriptor objects.
 */
public class EditIterationDescriptorBuilder {

    private EditIterationDescriptor descriptor;

    public EditIterationDescriptorBuilder() {
        descriptor = new EditIterationDescriptor();
    }

    /**
     * Returns an {@code EditIterationDescriptor} with fields containing {@code iteration}'s details
     */
    public EditIterationDescriptorBuilder(Iteration iteration) {
        descriptor = new EditIterationDescriptor();
        descriptor.setDate(iteration.getDate());
        descriptor.setDescription(iteration.getDescription());
        descriptor.setFeedback(iteration.getFeedback());
        descriptor.setImagePath(iteration.getImagePath());
    }

    /**
     * Sets the {@code Date} of the {@code EditIterationDescriptor} that we are building.
     */
    public EditIterationDescriptorBuilder withDate(LocalDate date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditIterationDescriptor} that we are building.
     */
    public EditIterationDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new IterationDescription(description));
        return this;
    }

    /**
     * Sets the {@code Path} of the {@code EditIterationDescriptor} that we are building.
     */
    public EditIterationDescriptorBuilder withPath(Path imagePath) {
        descriptor.setImagePath(imagePath);
        return this;
    }

    /**
     * Sets the {@code Feedback} of the {@code EditIterationDescriptor} that we are building.
     */
    public EditIterationDescriptorBuilder withFeedback(String feedback) {
        descriptor.setFeedback(new Feedback(feedback));
        return this;
    }

    public EditIterationDescriptor build() {
        return descriptor;
    }
}
