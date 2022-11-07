package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.ArchivalStatus;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {
    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setDescription(task.getDescription());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setCompletionStatus(task.getCompletionStatus());
        descriptor.setArchivalStatus(task.getArchivalStatus());

        descriptor.setTags(task.getTags());
        descriptor.setId(task.getId());
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the completion status of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withCompletionStatus(Boolean completionStatus) {
        descriptor.setCompletionStatus(new CompletionStatus(completionStatus));
        return this;
    }

    /**
     * Sets the archival status of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withArchivalStatus(Boolean archivalStatus) {
        descriptor.setArchivalStatus(new ArchivalStatus(archivalStatus));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withId(int id) {
        descriptor.setId(new Id(id));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
