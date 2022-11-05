package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.customer.CustomerContainsAllTagPredicate;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Finds and lists all customers in ArtBuddy who includes all the tags.
 * Tag matching is case-sensitive.
 */
public class AllTagCustomerCommand extends Command {

    public static final String COMMAND_WORD = "allcus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customers who includes "
            + "all the tags.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " t/tag1 t/tag2 t/tag3";

    private final CustomerContainsAllTagPredicate predicate;

    public AllTagCustomerCommand(CustomerContainsAllTagPredicate predicate) {
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
                || (other instanceof AllTagCustomerCommand // instanceof handles nulls
                && predicate.equals(((AllTagCustomerCommand) other).predicate)); // state check
    }
}
