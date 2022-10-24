package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating tasks with sample data.
 */
public class CommandUtil {

    /**
     * Encapsulates a person that has been deleted
     */
    public static class DeletedPerson extends Person {
        private static final Name name = new Name("This person has been deleted");
        private static final Address address = new Address("Deleted address");
        private static final Email email = new Email("dummyEmail@dummy.com");

        private static final Phone phone = new Phone("00000000");
        private static final Set<Tag> tags = new HashSet<>();
        private static final List<Task> tasks = new ArrayList<>();

        public DeletedPerson() {
            super(name, phone, email, address, tags, tasks);
        }
    }

    /**
     * Encapsulates a person that does not exist
     */
    public static class NotFoundPerson extends Person {
        private static final Name name = new Name("Person not Found");
        private static final Address address = new Address("Unknown");
        private static final Phone phone = new Phone("00000000");
        private static final Set<Tag> tags = new HashSet<>();
        private static final List<Task> tasks = new ArrayList<>();

        public NotFoundPerson(Email email) {
            super(name, phone, email, address, tags, tasks);
        }
    }
}
