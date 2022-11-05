package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;

import tuthub.commons.core.Messages;
import tuthub.model.Model;
import tuthub.model.tutor.EmailContainsKeywordsPredicate;

/**
 * Finds and lists all tutors in TutHub whose email contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindByEmailCommand extends FindByPrefixCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tutors whose email contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EMAIL + "alex john";

    private final EmailContainsKeywordsPredicate predicate;

    public FindByEmailCommand(EmailContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindByEmailCommand // instanceof handles nulls
                && predicate.equals(((FindByEmailCommand) other).predicate)); // state check
    }
}
