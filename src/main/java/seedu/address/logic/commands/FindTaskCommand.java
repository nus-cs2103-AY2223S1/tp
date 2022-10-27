package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TaskNameContainsKeywordsPredicateConverter;
import seedu.address.model.Model;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in the current team whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
@CommandLine.Command(name = "task")
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "-" + FLAG_NAME_STR + " NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " teams feature ";
    @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, required = true,
            paramLabel = "keywords to find", arity = "1", parameterConsumer = TaskNameContainsKeywordsPredicateConverter.class)
    private TaskNameContainsKeywordsPredicate predicate;

    public FindTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
