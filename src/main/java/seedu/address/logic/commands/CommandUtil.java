package seedu.address.logic.commands;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandUtil {

    private static class deletedPerson extends Person {
        private static final Name name = new Name("This person has been deleted");
        private static final Address address = new Address("Deleted address");
        private static final Email email = new Email("dummyEmail@dummy.com");

        private static final Phone phone = new Phone("00000000");
        private static final Set<Tag> tags = new HashSet<>();
        private static final List<Task> tasks = new ArrayList<>();
        public deletedPerson() {
            super(name, phone, email, address, tags, tasks);
        }
    }

    public static class notFoundPerson extends Person {
        private static final Name name = new Name("Person not Found");
        private static final Address address = new Address("Unknown");
        private static final Phone phone = new Phone("00000000");
        private static final Set<Tag> tags = new HashSet<>();
        private static final List<Task> tasks = new ArrayList<>();
        public notFoundPerson(Email email) {
            super(name, phone, email, address, tags, tasks);
        }
    }
}
