package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCodeStartsWithKeywordPredicate;

/**
 * Finds and lists all modules in Plannit whose module code starts with the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class FindModuleCommand extends Command {
    public static final String COMMAND_WORD = "find-module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all modules whose module code starts with "
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " CS";

    private final ModuleCodeStartsWithKeywordPredicate predicate;

    public FindModuleCommand(ModuleCodeStartsWithKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isNotAtHome = !model.getHomeStatusAsBoolean();

        if (isNotAtHome) {
            throw new CommandException(Messages.MESSAGE_NOT_AT_HOMEPAGE);
        }
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleCommand // instanceof handles nulls
                && predicate.equals(((FindModuleCommand) other).predicate)); // state check
    }
}
