package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.ArrayList;
import java.util.List;

import swift.commons.core.Messages;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.person.PersonNameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "find_contact";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(List.of(PREFIX_KEYWORD));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final PersonNameContainsKeywordsPredicate predicate;

    public FindContactCommand(PersonNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                CommandType.CONTACTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }
}
