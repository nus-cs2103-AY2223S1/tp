package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the person list based on the given prefix.
 */
public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sortp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons based on name or company name"
            + "Parameters: KEYWORD [c/] [n/]\n"
            + "Example: " + COMMAND_WORD + " c/";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "There should only be 1 sorting criterion.";
    public static final String MESSAGE_SUCCESS = "Sorted all persons";

    /**
     * Possible sorting criteria for person list.
     */
    public enum Criteria { NAME, COMPANY_NAME }

    private Criteria criteria;

    public SortPersonCommand(Criteria c) {
        this.criteria = c;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert (criteria != null);
        if (criteria == Criteria.NAME) {
            model.sortPersonList(Comparator.comparing(Person::getName));
        } else if (criteria == Criteria.COMPANY_NAME) {
            // internshipId can be null, so it needs a separate null handler
            model.sortPersonList(Comparator.comparing(Person::getInternshipId,
                    Comparator.nullsLast(Comparator.naturalOrder())));
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortPersonCommand // instanceof handles nulls
                && criteria.equals(((SortPersonCommand) other).criteria)); // state check
    }
}
