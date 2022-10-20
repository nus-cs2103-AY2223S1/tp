package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all modules and persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_TYPE = "find";
    public static final String COMMAND_WORD = COMMAND_TYPE;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all Modules and Persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2100 cs ";

    private final NameContainsKeywordsPredicate personPredicate;
    private final ModuleCodeContainsKeywordsPredicate modulePredicate;

    /**
     * Constructor
     * @param personPredicate keyword to filter person list by
     * @param modulePredicate keyword to filter module list by
     */
    public FindCommand(NameContainsKeywordsPredicate personPredicate,
                       ModuleCodeContainsKeywordsPredicate modulePredicate) {
        this.personPredicate = personPredicate;
        this.modulePredicate = modulePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert(personPredicate instanceof NameContainsKeywordsPredicate);
        assert(modulePredicate instanceof ModuleCodeContainsKeywordsPredicate);
        model.updateFilteredPersonList(personPredicate);
        model.updateFilteredModuleList(modulePredicate);
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size())
                        + " " + String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW,
                model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && personPredicate.equals(((FindCommand) other).personPredicate)
                && modulePredicate.equals(((FindCommand) other).modulePredicate)); // state check
    }
}
