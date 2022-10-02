package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    @Override
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        List<Index> indexList;
        int expectedIndexCount = 2;

        try {
            indexList = ParserUtil.parseIndexes(args, expectedIndexCount);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), pe);
        }

        Index indexOfPerson = indexList.get(0);
        Index indexOfAppointment = indexList.get(1);
        return new MarkCommand(indexOfPerson, indexOfAppointment);
    }
}
