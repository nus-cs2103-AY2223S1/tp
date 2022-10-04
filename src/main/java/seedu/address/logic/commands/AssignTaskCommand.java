package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Changes the remark of an existing person in the address book.
 */public class AssignTaskCommand extends Command {

    public static final String COMMAND_WORD = "assigntask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign task to a user with the given name in a group"
            + "Parameters: NAME " + PREFIX_GROUP + "GROUP "+ PREFIX_TASK + "TASK\n"
            + "Example: " + COMMAND_WORD + " alice g/Group Alpha t/Coursework 0";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Group: %2$s Task: %3$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_INVALID_PERSON = "This person is not in the address book.";
    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "ASSIGNTASK";

    private final Name name;
    private final String group;
    private final Assignment task;

    /**
     * @param name of the person in the filtered person list to edit the remark
     * @param task of the person to be updated to
     */
    public AssignTaskCommand(String name, String group, String task) {
        requireAllNonNull(name, group, task);
        this.name = new Name(name);
        this.group = group;
        this.task = new Assignment(task);
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
                personToAssignTask.getAddress(), personToAssignTask.getTags(), assignments);

        if (!personToAssignTask.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToAssignTask, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS));
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