package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TASK_SEARCH_KEYWORDS_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.LABEL_TASK_NAME_KEYWORDS;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TaskNameContainsKeywordsPredicateConverter;
import seedu.address.model.Model;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in the current team whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
@CommandLine.Command(name = FindTaskCommand.COMMAND_WORD,
        aliases = {FindTaskCommand.ALIAS}, mixinStandardHelpOptions = true)
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "task";
    public static final String ALIAS = "ta";
    public static final String FULL_COMMAND = FindCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final String HELP_MESSAGE =
            "The '" + FULL_COMMAND + "' command is used to find a task in the current team's task list.\n";

    public static final String MESSAGE_SUCCESS = "Showing all %1$d task(s) containing search string(s): %2$s.\n"
            + "Type `list tasks` to show all tasks again.";

    @CommandLine.Parameters(arity = "1", paramLabel = LABEL_TASK_NAME_KEYWORDS,
            parameterConsumer = TaskNameContainsKeywordsPredicateConverter.class,
            description = FLAG_TASK_SEARCH_KEYWORDS_DESCRIPTION)
    private TaskNameContainsKeywordsPredicate predicate;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public FindTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(HELP_MESSAGE + commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredTaskList().size(), predicate.getKeywordsAsString()));
    }

}
