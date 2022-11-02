package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the NUScheduler.
 */
public class AddEventCommand extends EventCommand {

    public static final String COMMAND_OPTION = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Adds an event to NUScheduler."
            + "Parameters: "
            + PREFIX_NAME + "TITLE "
            + PREFIX_START_DATE + "START "
            + PREFIX_END_DATE + "END "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " "
            + PREFIX_NAME + "Formal Dinner "
            + PREFIX_START_DATE + "20/04/2022 08:00 "
            + PREFIX_END_DATE + "20/04/2022 10:00 "
            + PREFIX_TAG + "RC "
            + PREFIX_TAG + "Evening";

    public static final String MESSAGE_SUCCESS = "New event added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the NUScheduler";
    public static final String MESSAGE_HELP = "Adds an event to NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " "
            + PREFIX_NAME + "TITLE "
            + PREFIX_START_DATE + "START "
            + PREFIX_END_DATE + "END "
            + "[" + PREFIX_TAG + "TAG]...";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
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

        if (!toAdd.isHasTimeEqual()) {
            throw new CommandException(Messages.MESSAGE_EVENTS_HAS_TIME);
        }

        if (!toAdd.isValidStartEnd()) {
            throw new CommandException(Messages.MESSAGE_EVENTS_INVALID_START_END);
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
