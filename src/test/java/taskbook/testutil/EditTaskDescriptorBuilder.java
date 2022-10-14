package taskbook.testutil;

import taskbook.logic.parser.DateParser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Name;
import taskbook.model.task.Deadline;
import taskbook.model.task.Description;
import taskbook.model.task.EditTaskDescriptor;
import taskbook.model.task.Event;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building EditPersonDescriptor objects.
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
     * Returns an {@code EditTaskDescriptorBuilder} with fields containing {@code todo}'s details.
     */
    public EditTaskDescriptorBuilder(Todo todo) {
        descriptor = new EditTaskDescriptor();
        descriptor.setName(todo.getName());
        descriptor.setAssignment(todo.getAssignment());
        descriptor.setDescription(todo.getDescription());
        descriptor.setIsDone(todo.isDone());
    }

    /**
     * Returns an {@code EditTaskDescriptorBuilder} with fields containing {@code deadline}'s details.
     */
    public EditTaskDescriptorBuilder(Deadline deadline) {
        descriptor = new EditTaskDescriptor();
        descriptor.setName(deadline.getName());
        descriptor.setAssignment(deadline.getAssignment());
        descriptor.setDescription(deadline.getDescription());
        descriptor.setIsDone(deadline.isDone());
        descriptor.setDate(deadline.getDate());
    }

    /**
     * Returns an {@code EditTaskDescriptorBuilder} with fields containing {@code event}'s details.
     */
    public EditTaskDescriptorBuilder(Event event) {
        descriptor = new EditTaskDescriptor();
        descriptor.setName(event.getName());
        descriptor.setAssignment(event.getAssignment());
        descriptor.setDescription(event.getDescription());
        descriptor.setIsDone(event.isDone());
        descriptor.setDate(event.getDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Assignment} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withAssignment(Assignment assignment) {
        descriptor.setAssignment(assignment);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withIsDone(boolean isDone) {
        descriptor.setIsDone(isDone);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDate(String date) {
        try {
            descriptor.setDate(DateParser.parse(date));
        } catch (ParseException pe) {
            // Silently fail.
            return this;
        }
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
