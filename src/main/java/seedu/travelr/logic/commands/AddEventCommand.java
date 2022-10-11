package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;


/**
 * Represents the AddEventCommand. Extends the Command class
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to Travelr. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESC + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Honeymoon "
            + PREFIX_DESC + "Going on a honeymoon with my prof."
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This event already exists in Travelr";

    private final Event toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRIP);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
