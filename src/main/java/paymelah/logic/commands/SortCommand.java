package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Comparator;

import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.person.Person;

/**
 * Sorts persons in the address book by their names or amount owed.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts persons with given criteria and order. "
            + "Use the symbol + to indicate ascending order and - to indicate descending order. "
            + "Parameters: "
            + "[" + PREFIX_MONEY + "<order>] OR "
            + "[" + PREFIX_NAME + "<order>]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MONEY + "-";

    public static final String MESSAGE_SUCCESS = "Sorted persons by %1$s in %2$s.";

    private final Comparator<Person> comparator;
    private final String criteria;
    private final String order;

    /**
     * Creates a SortCommand using the given comparator, and the criteria and order used.
     */
    public SortCommand(Comparator<Person> comparator, String criteria, String order) {
        this.comparator = comparator;
        this.criteria = criteria;
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortAddressBookPersonList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, criteria, order));
    }

}
