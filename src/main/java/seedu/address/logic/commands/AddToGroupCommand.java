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
 * Adds an existing person to a group.
 */
public class AddToGroupCommand extends Command {

    public static final String COMMAND_WORD = "addtogroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the person identified "
            + "by the index number used in the displayed person list to the identified group. "
            + "Group names should contain alphanumeric characters and should not contain spaces.\n"
            + "Parameters: INDEX (must be a positive integer) GROUPNAME\n"
            + "Example: " + COMMAND_WORD + " 1 friends";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book/group.";
    public static final String MESSAGE_GROUP_PERSON_SUCCESS = "Grouped Person: %1$s";
    public static final String MESSAGE_CREATE_GROUP_FAILED = "Sorry, it seems like the group creation failed, try "
            + "again.";

    private final Index index;
    private final Group groupToAdd;

    /**
     * @param index      of the person in the filtered person list to add to group
     * @param groupToAdd group to add the person to
     */
    public AddToGroupCommand(Index index, Group groupToAdd) {
        requireNonNull(index);
        this.index = index;
        this.groupToAdd = groupToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGroup = lastShownList.get(index.getZeroBased());
        Person groupedPerson = createGroupedPerson(personToGroup, groupToAdd);

        if (!personToGroup.isSamePerson(groupedPerson) && model.hasPerson(groupedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.setPerson(personToGroup, groupedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        UndoCommand.saveBeforeMod(this, pastAddressBook, model.getAddressBook());

        return new CommandResult(String.format(MESSAGE_GROUP_PERSON_SUCCESS, groupedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the group {@code groupToAdd}
     * added to {@code personToGroup}.
     */
    private Person createGroupedPerson(Person personToGroup, Group groupToAdd) throws CommandException {
        assert personToGroup != null;

        Occupation occupation = personToGroup.getOccupation();
        Name name = personToGroup.getName();
        Phone phone = personToGroup.getPhone();
        Email email = personToGroup.getEmail();
        Address address = personToGroup.getAddress();
        Set<Tag> tags = personToGroup.getTags();
        Social social = personToGroup.getSocial();
        Tutorial tutorial = personToGroup.getTutorial();

        Set<Group> previousGroups = new HashSet<>(personToGroup.getGroups());
        Set<Group> updatedGroups;

        if (previousGroups.contains(groupToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } else {
            previousGroups.add(groupToAdd);
            updatedGroups = previousGroups;
        }

        return new Person(occupation, name, phone, email, tutorial, address, tags, social, updatedGroups);
    }

    /**
     * Returns a {@code Person} with the group {@code groupToAdd} added to {@code personToGroup}.
     *
     * @param personToGroup     person to be added
     * @param groupToAdd        group to add the person into
     * @return                  groupedPerson
     * @throws CommandException if groupedPerson creation fails
     */
    public Person getGroupedPerson(Person personToGroup, Group groupToAdd) throws CommandException {
        Person groupedPerson;
        try {
            groupedPerson = createGroupedPerson(personToGroup, groupToAdd);
        } catch (CommandException e) {
            throw new CommandException(MESSAGE_CREATE_GROUP_FAILED);
        }
        return groupedPerson;
    }

}
