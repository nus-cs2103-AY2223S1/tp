package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;

/**
 * Removes a person from a group in TABS.
 */
public class DeleteGroupMemberCommand extends Command {

    public static final String COMMAND_WORD = "deletemember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a member from the specified group.\n"
            + "Parameters: " + PREFIX_GROUP + "GROUP " + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " g/Group 1 n/Bobby Chua";
    public static final String MESSAGE_INVALID_GROUP = "This group does not exist.";
    public static final String MESSAGE_INVALID_PERSON = "This person is not in TABS.";
    public static final String MESSAGE_PERSON_NOT_IN_GROUP = "%1$s is not in group %2$s.";
    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "%1$s was deleted from group: %2$s";

    private final Name name;
    private final PersonGroup personGroup;

    /**
     * @param name of the person in the filtered person list to delete from the group
     * @param group of the person to be deleted from
     */
    public DeleteGroupMemberCommand(PersonGroup group, Name name) {
        requireAllNonNull(name, group);
        this.name = name;
        this.personGroup = group;
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
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_IN_GROUP, this.name, this.personGroup));
        }

        //change field
        ArrayList<PersonGroup> personGroupArrayList = personToGroup.getPersonGroups();
        ArrayList<PersonGroup> personGroupArrayListCopy = new ArrayList<>(personGroupArrayList);

        Person originalPersonBeforeEdit = new Person(
            personToGroup.getName(), personToGroup.getPhone(), personToGroup.getEmail(),
            personToGroup.getAddress(), personToGroup.getTags(), personToGroup.getAssignments(),
                personGroupArrayList);

        personGroupArrayListCopy.remove(this.personGroup);

        HashMap<String, ArrayList<Assignment>> personAssignmentCopy = new HashMap<>(personToGroup.getAssignments());
        personAssignmentCopy.remove(this.personGroup.getGroupName());

        Person editedPerson = new Person(
                personToGroup.getName(), personToGroup.getPhone(), personToGroup.getEmail(),
                personToGroup.getAddress(), personToGroup.getTags(), personAssignmentCopy,
                personGroupArrayListCopy);

        for (PersonGroup pg : personGroupArrayListCopy) {
            Group currGroup = model.getGroupWithName(new GroupName(pg.getGroupName())).get(0);

            Set<Person> editedMembers = new HashSet<>(currGroup.getMembers());
            editedMembers.remove(originalPersonBeforeEdit);
            editedMembers.add(editedPerson);

            Group editedExistingGroups = new Group(currGroup.getName(), editedMembers);
            model.setGroup(currGroup, editedExistingGroups);
        }

        //deletes person from the group
        Set<Person> groupMembers = new HashSet<>(groupToDeletePerson.getMembers());

        groupMembers.remove(originalPersonBeforeEdit);

        Group editedGroup = new Group(groupToDeletePerson.getName(), groupMembers);

        model.setGroup(groupToDeletePerson, editedGroup);
        model.setPerson(personToGroup, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);

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
