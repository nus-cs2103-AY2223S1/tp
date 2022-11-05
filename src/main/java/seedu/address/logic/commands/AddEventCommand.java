package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;


/**
 * Adds an Event to the application.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an Event to the application. \n"
            + "Parameters: "
            + PREFIX_EVENT_TITLE + "TITLE OF EVENT "
            + PREFIX_START_DATE + "START DATE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_PURPOSE + "PURPOSE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_TITLE + "Shoe Sale "
            + PREFIX_START_DATE + "20/09/2022 "
            + PREFIX_START_TIME + "11:00 "
            + PREFIX_PURPOSE + "50 dollar discount on all shoes store";

    public static final String MESSAGE_SUCCESS = "New Event added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the application";

    private final Event toAdd;

    /**
     * Constructor for AddEventCommand
     * @param event to be wrapped by AddEventCommand object
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
