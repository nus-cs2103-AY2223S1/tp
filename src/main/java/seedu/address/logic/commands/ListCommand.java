package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.SortField;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    // MESSAGE_USAGE is currently unused
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all persons in the address book."
            + "Parameters: " + "[" + PREFIX_SORT + "FIELD] (must be n, N, d, D, g or G)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT + "n";

    public static final String MESSAGE_SUCCESS = "Listed all persons sorted by %s";


    private final SortField sortField;


    /**
     * Creates a ListCommand to list and sort all persons.
     *
     * @param sortField Field to sort by.
     */
    public ListCommand(SortField sortField) {
        requireNonNull(sortField);
        this.sortField = sortField;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersons(sortField);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, sortField.getField()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListCommand // instanceof handles nulls
            && sortField.equals(((ListCommand) other).sortField));
    }
}
