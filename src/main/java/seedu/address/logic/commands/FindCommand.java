package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonMatchesPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names or modules "
            + "matches any of the specified keywords (case-insensitive) and displays them as a list "
            + "with index numbers. Parameters: n/NAME m/MODULE_CODE [s/SPECIALISATION] p/PHONE e/EMAIL"
            + "g/GENDER [t/TAG]... l/LOCATION [r/RATING] Example: " + COMMAND_WORD
            + " n/alice bob charlie m/cs2100 cs2103t p/1234567 t/friends";

    private final PersonMatchesPredicate predicate;

    public FindCommand(PersonMatchesPredicate predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
