package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tutorial;
import seedu.address.model.social.Social;
import seedu.address.model.tag.Tag;

/**
 * Removes a person from a group.
 */
public class UngroupCommand extends Command {

    public static final String COMMAND_WORD = "ungroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the person identified "
            + "by the index number used in the displayed person list from the identified group. "
            + "Group names should not contain spaces.\n"
            + "Parameters: INDEX (must be a positive integer) GROUPNAME\n"
            + "Example: " + COMMAND_WORD + " 1 friends";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_UNGROUP_PERSON_SUCCESS = "Ungrouped person: %1$s";
    public static final String MESSAGE_UNGROUP_PERSON_FAIL = "The person is not currently in this group.";

    private final Index index;
    private final Group groupToRemove;

    /**
     * @param index      of the person in the filtered person list to remove from group
     * @param groupToRemove group to remove the person from
     */
    public UngroupCommand(Index index, Group groupToRemove) {
        requireNonNull(index);
        this.index = index;
        this.groupToRemove = groupToRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUngroup = lastShownList.get(index.getZeroBased());
        Person removedPerson = createUngroupedPerson(personToUngroup, groupToRemove);

        if (!personToUngroup.isSamePerson(removedPerson) && model.hasPerson(removedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.setPerson(personToUngroup, removedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        UndoCommand.saveBeforeMod(this, pastAddressBook, model.getAddressBook());

        return new CommandResult(String.format(MESSAGE_UNGROUP_PERSON_SUCCESS, removedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the group {@code groupToRemove}
     * removed from {@code personToUngroup}.
     */
    private Person createUngroupedPerson(Person personToUngroup, Group groupToRemove) throws CommandException {
        assert personToUngroup != null;

        Occupation occupation = personToUngroup.getOccupation();
        Name name = personToUngroup.getName();
        Phone phone = personToUngroup.getPhone();
        Email email = personToUngroup.getEmail();
        Address address = personToUngroup.getAddress();
        Set<Tag> tags = personToUngroup.getTags();
        Social social = personToUngroup.getSocial();
        Tutorial tutorial = personToUngroup.getTutorial();

        Set<Group> previousGroups = new HashSet<>(personToUngroup.getGroups());
        Set<Group> updatedGroups;

        if (previousGroups.contains(groupToRemove)) {
            previousGroups.remove(groupToRemove);
            updatedGroups = previousGroups;
        } else {
            throw new CommandException(MESSAGE_UNGROUP_PERSON_FAIL);
        }

        return new Person(occupation, name, phone, email, tutorial, address, tags, social, updatedGroups);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UngroupCommand // instanceof handles nulls
                && index.equals(((UngroupCommand) other).index)
                && groupToRemove.equals(((UngroupCommand) other).groupToRemove));
    }

}
