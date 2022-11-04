package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FindPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or risk tags, \n"
            + "and displays them as a list with index numbers.\n\n"
            + "Parameters: PREFIX/ KEYWORD [MORE_KEYWORDS]\n"
            + PREFIX_NAME.getPrefix() + " NAME [MORE_NAMES]...\n"
            + PREFIX_RISKTAG.getPrefix() + " RISK_APPETITE [MORE_RISK_APPETITES]...\n"
            + PREFIX_CLIENTTAG.getPrefix() + " CLIENT_TYPE [MORE_CLIENT_TYPES]...\n"
            + PREFIX_TAG.getPrefix() + " TAG [MORE_TAGS]...\n"
            + PREFIX_PHONE.getPrefix() + " PHONE [MORE_PHONE]...\n"
            + PREFIX_INCOME.getPrefix() + " >/</= INCOME \n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_NAME.getPrefix() + " alice bob charlie\n"
            + COMMAND_WORD + " " + PREFIX_RISKTAG.getPrefix() + " high low\n"
            + COMMAND_WORD + " " + PREFIX_CLIENTTAG.getPrefix() + " potential\n"
            + COMMAND_WORD + " " + PREFIX_PLANTAG.getPrefix() + " savings plan\n"
            + COMMAND_WORD + " " + PREFIX_PHONE.getPrefix() + " 91234567\n"
            + COMMAND_WORD + " " + PREFIX_INCOME.getPrefix() + " >1000";

    private final List<FindPredicate> predicates;

    public FindCommand(List<FindPredicate> predicates) {
        this.predicates = predicates;
    };

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicates);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        // state check
        return this.predicates.equals(((FindCommand) other).predicates);
    }
}
