package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NormalTagContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.RiskTagContainsKeywordsPredicate;

import java.util.function.Predicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) or risk tags, \n"
            + "and displays them as a list with index numbers.\n\n"
            + "Parameters: PREFIX/ KEYWORD [MORE_KEYWORDS]\n"
            + PREFIX_NAME.getPrefix() + " KEYWORD [MORE_KEYWORDS]...\n"
            + PREFIX_RISKTAG.getPrefix() + " RISKTAG [MORE_RISKTAGS]...\n"
            + PREFIX_TAG.getPrefix() + " TAG [MORE_TAGS]...\n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_NAME.getPrefix() + " alice bob charlie\n"
            + COMMAND_WORD + " " + PREFIX_RISKTAG.getPrefix() + " high low";

    private final Predicate<Person> predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(RiskTagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindCommand(NormalTagContainsKeywordsPredicate predicate) {
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
