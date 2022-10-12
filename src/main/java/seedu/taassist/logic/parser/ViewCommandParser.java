package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.ViewCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    @Override
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(index);
    }
}
