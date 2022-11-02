package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;

import java.util.List;
import java.util.Map;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Displays a summary of how tasks have been assigned in the current team.
 */
@CommandLine.Command(name = TasksSummaryCommand.COMMAND_WORD,
        aliases = {TasksSummaryCommand.ALIAS_SU, TasksSummaryCommand.ALIAS_SUM}, mixinStandardHelpOptions = true)
public class TasksSummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";
    public static final String ALIAS_SU = "su";
    public static final String ALIAS_SUM = "sum";
    public static final String FULL_COMMAND = COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND
            + ": Displays a summary of how tasks have been assigned in the current team.\n"
            + "Example: " + FULL_COMMAND;

    public static final String MESSAGE_TASK_SUMMARY = "Task Summary: \n%1$s";

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    public TasksSummaryCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Person> members = model.getTeam().getTeamMembers();
        Map<Person, Integer> tasks = model.getTeam().getNumTasksPerPerson();

        StringBuilder taskSummary = new StringBuilder();
        for (int i = 0; i < members.size(); i++) {
            taskSummary.append(i + 1).append(". ").append(members.get(i).getName()).append(": ")
                    .append(tasks.getOrDefault(members.get(i), 0)).append(" task(s) assigned\n");
        }

        return new CommandResult(String.format(MESSAGE_TASK_SUMMARY, taskSummary));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TasksSummaryCommand;
    }
}
