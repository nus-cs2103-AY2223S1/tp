package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;

import java.util.function.Predicate;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Finds and lists all applicants in TrackAScholar whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all applicants who contain any of the "
            + "specified attribute keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: must contain at least one prefix with non empty input "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_SCHOLARSHIP + "SCHOLARSHIP]... "
            + "[" + PREFIX_MAJOR + "MAJOR]... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Sam "
            + PREFIX_SCHOLARSHIP + "NUS Merit Scholarship "
            + PREFIX_MAJOR + "Software Engineering "
            + PREFIX_MAJOR + "Mathematics";

    private final Predicate<Applicant> predicate;

    public FindCommand(Predicate<Applicant> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
