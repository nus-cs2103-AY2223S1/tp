package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;

/**
 * Adds a person to a group in the address book.
 */
public class AddGroupMemberCommand extends Command {

    public static final String COMMAND_WORD = "addmember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds member to a specified group. "
            + "Parameters: " + PREFIX_GROUP + "GROUP " + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " g/Group Alpha n/Alice Chee";
    public static final String MESSAGE_DUPLICATE_PERSON_IN_GROUP = "%1$s already exists in the group.";
    public static final String MESSAGE_INVALID_GROUP = "This group does not exist.";
    public static final String MESSAGE_INVALID_PERSON = "%1$s is not in the address book.";
    public static final String MESSAGE_ASSIGN_GROUP_SUCCESS = "%1$s was added to group: %2$s";

    private final Name name;
    private final PersonGroup personGroup;

    /**
     * @param name of the person in the filtered person list to add to group
     * @param group of the person to be added to
     */
    public AddGroupMemberCommand(String group, String name) {
        requireAllNonNull(name, group);
        this.name = new Name(name);
        this.personGroup = new PersonGroup(group);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Person> personList = model.getPersonWithName(this.name);
        Person personToGroup;

        // checks for the person
        try {
            personToGroup = personList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_PERSON, this.name));
        }

        // check if group exist
        ObservableList<Group> groupList = model.getGroupWithName(new GroupName(this.personGroup.getGroupName()));
        Group groupToAddPerson;
        try {
            groupToAddPerson = groupList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_GROUP, this.personGroup));
        }

        // check if person already in group
        if (groupToAddPerson.contains(personToGroup)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON_IN_GROUP, this.name, this.personGroup));
        }

        //change field
        ArrayList<PersonGroup> personGroupArrayList = personToGroup.getPersonGroups();
        personGroupArrayList.add(this.personGroup);

        Person editedPerson = new Person(
                personToGroup.getName(), personToGroup.getPhone(), personToGroup.getEmail(),
                personToGroup.getAddress(), personToGroup.getTags(), personToGroup.getAssignments(),
                personGroupArrayList);

        //add person to the group
        Set<Person> groupMembers = new HashSet<>();
        groupMembers.addAll(groupToAddPerson.getMembers());
        groupMembers.add(editedPerson);
        Group editedGroup = new Group(groupToAddPerson.getName(), groupMembers);

        model.setGroup(groupToAddPerson, editedGroup);
        model.setPerson(personToGroup, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ASSIGN_GROUP_SUCCESS, this.name, editedGroup));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGroupMemberCommand)) {
            return false;
        }

        // state check
        AddGroupMemberCommand e = (AddGroupMemberCommand) other;
        return name.equals(e.name)
                && personGroup.equals(e.personGroup);
    }
}
