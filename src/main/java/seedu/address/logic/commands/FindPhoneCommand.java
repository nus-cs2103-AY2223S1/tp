package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose phone number contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindPhoneCommand extends Command {

    public static final String COMMAND_WORD = "findphone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone numbers contain any of "
            + "the sequence of numbers and displays them as a list with index phones.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 86671234 91234567";

    private final PhoneContainsKeywordsPredicate predicate;

    public FindPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindPhoneCommand // instanceof handles nulls
                && predicate.equals(((FindPhoneCommand) other).predicate)); // state check
    }


}
