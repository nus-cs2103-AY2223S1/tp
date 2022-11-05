package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.commission.CompositeCustomerPredicate;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Finds and lists all customers in address book whose name contains any of the argument keywords
 * and possesses all the necessary tags and at least one of the optional tags if specified.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all currently listed customers whose "
            + "name contains any of the specified keywords (case-insensitive) and "
            + "includes all of the specified tags under -all and "
            + "at least one of the tags under -any.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... -all TAG [MORE_TAGS]... -any TAG [MORE_TAGS]\n"
            + "Example: " + COMMAND_WORD + " k/alice k/bob -all t/tag1 t/tag2 t/tag3 -any t/tag4 t/tag5";

    private final CompositeCustomerPredicate predicate;

    public FindCommand(CompositeCustomerPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        model.selectTab(GuiTab.CUSTOMER);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW,
                        model.getSortedFilteredCustomerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
