package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.NameContainsKeywordsPredicate;

/**
 * Finds and lists all internships applications in InTrack whose company name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindNameCommand extends Command {

    public static final String COMMAND_WORD = "findc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internship applications whose company name "
            + "contains any of the specified keywords (case-insensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Google";

    private final NameContainsKeywordsPredicate predicate;

    public FindNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindNameCommand // instanceof handles nulls
                && predicate.equals(((FindNameCommand) other).predicate)); // state check
    }

}
