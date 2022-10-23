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
public class DeleteGroupMemberCommand extends Command {

    public static final String COMMAND_WORD = "deletemember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes member from the specified group"
            + " Parameters: " + PREFIX_GROUP + "GROUP " + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " g/Group 1 n/Bobby Chua";
    public static final String MESSAGE_INVALID_GROUP = "This group does not exist.";
    public static final String MESSAGE_INVALID_PERSON = "This person is not in the address book.";
    public static final String MESSAGE_PERSON_NOT_IN_GROUP = "%1$s is not in group %2$s";
    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "%1$s was deleted from group: %2$s";

    private final Name name;
    private final PersonGroup personGroup;

    /**
     * @param name of the person in the filtered person list to add to group
     * @param group of the person to be added to
     */
    public DeleteGroupMemberCommand(String group, String name) {
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
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        // check if group exist
        ObservableList<Group> groupList = model.getGroupWithName(new GroupName(this.personGroup.getGroupName()));
        Group groupToDeletePerson;
        try {
            groupToDeletePerson = groupList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_GROUP);
        }

        if (!groupToDeletePerson.contains(personToGroup)) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_IN_GROUP, this.name));
        }
        //change field
        ArrayList<PersonGroup> personGroupArrayList = personToGroup.getPersonGroups();
        personGroupArrayList.remove(this.personGroup);

        Person editedPerson = new Person(
                personToGroup.getName(), personToGroup.getPhone(), personToGroup.getEmail(),
                personToGroup.getAddress(), personToGroup.getTags(), personToGroup.getAssignments(),
                personGroupArrayList);

        //deletes person from the group
        Set<Person> groupMembers = new HashSet<>();
        groupMembers.addAll(groupToDeletePerson.getMembers());
        groupMembers.remove(editedPerson);
        Group editedGroup = new Group(groupToDeletePerson.getName(), groupMembers);


        model.setGroup(groupToDeletePerson, editedGroup);
        model.setPerson(personToGroup, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_MEMBER_SUCCESS, this.name, editedGroup));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGroupMemberCommand)) {
            return false;
        }

        // state check
        DeleteGroupMemberCommand e = (DeleteGroupMemberCommand) other;
        return name.equals(e.name)
                && personGroup.equals(e.personGroup);
    }
}
