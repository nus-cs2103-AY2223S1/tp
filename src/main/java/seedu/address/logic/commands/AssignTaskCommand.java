package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_INDEX_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_INDEX_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.Arrays;
import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Assigns a task to a specific member in the team.
 */
@CommandLine.Command(name = "task")
public class AssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "assign task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a task to the specified member in the team.\n"
            + "Parameters: TASK_INDEX (must be a valid positive integer) \n"
            + "Parameters: MEMBER_INDEX (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigned task succesfully. %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This task has already been assigned to %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist."
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS = "Invalid member index provided";

    @CommandLine.Option(names = {FLAG_INDEX_STR, FLAG_INDEX_STR_LONG}, required = true)
    private Index taskIndex;

    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true)
    private String[] assignees;

    public AssignTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getTeam().getTaskList();
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        List<String> assigneesList;
        if (assignees.length == 1 && Arrays.asList(assignees).contains("")) {
            assigneesList = List.of();
        } else {
            assigneesList = Arrays.asList(assignees);
        }
        List<Person> memberList = model.getTeam().getTeamMembers();
        for (int i = 0; i < assigneesList.size(); i++) {
            if (Integer.parseInt(assigneesList.get(i)) < 1
                    || Integer.parseInt(assigneesList.get(i)) > memberList.size()) {
                throw new CommandException(MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS);
            }
        }
        List<Person> assigneePersonList = new java.util.ArrayList<>(List.of());
        for (String index : assigneesList) {
            int assigneeIndex = Integer.parseInt(index);
            assigneePersonList.add(memberList.get(assigneeIndex - 1));
        }
        for (Person assignee : assigneePersonList) {
            if (taskList.get(taskIndex.getZeroBased()).checkAssignee(assignee)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_ASSIGNMENT, assignee.getName()));
            }
        }
        for (Person assignee : assigneePersonList) {
            taskList.get(taskIndex.getZeroBased()).assignTo(assignee);
        }
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                taskList.get(taskIndex.getZeroBased())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignTaskCommand // instanceof handles nulls
                && taskIndex.equals(((AssignTaskCommand) other).taskIndex)); // state check
    }
}
