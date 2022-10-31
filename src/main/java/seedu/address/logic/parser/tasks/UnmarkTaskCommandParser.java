package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.UnmarkTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

// @@author connlim
/**
 * Parses input arguments and creates a new UnmarkTaskCommand object
 */
public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {

    @Override
    public UnmarkTaskCommand parse(String args) throws ParseException {
        if (args.trim().equals("")) {
            return new UnmarkTaskCommand(null);
        }

        Index index = ParserUtil.parseIndex(args);
        return new UnmarkTaskCommand(index);
    }
}
