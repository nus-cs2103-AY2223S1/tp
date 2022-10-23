package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.logic.commands.SortCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new {@code SortCommand} object
 */
public class SortCommandParser implements Parser<SortCommand>{
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SortCommand}
     * and returns a {@code SortCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

        public SortCommand parse(String args) throws ParseException {
            requireNonNull(args);
            String[] splitCommand = args.split("\\s+");

            if (splitCommand.length != 2) { //test this later
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
            String orderType = splitCommand[1];

            switch (orderType.toUpperCase()) {
            case "A":
                return new SortCommand("a");
            case "D":
                return new SortCommand("d");
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SortCommand.SORT_COMMAND_CONSTRAINTS));
            }
        }
}
