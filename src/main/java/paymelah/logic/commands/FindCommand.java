package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_EMAIL;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;

import paymelah.commons.core.Messages;
import paymelah.model.Model;
import paymelah.model.person.PersonMatchesDescriptorPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons containing all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_DESCRIPTION + "DEBT DESCRIPTION]..."
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "john "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney"
            + PREFIX_DESCRIPTION + "burger ";

    public static final String MESSAGE_NO_KEYWORDS = "At least one field to search for must be provided.";

    private final PersonMatchesDescriptorPredicate predicate;

    public FindCommand(PersonMatchesDescriptorPredicate predicate) {
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
