package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;

import java.util.List;
import java.util.function.Predicate;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.team.Task;


/**
 * Displays a summary of how tasks have been assigned in the current team.
 */
@CommandLine.Command(name = TasksOfCommand.COMMAND_WORD)
public class TasksOfCommand extends Command {
    public static final String COMMAND_WORD = "tasksof";

    public static final String FULL_COMMAND = COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Displays all tasks of a particular team member.\n"
            + "Parameters: MEMBER_INDEX (must be a valid positive integer)\n"
            + "Example: " + FULL_COMMAND + " 1";

    public static final String MESSAGE_SUCCESS = "Showing all %1$d task(s) assigned to %2$s. \n"
            + "Type `list tasks` to show all tasks again.";
    public static final String MESSAGE_MEMBER_INDEX_TOO_LARGE = "This member does not exist. "
            + "There are less than %1$s members in your list.";
    public static final String MESSAGE_MEMBER_INDEX_TOO_SMALL = "This index is invalid. "
            + "Please input a valid positive integer.";

    @CommandLine.Parameters(arity = "1")
    private Index memberIndex;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public TasksOfCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Person> members = model.getFilteredMemberList();
        if (memberIndex.getZeroBased() >= members.size()) {
            throw new CommandException(
                    String.format(MESSAGE_MEMBER_INDEX_TOO_LARGE, memberIndex.getOneBased()));
        } else if (memberIndex.getOneBased() < 1) {
            throw new CommandException(MESSAGE_MEMBER_INDEX_TOO_SMALL);
        }
        Person member = members.get(memberIndex.getZeroBased());
        Predicate<Task> predicate = task -> task.checkAssignee(member);
        model.updateFilteredTaskList(predicate);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredTaskList().size(), member.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TasksOfCommand
                && memberIndex.equals(((TasksOfCommand) other).memberIndex);
    }
}
