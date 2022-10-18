package tuthub.logic.commands;

import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.Prefix;
import tuthub.model.Model;
import tuthub.model.Tuthub;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.model.tutor.Tutor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.model.Model.PREDICATE_SHOW_ALL_TUTORS;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the tutor list based on quantitative measures"
            + "in ascending or descending order. "
            + "Parameters: "
            + "[a/d]"
            + "[" + PREFIX_TEACHINGNOMINATION + "/" + PREFIX_RATING + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + "a " + PREFIX_TEACHINGNOMINATION;

    public static final Integer NEGATIVE_MULTIPLIER = -1;

    public static final String MESSAGE_SORT_TUTOR_SUCCESS = "Sorted based on %1$s, in %2$s order.";
    public static final String MESSAGE_UNKNOWN_ORDER = "Order %1$s is not valid for this command. "
            + "Valid orders are a or d.";

    public static final String ASCENDING_SHORT = "a";
    public static final String DESCENDING_SHORT = "d";
    public static final String ASCENDING_WORD = "ascending";
    public static final String DESCENDING_WORD = "descending";
    public static final String RATING_WORD = "RATINGS";
    public static final String TEACHING_NOMINATION_WORD = "TEACHING NOMINATIONS";

    private final String order;
    private final Prefix prefix;
    private final Comparator<Tutor> comparator;

    /**
     * Creates a SortCommand to sort the tutor list.
     */
    public SortCommand(String order, Prefix prefix, Comparator<Tutor> comparator) {
        this.order = order;
        this.prefix = prefix;
        this.comparator = comparator;
    }

    public String getOrderWord(String order) {
        return order.equals(ASCENDING_SHORT)
            ? ASCENDING_WORD
            : order.equals(DESCENDING_SHORT)
                ? DESCENDING_WORD
                : null;
    }

    public String getCategoryWord(Prefix prefix) {
        return prefix.equals(PREFIX_RATING)
            ? RATING_WORD
            : TEACHING_NOMINATION_WORD;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String orderWord = getOrderWord(order);

        if (orderWord == null) {
            throw new CommandException(String.format(MESSAGE_UNKNOWN_ORDER, order));
        }

        String categoryWord = getCategoryWord(prefix);

        List<Tutor> tutorsList = model.getTuthub().getTutorList();
        List<Tutor> sortedTutors = new ArrayList<>();
        sortedTutors.addAll(tutorsList);
        sortedTutors.sort(comparator);

        Tuthub sortedTuthub = new Tuthub();
        sortedTuthub.setTutors(sortedTutors);
        model.setTuthub(sortedTuthub);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);

        return new CommandResult(String.format(MESSAGE_SORT_TUTOR_SUCCESS, categoryWord, orderWord));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && order.equals(((SortCommand) other).order)
                && prefix.equals(((SortCommand) other).prefix)
                && comparator.equals(((SortCommand) other).comparator));
    }
}
