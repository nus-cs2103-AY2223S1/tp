package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;

/**
 * Deletes the task of an existing person in the address book.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete task from a user with the given name in a group"
            + "Parameters: NAME " + PREFIX_GROUP + "GROUP " + PREFIX_TASK + "TASK\n"
            + "Example: " + COMMAND_WORD + " alice g/Group Alpha task/Coursework 0";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Group: %2$s Task: %3$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_INVALID_PERSON = "This person is not in the address book.";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "DELETETASK";
    public static final String MESSAGE_GROUP_NOT_FOUND = "This group is not in the address book.";
    public static final String MESSAGE_ASSIGNMENT_NOT_FOUND = "This person does not have this assignment.";
    public static final String MESSAGE_INVALID_PERSON_NOT_IN_GROUP = "This person is not in the specified group.";

    private final Name name;
    private final String group;
    private final Assignment task;

    /**
     * @param name of the person in the filtered person list to edit the remark
     * @param task of the person to be updated to
     */
    public DeleteTaskCommand(Name name, String group, Assignment task) {
        requireAllNonNull(name, group, task);
        this.name = name;
        this.group = group;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Person> personList = model.getPersonWithName(this.name);
        Person personToDeleteTask;

        try {
            personToDeleteTask = personList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }
        HashMap<String, ArrayList<Assignment>> assignments = personToDeleteTask.getAssignments();

        ArrayList<Assignment> listOfAssignment;
        if (assignments.containsKey(group)) {
            listOfAssignment = assignments.get(group);
        } else {
            if (model.getGroupWithName(new GroupName(group)).size() == 0) {
                throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
            } else {
                if (personToDeleteTask.getPersonGroups().contains(new PersonGroup(group))) {
                    throw new CommandException(MESSAGE_ASSIGNMENT_NOT_FOUND);
                }
                throw new CommandException(MESSAGE_INVALID_PERSON_NOT_IN_GROUP);
            }
        }

        if (listOfAssignment.contains(task)) {
            listOfAssignment.remove(task);
        } else {
            throw new CommandException(MESSAGE_ASSIGNMENT_NOT_FOUND);
        }

        if (listOfAssignment.size() != 0) {
            assignments.put(group, listOfAssignment);
        } else {
            assignments.remove(group);
        }

        Person editedPerson = new Person(
                personToDeleteTask.getName(), personToDeleteTask.getPhone(), personToDeleteTask.getEmail(),
                personToDeleteTask.getAddress(), personToDeleteTask.getTags(),
                assignments, personToDeleteTask.getPersonGroups());

        model.setPerson(personToDeleteTask, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS + "\n" + MESSAGE_ARGUMENTS,
                this.name, this.group, this.task));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return name.equals(e.name)
                && group.equals(e.group)
                && task.equals(e.task);
    }
}
