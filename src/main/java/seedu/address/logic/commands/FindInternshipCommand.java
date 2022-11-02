package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Finds and lists all internships in InterNUS whose fields contain any of the argument keywords.
 * Keyword matching is case-insensitive, and only the fields corresponding the specified prefixes will be checked.
 */
public class FindInternshipCommand extends Command {

    public static final String COMMAND_WORD = "find -i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all internships whose fields contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers. "
            + "Absent fields will not be searched. "
            + "e.g. \"No interviews scheduled\" will not find internships with blank interview date.\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY_NAME + " COMPANY_NAME_KEYWORDS...] "
            + "[" + PREFIX_INTERNSHIP_ROLE + " INTERNSHIP_ROLE_KEYWORDS...] "
            + "[" + PREFIX_INTERNSHIP_STATUS + " INTERNSHIP_STATUS_KEYWORDS...] "
            + "[" + PREFIX_INTERVIEW_DATE + " INTERVIEW_DATE_KEYWORDS...]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_COMPANY_NAME + "abc pte ltd";

    private final InternshipContainsKeywordsPredicate predicate;

    public FindInternshipCommand(InternshipContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInternshipCommand // instanceof handles nulls
                && predicate.equals(((FindInternshipCommand) other).predicate)); // state check
    }
}
