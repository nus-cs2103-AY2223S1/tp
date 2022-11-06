package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ItemContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all suppliers in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all suppliers whose, depending on the "
            + "command prefix used, names or items contain any of the specified keywords\n"
            + "(case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD n/[NAME_KEYWORDS] or KEYWORD i/[ITEM_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " n/Pte Ltd, " + COMMAND_WORD + " i/Chicken";

    public static final String MESSAGE_NAME_EMPTY_COMMAND = "Name field must be provided, cannot be empty !\n"
            + "Example: " + COMMAND_WORD + " n/Pte Ltd";
    public static final String MESSAGE_ITEM_EMPTY_COMMAND = "Item field must be provided, cannot be empty !\n"
            + "Example: " + COMMAND_WORD + " i/Chicken";

    private final Predicate<Person> predicate;

    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this.predicate = namePredicate;
    }

    public FindCommand(ItemContainsKeywordsPredicate itemPredicate) {
        this.predicate = itemPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
