package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.address.model.Model;
import seedu.address.model.event.EventSortField;

/**
 * Lists all events in the address book to the user.
 */
public class ListEventsCommand extends Command {

    public static final String COMMAND_WORD = "listEvents";

    // MESSAGE_USAGE is currently unused
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all events in the address book."
            + "Parameters: " + "[" + PREFIX_SORT + "FIELD] (must be n, N, d or D)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT + "n";

    public static final String MESSAGE_SUCCESS = "Listed all events sorted by %s";


    private final EventSortField sortField;


    /**
     * Creates a {@code ListEventsCommand} to list and sort all events.
     *
     * @param sortField field to sort by.
     */
    public ListEventsCommand(EventSortField sortField) {
        requireNonNull(sortField);
        this.sortField = sortField;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortEvents(sortField);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, sortField.getField()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListEventsCommand // instanceof handles nulls
            && sortField.equals(((ListEventsCommand) other).sortField));
    }
}
