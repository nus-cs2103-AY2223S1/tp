package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.COMMAND_IDENTIFIER_MODULE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;

/**
 * Finds and lists all modules in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindModuleCommand extends Command {

    public static final String COMMAND_TYPE = "find";
    public static final String COMMAND_WORD = COMMAND_TYPE + COMMAND_IDENTIFIER_MODULE;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Modules whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2100 cs ";

    private final ModuleCodeContainsKeywordsPredicate predicate;

    public FindModuleCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModuleCommand // instanceof handles nulls
                && predicate.equals(((FindModuleCommand) other).predicate)); // state check
    }
}
