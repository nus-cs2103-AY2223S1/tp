package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateTaskCommand.UpdateTaskDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building UpdateTaskDescriptor objects.
 */
public class UpdateTaskDescriptorBuilder {

    private UpdateTaskDescriptor descriptor;

    public UpdateTaskDescriptorBuilder() {
        descriptor = new UpdateTaskDescriptor();
    }

    public UpdateTaskDescriptorBuilder(UpdateTaskDescriptor descriptor) {
        this.descriptor = new UpdateTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateTaskDescriptor} with fields containing {@code task}'s details
     */
    public UpdateTaskDescriptorBuilder(Task task) {
        descriptor = new UpdateTaskDescriptor();
        descriptor.setTitle(task.getTitle());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setStatus(task.getStatus());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code UpdateTaskDescriptor} that we are building.
     */
    public UpdateTaskDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(title);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code UpdateTaskDescriptor} that we are building.
     */
    public UpdateTaskDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code UpdateTaskDescriptor} that we are building.
     */
    public UpdateTaskDescriptorBuilder withStatus(boolean status) {
        descriptor.setStatus(status);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code UpdateTaskDescriptor}
     * that we are building.
     */
    public UpdateTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public UpdateTaskDescriptor build() {
        return descriptor;
    }
}
