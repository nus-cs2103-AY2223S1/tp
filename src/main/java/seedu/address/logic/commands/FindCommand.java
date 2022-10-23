package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String DESCRIPTION = "Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.";
    public static final String PARAMETER = "KEYWORD [MORE_KEYWORDS]...";
    public static final String EXAMPLE = COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + DESCRIPTION + "\n"
            + PARAMETER + "\n"
            + "Example: " + EXAMPLE;

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand() {
        this.predicate = null;
    }

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getParameter() {
        return PARAMETER;
    }

    @Override
    public String getExample() {
        return EXAMPLE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
