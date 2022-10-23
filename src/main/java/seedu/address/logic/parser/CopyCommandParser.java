package seedu.address.logic.parser;

import seedu.address.commons.core.index.*;
import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.*;

import java.util.*;

import static seedu.address.commons.core.Messages.*;

/**
 * Parses input arguments and creates a new CopyCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {

        Index index;
        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE), pe);
        }

        return new CopyCommand(index);
    }
}

