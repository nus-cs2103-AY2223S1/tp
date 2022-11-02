package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;

import java.util.Comparator;

import seedu.studmap.model.Model;
import seedu.studmap.model.order.Order;
import seedu.studmap.model.student.Student;

/**
 * Sorts the student map by specified attribute in specified order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the list "
            + "by the attribute specified. \n "
            + "Parameters: ORDER(asc or dsc) "
            + "[" + PREFIX_ATTRIBUTE + "ATTRIBUTE]\n "
            + "Example: " + COMMAND_WORD + " asc "
            + PREFIX_ATTRIBUTE + "name\n"
            + "Supported attributes: name, module, phone, id, git, handle, email, attendance";

    public static final String MESSAGE_SORT_SUCCESS = "StudMap has been sorted by %1$s in %2$s order!";

    private final Comparator<Student> comparator;
    private final Order sortOrder;
    private final String attributeType;

    /**
     * Sorts the working list by the comparator in ascending or descending order.
     *
     * @param comparator The comparator to sort the list by
     * @param sortOrder Order to sort the list in
     */
    public SortCommand(Comparator<Student> comparator, String attributeType, Order sortOrder) {
        this.comparator = comparator;
        this.attributeType = attributeType;
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredStudentList(comparator, sortOrder);
        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS, attributeType, sortOrder.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attributeType.equals(((SortCommand) other).attributeType) // state check
                && sortOrder.equals(((SortCommand) other).sortOrder)); // state check
    }
}
