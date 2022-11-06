package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.logic.commands.StatusCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Status;


/**
 * Parses input arguments and creates a new {@code StatusCommand} object
 */
public class StatusCommandParser implements Parser<StatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code StatusCommand}
     * and returns a {@code StatusCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitCommand = args.split("\\s+");

        Index index;

        if (splitCommand.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(splitCommand[1]);
            assert index.getOneBased() > 0 : "index should be a positive unsigned integer";
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE), ive);
        }

        switch (splitCommand[2].toUpperCase()) {
        case "O":
            return new StatusCommand(index, new Status("Offered"));
        case "P":
            return new StatusCommand(index, new Status("Progress"));
        case "R":
            return new StatusCommand(index, new Status("Rejected"));
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StatusCommand.STATUS_COMMAND_CONSTRAINTS));
        }
    }
}
