package seedu.address.testutil;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.task.Description;
import seedu.address.logic.task.Priority;
import seedu.address.logic.task.Task;
import seedu.address.logic.task.TaskCategory;
import seedu.address.logic.task.TaskDeadline;
import seedu.address.logic.task.TaskName;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private final EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@codetTask}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setName(task.getName());
        descriptor.setCategory(task.getCategory());
        descriptor.setDescription(task.getDescription());
        descriptor.setPriority(task.getPriority());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setPerson(task.getPerson());
        descriptor.setDone(task.isDone());
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setName(new TaskName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withCategory(TaskCategory category) {
        descriptor.setCategory(category);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withPriority(Priority priority) {
        descriptor.setPriority(priority);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(TaskDeadline deadline) {
        descriptor.setDeadline(deadline);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withPerson(Person person) {
        descriptor.setPerson(person);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withIsDone(Boolean isDone) {
        descriptor.setDone(isDone);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}

