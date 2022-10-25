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
        switch (type) {
        case NAME:
            if (order.equals(ORDER.ASC)) {
                this.comparator = Person::compareToByNameAsc;
            } else {
                this.comparator = Person::compareToByNameDesc;
            }
            break;
        default:
            // default sorting is to sort by Name Asc
            this.comparator = Person::compareToByNameAsc;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(this.comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
