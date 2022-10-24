package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_INDEX_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_INDEX_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;

/**
 * Assigns a task to a specific member in the team.
 */
@CommandLine.Command(name = "task")
public class AssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "assign_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a task to the specified member in the team.\n"
            + "Parameters: index of task \n"
            + "Parameters: name of person \n"
            + "Example: " + COMMAND_WORD + " 1" + " Bernice Yu";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigned Task: %1$s assigned to %2$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This task has already been assigned to %1$s";
    public static final String MESSAGE_TASK_INDEX_OUT_OF_BOUNDS = "This task does not exist."
            + "There are less than %1$s tasks in your list.";
    public static final String MESSAGE_MEMBER_DOES_NOT_EXIST = "This member does not exist.";

    @CommandLine.Option(names = {FLAG_INDEX_STR, FLAG_INDEX_STR_LONG}, required = true)
    private Index taskIndex;

    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true)
    private Name memberName;

    public AssignTaskCommand() {
    }

    /**
     * Returns a command that assigns a task to the specified member in the team.
     *
     * @param taskIndex  the index of the task to be added.
     * @param memberName the name of the member that the task is assigned to.
     */
    public AssignTaskCommand(int taskIndex, String memberName) {
        this.taskIndex = Index.fromZeroBased(taskIndex);
        this.memberName = new Name(memberName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> taskList = model.getTeam().getTaskList();
        List<Person> memberList = model.getTeam().getTeamMembers();
        Person currentPerson = null;
        for (Person person : memberList) {
            System.out.println(person.getName() + " " + memberName);
            if (person.getName().equals(memberName)) {
                currentPerson = person;
                break;
            }
        }
        if (taskIndex.getZeroBased() >= taskList.size()) {
            throw new CommandException(String.format(MESSAGE_TASK_INDEX_OUT_OF_BOUNDS, taskIndex.getOneBased()));
        }
        if (currentPerson == null) {
            throw new CommandException(MESSAGE_MEMBER_DOES_NOT_EXIST);
        }
        if (taskList.get(taskIndex.getZeroBased()).checkAssignee(currentPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ASSIGNMENT, currentPerson.getName()));
        }
        model.getTeam().assignTask(taskList.get(taskIndex.getZeroBased()), currentPerson);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                taskList.get(taskIndex.getZeroBased()).getName(), memberName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignTaskCommand // instanceof handles nulls
                && taskIndex == (((AssignTaskCommand) other).taskIndex)) // state check
                && memberName == (((AssignTaskCommand) other).memberName); // state check
    }
}
