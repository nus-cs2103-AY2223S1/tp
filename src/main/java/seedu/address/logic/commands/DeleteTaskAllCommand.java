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
 * Deletes an assignment to all members in a group in the address book.
 * Skips over members who do not have the assignment.
 */
public class DeleteTaskAllCommand extends Command {

    public static final String COMMAND_WORD = "deletetaskall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete task from all users in a group. "
            + "Members who do not have this task are skipped over. "
            + "Parameters: " + PREFIX_GROUP + "GROUP " + PREFIX_TASK + "TASK\n"
            + "Example: " + COMMAND_WORD + " g/Group Alpha task/Coursework 0";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Group: %2$s Task: %3$s";
    public static final String MESSAGE_INVALID_GROUP = "This group is not in the address book.";
    public static final String MESSAGE_NO_MEMBERS = "This group does not have any members.";
    public static final String MESSAGE_NO_TASKS_DELETED = "All members of this group do not have this task.";
    public static final String MESSAGE_MEMBER_LIST_ERROR = "The group specified has an erroneous member list.";
    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Task deleted for the following persons.";

    public final String group;
    public final Assignment task;

    /**
     * Deletes an assignment to all members in a group in the address book.
     * Skips over members who do not have the assignment.
     */
    public DeleteTaskAllCommand(String group, Assignment task) {
        requireAllNonNull(group, task);
        this.group = group;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Group> groupList = model.getGroupWithName(new GroupName(this.group));
        Group groupToAssign;
        ArrayList<Person> successfullyDeleted = new ArrayList<>();

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
            Person personToDeleteTask;
            try {
                personToDeleteTask = model.getPersonWithName(p.getName()).get(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CommandException(MESSAGE_MEMBER_LIST_ERROR);
            }

            HashMap<String, ArrayList<Assignment>> assignments = personToDeleteTask.getAssignments();

            ArrayList<Assignment> listOfAssignment;
            if (assignments.containsKey(group)) {
                listOfAssignment = assignments.get(group);
            } else {
                continue;
            }

            if (listOfAssignment.contains(task)) {
                listOfAssignment.remove(task);
            } else {
                continue;
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
            successfullyDeleted.add(editedPerson);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (successfullyDeleted.isEmpty()) {
            throw new CommandException(MESSAGE_NO_TASKS_DELETED);
        }

        StringBuilder updatedPersonsStrBld = new StringBuilder();
        for (Person personToAssignTask : successfullyDeleted) {
            updatedPersonsStrBld.append(
                    String.format(MESSAGE_ARGUMENTS, personToAssignTask.getName(), this.group, this.task));
        }
        String updatedPersonsString = updatedPersonsStrBld.toString();

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS + "\n" + updatedPersonsString));
    }
}
