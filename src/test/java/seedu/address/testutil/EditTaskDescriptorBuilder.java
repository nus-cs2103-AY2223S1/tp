package seedu.address.testutil;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;

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
        descriptor.setPersonEmail(task.getEmail());
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
     * Sets the {@code Category} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withCategory(TaskCategory category) {
        descriptor.setCategory(category);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(Description description) {
        descriptor.setDescription(description);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withPriority(Priority priority) {
        descriptor.setPriority(priority);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(TaskDeadline deadline) {
        descriptor.setDeadline(deadline);
        return this;
    }

    /**
     * Sets the person's {@code Email} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withPersonEmail(Email email) {
        descriptor.setPersonEmail(email);
        return this;
    }

    /**
     * Sets the {@code Done} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withIsDone(Boolean isDone) {
        descriptor.setDone(isDone);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}

