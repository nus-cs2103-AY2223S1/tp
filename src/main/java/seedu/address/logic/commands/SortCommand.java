package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all people in the addressbook by the keyword.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons based on the given KEYWORD"
            + " in ascending or descending order"
            + "(KEYWORD: name, appt, risk, income, monthly, client) \n"
            + "Parameters: [KEYWORD]/ [KEYWORD] desc\n"
            + "Example: " + COMMAND_WORD + " name" + "/" + COMMAND_WORD + " name desc";

    public static final String MESSAGE_SUCCESS = "Sorted all persons by %1$s";

    public static final String MESSAGE_INCORRECT_KEYWORD = " Incorrect keyword! \n%1$s";

    private final Comparator<Person> comparator;
    private final String keyword;

    /**
     * Creates a SortCommand to sort the people by specified {@code keyword}
     */
    public SortCommand(Comparator<Person> comparator, String keyword) {
        this.comparator = comparator;
        this.keyword = keyword;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortPerson(this.comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, keyword));
    }

    @Override
    public boolean equals(Object obj) {
        // if object is the same, short circuit this code
        if (obj == this) {
            return true;
        }

        if (obj instanceof SortCommand) {
            SortCommand s = (SortCommand) obj;
            return this.comparator.equals(s.comparator) && this.keyword.equals(s.keyword);
        } else {
            // handles null
            return false;
        }
    }
}
