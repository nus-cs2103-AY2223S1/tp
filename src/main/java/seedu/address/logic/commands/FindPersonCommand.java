package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Finds and lists all persons in InterNUS whose fields contain any of the argument keywords.
 * Keyword matching is case-insensitive, and only the fields corresponding the specified prefixes will be checked.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "find -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose fields contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers. "
            + "Absent fields will not be searched. "
            + "e.g. \"No company\" will not find persons with blank company field.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + " NAME_KEYWORDS...] "
            + "[" + PREFIX_PHONE + " PHONE_KEYWORDS...] "
            + "[" + PREFIX_EMAIL + " EMAIL_KEYWORDS...] "
            + "[" + PREFIX_TAG + " TAG_KEYWORDS...] "
            + "[" + PREFIX_COMPANY + "COMPANY_KEYWORDS...]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie";

    private final PersonContainsKeywordsPredicate predicate;

    public FindPersonCommand(PersonContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
