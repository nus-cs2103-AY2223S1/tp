package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code task}'s details
     */
    public EditPersonDescriptorBuilder(Task task) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(task.getName());
        descriptor.setModule(task.getModule());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModule(String module) {
        descriptor.setModule(new Module(module));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
