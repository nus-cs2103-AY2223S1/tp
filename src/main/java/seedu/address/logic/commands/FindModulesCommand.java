package seedu.address.logic.commands;


import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all modules in the module list whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */

public class FindModulesCommand extends Command {
    public static final String COMMAND_WORD = "findmodules";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all modules whose names partially or fully contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " module1";

    private final ModuleCodeContainsKeywordsPredicate predicate;

    public FindModulesCommand(ModuleCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredModuleList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindModulesCommand // instanceof handles nulls
                && predicate.equals(((FindModulesCommand) other).predicate)); // state check
    }
}


