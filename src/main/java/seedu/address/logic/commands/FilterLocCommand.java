package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.LocationContainsKeywordsPredicate;
import seedu.address.model.person.Supplier;

/**
 * Filters and lists all persons in address book whose locations match the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class FilterLocCommand extends Command {
    public static final String COMMAND_WORD = "filterLoc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons who are tagged: "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Singapore";

    private final LocationContainsKeywordsPredicate<Buyer> bPredicate;
    private final LocationContainsKeywordsPredicate<Deliverer> dPredicate;
    private final LocationContainsKeywordsPredicate<Supplier> sPredicate;

    public FilterLocCommand(LocationContainsKeywordsPredicate<Buyer> bPredicate,
                            LocationContainsKeywordsPredicate<Deliverer> dPredicate,
                            LocationContainsKeywordsPredicate<Supplier> sPredicate) {
        this.bPredicate = bPredicate;
        this.dPredicate = dPredicate;
        this.sPredicate = sPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(bPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredBuyerList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterLocCommand // instanceof handles nulls
                && bPredicate.equals(((FilterLocCommand) other).bPredicate)
                && dPredicate.equals(((FilterLocCommand) other).dPredicate)
                && sPredicate.equals(((FilterLocCommand) other).sPredicate)); // state check
    }
}
