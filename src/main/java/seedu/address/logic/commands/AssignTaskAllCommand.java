package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORKLOAD;
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
 * An assignment must consist of a workload, with an optional deadline field.
 * Skips over members who already have the assignment.
 */
public class AssignTaskAllCommand extends Command {

    public static final String COMMAND_WORD = "assigntaskall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign task to all users in a group.\n"
            + "Workload specified must be low, medium or high.\n"
            + "Deadline must be in yyyy-MM-dd or yyyy-MM-dd HH:mm format\n"
            + "Members with a similarly named task are skipped over.\n"
            + "Parameters: "
            + PREFIX_GROUP + "GROUP "
            + PREFIX_TASK + "TASK"
            + PREFIX_WORKLOAD + "WORKLOAD "
            + PREFIX_DEADLINE + "DEADLINE\n"
            + "Example: " + COMMAND_WORD
            + " g/Group Alpha task/Coursework 0 w/High d/2022-01-01 23:59";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Group: %2$s Task: %3$s";
    public static final String MESSAGE_INVALID_GROUP = "This group is not in the address book.";
    public static final String MESSAGE_NO_MEMBERS = "This group does not have any members.";
    public static final String MESSAGE_NO_TASKS_ADDED = "All members of this group already have this task.";
    public static final String MESSAGE_MEMBER_LIST_ERROR = "The group specified has an erroneous member list.";
    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "New task added for the following persons.";

    private final String group;
    private final Assignment task;

    /**
     * Assigns an assignment to all members in a group in the address book.
     * An assignment must consist of a workload, with an optional deadline field.
     * Skips over members who already have the assignment.
     */
    public AssignTaskAllCommand(String group, Assignment task) {
        requireAllNonNull(group, task);
        this.group = group;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Group> groupList = model.getGroupWithName(new GroupName(this.group));
        Group groupToAssign;
        ArrayList<Person> successfullyAdded = new ArrayList<>();

        try {
            groupToAssign = groupList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_GROUP);
        }

        Set<Person> members = groupToAssign.getMembers();

        if (members.isEmpty()) {
            throw new CommandException(MESSAGE_NO_MEMBERS);
        }

        for (Person p : members) {
            Person personToAssignTask;
            try {
                personToAssignTask = model.getPersonWithName(p.getName()).get(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CommandException(MESSAGE_MEMBER_LIST_ERROR);
            }

            HashMap<String, ArrayList<Assignment>> assignments = personToAssignTask.getAssignments();

            ArrayList<Assignment> listOfAssignment;
            if (assignments.containsKey(group)) {
                listOfAssignment = assignments.get(group);
            } else {
                listOfAssignment = new ArrayList<>();
            }

            if (listOfAssignment.contains(task)) {
                continue;
            }

            listOfAssignment.add(task);
            assignments.put(group, listOfAssignment);

            Person editedPerson = new Person(
                    personToAssignTask.getName(), personToAssignTask.getPhone(), personToAssignTask.getEmail(),
                    personToAssignTask.getAddress(), personToAssignTask.getTags(), assignments,
                    personToAssignTask.getPersonGroups());

            model.setPerson(personToAssignTask, editedPerson);
            successfullyAdded.add(editedPerson);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (successfullyAdded.isEmpty()) {
            throw new CommandException(MESSAGE_NO_TASKS_ADDED);
        }

        StringBuilder updatedPersonsStrBld = new StringBuilder();
        for (Person personToAssignTask : successfullyAdded) {
            updatedPersonsStrBld.append(
                    String.format(MESSAGE_ARGUMENTS, personToAssignTask.getName(), this.group, this.task));
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
