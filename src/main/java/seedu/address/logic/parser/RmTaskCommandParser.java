package seedu.address.logic.parser;

import seedu.address.logic.commands.tasks.RmTaskCommand;
import seedu.address.logic.commands.tasks.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;

public class RmTaskCommandParser implements Parser<RmTaskCommand> {

    @Override
    public RmTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RmTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE), pe);
        }
    }

}
