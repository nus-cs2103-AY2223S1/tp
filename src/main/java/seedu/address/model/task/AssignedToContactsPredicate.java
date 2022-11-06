package seedu.address.model.task;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.teammate.Teammate;

/**
 * Tests that a {@code Task}'s assigned contacts contains a specified set of contacts.
 */
public class AssignedToContactsPredicate implements Predicate<Task> {
    private final Set<Contact> contacts = new HashSet<>();

    /**
     * Constructs an AssignedToContactsPredicate.
     * @param contacts the set of contacts to search for
     */
    public AssignedToContactsPredicate(Collection<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Constructs an AssignedToContactsPredicate.
     * @param contact the contact to search for
     */
    public AssignedToContactsPredicate(Contact contact) {
        this.contacts.add(contact);
    }
    /**
     * Constructs an AssignedToContactsPredicate.
     * @param model the model to use when looking for contacts
     * @param teammateIndexes the indices which correspond to contacts in the model's address book
     */
    public AssignedToContactsPredicate(Model model, Set<Index> teammateIndexes) throws CommandException {
        List<Teammate> lastShownTeammatesList = model.getFilteredTeammateList();

        for (Index teammateIndex : teammateIndexes) {
            if (teammateIndex.getZeroBased() >= lastShownTeammatesList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
            }
            Contact c =
                    new Contact(lastShownTeammatesList.get(teammateIndex.getZeroBased()).getName().fullName);
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
