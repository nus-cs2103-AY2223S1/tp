package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Lists all students in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all students with the given order";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the students by the given argument type and order. "
            + "Parameters: ARGUMENT ORDER\n"
            + "ARGUMENT can be either 'NAME', 'CLASS' and 'OWED'\nORDER can be either 'ASC' OR 'DESC'\n"
            + "Example: " + COMMAND_WORD + " NAME ASC";
    public static final String MESSAGE_UNKNOWN_ORDER_KEYWORD =
            "The order of Sort Command should be 'ASC' or 'DESC' or left empty.";
    public static final String MESSAGE_UNKNOWN_TYPE_KEYWORD = "You may sort by 'name', 'class' or 'owed' amount, "
            + "followed by 'asc' or 'desc' order.\n"
            + "Example: sort owed asc";

    /**
     * {@code TYPE} specifies what possible types {@code SortCommand} can accept.
     */
    public static enum Type {
        NAME,
        CLASS,
        OWED
    };

    /**
     * {@code ORDER} specifies what possible order {@code SortCommand} can accept.
     */
    public static enum Order {
        ASC,
        DESC
    };

    private final Comparator<Student> comparator;

    public SortCommand(Type type, Order order) {
        this.comparator = generateComparator(type, order);
    }

    /**
     * Generates a Comparator for Student based on parameters.
     *
     * @param type type of attribute to be compared
     * @param order order of sorting
     */
    public static Comparator<Student> generateComparator(Type type, Order order) {
        switch (type) {
        case NAME:
            if (order.equals(Order.ASC)) {
                return Student::compareToByNameAsc;
            } else {
                return Student::compareToByNameDesc;
            }
        case CLASS:
            if (order.equals(Order.ASC)) {
                return Student::compareToByClassAsc;
            } else {
                return Student::compareToByClassDesc;
            }
        case OWED:
            if (order.equals(Order.ASC)) {
                return Student::compareToByMoneyOwedAsc;
            } else {
                return Student::compareToByMoneyOwedDesc;
            }
        default:
            // default sorting is to sort by Name Asc
            return Student::compareToByNameAsc;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortStudents(this.comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return this == other // short circuit if same object
                || (other instanceof SortCommand // instanceof handles null
                && this.comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
