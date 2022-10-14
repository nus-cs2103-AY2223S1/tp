package seedu.address.model.task;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Task}'s {@code Title} matches any of the keywords given.
 */
public class AssignedToContactsPredicate implements Predicate<Task> {
    private final Set<Contact> contacts = new HashSet<>();

    public AssignedToContactsPredicate(Collection<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    public AssignedToContactsPredicate(Model model, Set<Index> personIndexes) throws CommandException {
        List<Person> lastShownPersonsList = model.getFilteredPersonList();

        for (Index personIndex : personIndexes) {
            if (personIndex.getZeroBased() >= lastShownPersonsList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Contact c =
                    new Contact(lastShownPersonsList.get(personIndex.getZeroBased()).getName().fullName);
            this.contacts.add(c);
        }
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
    public String getContactNames() {
        return contacts.stream().map(c -> c.contactName).collect(Collectors.joining(", "));
    }

    @Override
    public boolean test(Task task) {
        return task.getAssignedContacts().containsAll(contacts);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignedToContactsPredicate // instanceof handles nulls
                && contacts.equals(((AssignedToContactsPredicate) other).contacts)); // state check
    }
}
