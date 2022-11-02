package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.internship.Internship;

/**
 * Sorts the internship list based on the given prefix.
 */
public class SortInternshipCommand extends Command {

    public static final String COMMAND_WORD = "sort -i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts internships by company, interview date, or internship status.\n"
            + "Parameters: [c/] [d/] [s/] (Only 1 criterion can be specified)\n"
            + "Example: " + COMMAND_WORD + " c/";

    public static final String MESSAGE_SUCCESS = "Sorted internships by %1$s";

    /**
     * Possible sorting criteria for internship list.
     */
    public enum Criteria {
        COMPANY_NAME("company name"),
        INTERVIEW_DATE("interview date"),
        INTERNSHIP_STATUS("status");

        private final String name;

        Criteria(String name) {
            this.name = name;
        }

        private String getName() {
            return name;
        }
    }

    private final Criteria criteria;

    public SortInternshipCommand(Criteria c) {
        this.criteria = c;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert (criteria != null);
        switch (criteria) {
        case COMPANY_NAME:
            model.sortInternshipList(Internship.compareByCompanyName());
            break;
        case INTERVIEW_DATE:
            model.sortInternshipList(Internship.compareByInterviewDate());
            break;
        case INTERNSHIP_STATUS:
            model.sortInternshipList(Internship.compareByInternshipStatus());
            break;
        default:
            break;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, criteria.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortInternshipCommand // instanceof handles nulls
                && criteria.equals(((SortInternshipCommand) other).criteria)); // state check
    }
}
