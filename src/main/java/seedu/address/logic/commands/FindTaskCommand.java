package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.List;
import java.util.function.Predicate;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in the current team whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
@CommandLine.Command(name = "task", aliases = {"ta"})
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "-" + FLAG_NAME_STR + " NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " teams feature ";

    public static final String MESSAGE_SUCCESS = "Showing all %1$d task(s) containing search string(s)%2$s. \n"
            + "Type `list tasks` to show all tasks again.";
    @CommandLine.ArgGroup(exclusive = true, multiplicity = "1")
    private Exclusive predicate;
    static class Exclusive {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG},
                required = true, arity = "1..*")
        private String[] nameKeywords;
        Predicate<Task> getPredicate() {
            return new TaskNameContainsKeywordsPredicate(List.of(nameKeywords));
        }
        String keywordsToString() {
            return List.of(nameKeywords).stream().reduce("", (a, b) -> a + " " + b);
        }
    }


    public FindTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate.getPredicate());
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredTaskList().size(), predicate.keywordsToString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
