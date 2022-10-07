package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose address contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindAddressCommand extends FindCommand {
    private final AddressContainsKeywordsPredicate predicate;

    public FindAddressCommand(AddressContainsKeywordsPredicate predicate) {
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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAddressCommand // instanceof handles nulls
                && predicate.equals(((FindAddressCommand) other).predicate)); // state check
    }
}
