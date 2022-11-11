package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.logic.commands.SortCommand;
import seedu.intrack.logic.commands.SortSalaryCommand;
import seedu.intrack.logic.commands.SortTimeCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new {@code SortCommand} object
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SortCommand}
     * and returns a {@code SortCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitCommand = args.split("\\s+");

        if (splitCommand.length != 3) { //test this later
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String sortType = splitCommand[1];
        String orderType = splitCommand[2].toLowerCase();

        switch (sortType.toUpperCase()) {
        case "TIME":
            return new SortTimeCommand(orderType);
        case "SALARY":
            return new SortSalaryCommand(orderType);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortCommand.MESSAGE_USAGE));
        }
    }
}

