package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_INVALID_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;

/**
 * Deletes a group identified using a string name from the address book.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletegroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by the GROUPNAME used.\n"
            + "Parameters: "
            + PREFIX_GROUP + "GROUPNAME "
            + "(must be one currently in Address Book)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "CS2103T Team Project";

    public static final String MESSAGE_SUCCESS = "Deleted group: %1$s";
    private static final String MESSAGE_UNKNOWN_GROUP = "The group specified cannot be found!";

    private final Group targetGroup;

    public DeleteGroupCommand(Group targetGroup) {
        this.targetGroup = targetGroup;
    }

    /** Executes DeleteGroupCommand */
    public CommandResult execute(Model model) throws CommandException {
        //todo remove assignments from members when group is deleted
        requireNonNull(model);

        if (!model.hasGroup(targetGroup)) {
            throw new CommandException(MESSAGE_UNKNOWN_GROUP);
        }

        //find out which group to delete from
        ObservableList<Group> groupList = model.getGroupWithName(targetGroup.getName());

        //the group specified cannot be found
        Group groupToCheck;
        try {
            groupToCheck = groupList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_GROUP);
        }

        Set<Person> groupMembers = groupToCheck.getMembers();

        //remove tags and assignments
        for (Person currentPerson : groupMembers) {
            Person editedPerson = new Person(
                    currentPerson.getName(), currentPerson.getPhone(), currentPerson.getEmail(),
                    currentPerson.getAddress(), currentPerson.getTags(), currentPerson.getAssignments(),
                    currentPerson.getPersonGroups());
            editedPerson.getPersonGroups().remove(new PersonGroup(groupToCheck.getName().toString()));
            editedPerson.getAssignments().remove((groupToCheck.getName().toString()));
            model.setPerson(currentPerson, editedPerson);
        }

        model.deleteGroup(groupToCheck);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupToCheck));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteGroupCommand
                && targetGroup.equals(((DeleteGroupCommand) other).targetGroup));
    }
}
