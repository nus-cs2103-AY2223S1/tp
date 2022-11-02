package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;

/**
 * Finds and lists all properties in property book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPropertiesCommand extends Command {

    public static final String COMMAND_WORD = "findprops";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all properties whose property name contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Heng Mui Keng";

    private final PropertyNameContainsKeywordsPredicate predicate;

    public FindPropertiesCommand(PropertyNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROPERTY_LISTED_OVERVIEW, model.getFilteredPropertyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPropertiesCommand // instanceof handles nulls
                && predicate.equals(((FindPropertiesCommand) other).predicate)); // state check
    }
}
