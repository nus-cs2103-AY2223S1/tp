package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_MEMBER_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_MEMBER_INDEX;

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
@CommandLine.Command(name = TasksOfCommand.COMMAND_WORD, aliases = {TasksOfCommand.ALIAS})
public class TasksOfCommand extends Command {
    public static final String COMMAND_WORD = "tasksof";
    public static final String ALIAS = "to";
    public static final String FULL_COMMAND = COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to view all tasks assigned to a particular team member.\n";

    public static final String MESSAGE_SUCCESS = "Showing all %1$d task(s) assigned to %2$s. \n"
            + "Type `list tasks` to show all tasks again.";
    public static final String MESSAGE_MEMBER_INDEX_TOO_LARGE = "This member does not exist. "
            + "There are less than %1$s members in your list.";
    public static final String MESSAGE_MEMBER_INDEX_TOO_SMALL = "This index is invalid. "
            + "Please input a valid positive integer.";

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_MEMBER_INDEX, description = FLAG_MEMBER_INDEX_DESCRIPTION)
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
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
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

}
