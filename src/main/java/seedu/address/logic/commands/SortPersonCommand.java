package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the person list based on the given prefix.
 */
public class SortPersonCommand extends Command {

    public static final String COMMAND_WORD = "sort -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons based on person name or company name.\n"
            + "Parameters: [c/] [n/] (Only 1 criterion can be specified)\n"
            + "Example: " + COMMAND_WORD + " c/";

    public static final String MESSAGE_SUCCESS = "Sorted persons by %1$s";

    /**
     * Possible sorting criteria for person list.
     */
    public enum Criteria {
        NAME("person name"),
        COMPANY_NAME("company name");
        private final String name;

        Criteria(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    private final Criteria criteria;

    public SortPersonCommand(Criteria c) {
        this.criteria = c;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert (criteria != null);
        // solution adapted from
        // https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
        // #comparing-java.util.function.Function-java.util.Comparator-
        if (criteria == Criteria.NAME) {
            model.sortPersonList(Comparator.comparing(Person::getName));
        } else if (criteria == Criteria.COMPANY_NAME) {
            // internshipId can be null, so it needs a separate null handler
            model.sortPersonList(Comparator.comparing(Person::getCompany,
                    Comparator.nullsLast(Comparator.naturalOrder())));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, criteria.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortPersonCommand // instanceof handles nulls
                && criteria.equals(((SortPersonCommand) other).criteria)); // state check
    }
}
