package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORKLOAD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Adds an assignment of an existing person in a group in the address book.
 * Assignment must consist of a workload, with an optional deadline field.
 */
public class AssignTaskCommand extends Command {

    public static final String COMMAND_WORD = "assigntask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign task to a user with the given name in a "
            + "group.\nWorkload specified must be low, medium or high.\n"
            + "Deadline must be in yyyy-MM-dd or yyyy-MM-dd HH:mm format\n"
            + "Parameters: NAME "
            + PREFIX_GROUP + "GROUP "
            + PREFIX_TASK + "TASK "
            + PREFIX_WORKLOAD + "WORKLOAD "
            + PREFIX_DEADLINE + "DEADLINE\n"
            + "Example: " + COMMAND_WORD
            + " alice g/Group Alpha task/Coursework 0 w/High d/2022-01-01 23:59";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Group: %2$s Task: %3$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_TASK = "This person already has a task of this name.";
    public static final String MESSAGE_INVALID_PERSON = "This person is not in the address book.";
    public static final String MESSAGE_INVALID_PERSON_NOT_IN_GROUP = "This person is not in the specified group.";
    public static final String MESSAGE_INVALID_GROUP = "This group is not in the address book.";
    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "New task added for the following person.";

    private final Name name;
    private final String group;
    private final Assignment task;

    /**
     * @param name of the person in the filtered person list to edit the remark
     * @param task of the person to be updated to
     */
    public AssignTaskCommand(Name name, String group, Assignment task) {
        requireAllNonNull(name, group, task);
        this.name = name;
        this.group = group;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Person> personList = model.getPersonWithName(this.name);
        Person personToAssignTask;

        try {
            personToAssignTask = personList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        ObservableList<Group> groupList = model.getGroupWithName(new GroupName(this.group));
        Group groupToCheck;
        try {
            groupToCheck = groupList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_GROUP);
        }

        if (!groupToCheck.contains(personToAssignTask)) {
            throw new CommandException(MESSAGE_INVALID_PERSON_NOT_IN_GROUP);
        }

        HashMap<String, ArrayList<Assignment>> assignments = personToAssignTask.getAssignments();

        ArrayList<Assignment> listOfAssignment;
        if (assignments.containsKey(group)) {
            listOfAssignment = assignments.get(group);
        } else {
            listOfAssignment = new ArrayList<>();
        }

        if (listOfAssignment.contains(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        listOfAssignment.add(task);
        assignments.put(group, listOfAssignment);

        Person editedPerson = new Person(
                personToAssignTask.getName(), personToAssignTask.getPhone(), personToAssignTask.getEmail(),
                personToAssignTask.getAddress(), personToAssignTask.getTags(), assignments,
                personToAssignTask.getPersonGroups());

        model.setPerson(personToAssignTask, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS + "\n" + MESSAGE_ARGUMENTS,
                this.name, this.group, this.task));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignTaskCommand)) {
            return false;
        }

        // state check
        AssignTaskCommand e = (AssignTaskCommand) other;
        return name.equals(e.name)
                && group.equals(e.group)
                && task.equals(e.task);
    }
}
