package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Comparator;

/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons with the given order";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the persons by the given argument type and order. "
            + "Parameters: ARGUMENT ORDER\n"
            + "ARGUMENT can be either 'NAME', 'CLASS' and 'OWED'\nORDER can be either 'ASC' OR 'DESC'\n"
            + "Example: " + COMMAND_WORD + " NAME ASC";
    public static final String MESSAGE_UNKNOWN_ORDER_KEYWORD =
            "The order of Sort Command must be 'ASC' or 'DESC' or left empty.";
    public static final String MESSAGE_UNKNOWN_TYPE_KEYWORD =
            "The type of Sort Command must be from 'NAME', 'CLASS; or 'OWED'";
    public static enum TYPE {NAME};
    public static enum ORDER {ASC, DESC};

    private final Comparator<Person> comparator;

    public SortCommand(TYPE type, ORDER order) {
        this.comparator = generateComparator(type, order);
    }

    public static Comparator<Person> generateComparator(TYPE type, ORDER order) {
        switch (type) {
        case NAME:
            if (order.equals(ORDER.ASC)) {
                return Person::compareToByNameAsc;
            } else {
                return Person::compareToByNameDesc;
            }
        default:
            // default sorting is to sort by Name Asc
            return Person::compareToByNameAsc;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersons(this.comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return this == other // short circuit if same object
                || (other instanceof SortCommand // instanceof handles null
        && this.comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
