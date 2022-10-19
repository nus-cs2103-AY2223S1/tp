package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;


/**
 * Adds an assignment to all members in a group in the address book.
 * Skips over members who already have the assignment.
 */
public class AssignTaskAllCommand extends Command {

    public static final String COMMAND_WORD = "assigntaskall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign task to all users in a group."
            + "Members already with this task are skipped over."
            + "Parameters: " + PREFIX_GROUP + "GROUP " + PREFIX_TASK + "TASK\n"
            + "Example: " + COMMAND_WORD + " g/Group Alpha task/Coursework 0";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Group: %2$s Task: %3$s";
    public static final String MESSAGE_INVALID_GROUP = "This group is not in the address book.";
    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "New task added for the following persons.";

    private final String group;
    private final Assignment task;

    public AssignTaskAllCommand(String group, Assignment task) {
        requireAllNonNull(group, task);
        this.group = group;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // for now, doesn't check if person already has task
        ObservableList<Group> groupList = model.getGroupWithName(new GroupName(this.group));
        Group groupToAssign;
        try {
            groupToAssign = groupList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_GROUP);
        }

        Set<Person> members = groupToAssign.getMembers();

        for (Person personToAssignTask : members) {
            HashMap<String, ArrayList<Assignment>> assignments = personToAssignTask.getAssignments();

            ArrayList<Assignment> listOfAssignment;
            if (assignments.containsKey(group)) {
                listOfAssignment = assignments.get(group);
            } else {
                listOfAssignment = new ArrayList<>();
            }

            listOfAssignment.add(task);
            assignments.put(group, listOfAssignment);

            Person editedPerson = new Person(
                    personToAssignTask.getName(), personToAssignTask.getPhone(), personToAssignTask.getEmail(),
                    personToAssignTask.getAddress(), personToAssignTask.getTags(), assignments,
                    personToAssignTask.getPersonGroups());

            model.setPerson(personToAssignTask, editedPerson);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        StringBuilder updatedPersonsStrBld = new StringBuilder();
        for (Person personToAssignTask : members) {
            updatedPersonsStrBld.append(
                    String.format(MESSAGE_ARGUMENTS, personToAssignTask.getName(), this.group,this.task) + "\n");
        }
        String updatedPersonsString = updatedPersonsStrBld.toString();

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS + "\n" + updatedPersonsString));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignTaskAllCommand)) {
            return false;
        }

        // state check
        AssignTaskAllCommand e = (AssignTaskAllCommand) other;
        return group.equals(e.group)
                && task.equals(e.task);
    }
}
