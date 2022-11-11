package swift.testutil;

import swift.logic.commands.EditTaskCommand.EditTaskDescriptor;
import swift.model.task.Task;
import swift.model.task.TaskName;

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
        descriptor.setTaskName(task.getName());
        descriptor.setDescription(task.getDescription().orElse(null));
        descriptor.setDeadline(task.getDeadline().orElse(null));
        descriptor.setIsDone(task.isDone());
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setTaskName(new TaskName(name));
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withIsDone(boolean isDone) {
        descriptor.setIsDone(isDone);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
