package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.customer.CustomerContainsAnyTagPredicate;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Finds and lists all customers in ArtBuddy who includes at least one of the tags.
 * Tag matching is case-sensitive.
 */
public class AnyTagCustomerCommand extends Command {

    public static final String COMMAND_WORD = "anycus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customers who includes at least "
            + "one of the tags.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " t/tag1 t/tag2 t/tag3";

    private final CustomerContainsAnyTagPredicate predicate;

    public AnyTagCustomerCommand(CustomerContainsAnyTagPredicate predicate) {
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
                || (other instanceof AnyTagCustomerCommand // instanceof handles nulls
                && predicate.equals(((AnyTagCustomerCommand) other).predicate)); // state check
    }
}
