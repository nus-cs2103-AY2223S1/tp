package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

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
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setTitle(task.getTitle());
        descriptor.setCompleted(task.getCompleted());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setAssignedContacts(task.getAssignedContacts());
    }

    /**
     * Sets the {@code Title} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the status of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withCompleted(boolean isCompleted) {
        descriptor.setCompleted(isCompleted);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(String deadline) throws ParseException {
        // descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withAssignedContacts(String... assignedContacts) {
        Set<Contact> contactSet = Stream.of(assignedContacts).map(Contact::new).collect(Collectors.toSet());
        descriptor.setAssignedContacts(contactSet);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
