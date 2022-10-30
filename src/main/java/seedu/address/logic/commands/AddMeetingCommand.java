package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;


/**
 * Adds a Meeting to the address book.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addM";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the address book. "
            + "Parameters: "
            + PREFIX_LISTING_ID + "LISTING ADDRESS "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATETIME + "Date Time (yyyy-MM-dd HH:mm) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LISTING_ID + "30_SERGARDENS_LOR23_0718 "
            + PREFIX_NAME + "Bob "
            + PREFIX_DATETIME + "2022-12-14 12:00";

    public static final String MESSAGE_SUCCESS = "New Meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists!";

    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }
        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddMeetingCommand
                && toAdd.equals(((AddMeetingCommand) other).toAdd));
    }
}
