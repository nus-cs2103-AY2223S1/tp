package friday.logic.commands;

import static friday.logic.parser.CliSyntax.ORDER_ASCENDING;
import static friday.logic.parser.CliSyntax.ORDER_DESCENDING;
import static friday.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static friday.logic.parser.CliSyntax.PREFIX_FINALS;
import static friday.logic.parser.CliSyntax.PREFIX_MASTERYCHECK;
import static friday.logic.parser.CliSyntax.PREFIX_MIDTERM;
import static friday.logic.parser.CliSyntax.PREFIX_NAME;
import static friday.logic.parser.CliSyntax.PREFIX_PRACTICAL;
import static friday.logic.parser.CliSyntax.PREFIX_RA1;
import static friday.logic.parser.CliSyntax.PREFIX_RA2;
import static friday.logic.parser.CliSyntax.PREFIX_TELEGRAMHANDLE;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import friday.model.Model;
import friday.model.student.Student;

/**
 * Sorts students in FRIDAY.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts students in FRIDAY.\n"
            + "Parameters: CONDITION/ORDER, where CONDITION is "
            + PREFIX_NAME.getPrefixWithoutSlash() + ", "
            + PREFIX_TELEGRAMHANDLE.getPrefixWithoutSlash() + ", "
            + PREFIX_CONSULTATION.getPrefixWithoutSlash() + ", "
            + PREFIX_MASTERYCHECK.getPrefixWithoutSlash() + ", "
            + PREFIX_RA1.getPrefixWithoutSlash() + ", "
            + PREFIX_RA2.getPrefixWithoutSlash() + ", "
            + PREFIX_MIDTERM.getPrefixWithoutSlash() + ", "
            + PREFIX_PRACTICAL.getPrefixWithoutSlash() + ", or "
            + PREFIX_FINALS.getPrefixWithoutSlash()
            + "; ORDER is "
            + ORDER_ASCENDING + " or "
            + ORDER_DESCENDING + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + ORDER_ASCENDING;

    public static final String MESSAGE_TOO_MANY_CRITERIA =
            "Too many sorting criteria. Only one criterion should be provided";

    public static final String MESSAGE_SUCCESS = "Sorted all students";

    private final Comparator<Student> comparator;

    /**
     * @param comparator To compare the students with.
     */
    public SortCommand(Comparator<Student> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedStudentList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator));
    }
}
