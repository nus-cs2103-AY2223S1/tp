package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author connlim
/**
 * Parses input arguments and creates a new MarkTaskCommand object
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {

    @Override
    public MarkTaskCommand parse(String args) throws ParseException {
        try {
            if (args.trim().equals("")) {
                return new MarkTaskCommand(null);
            } else {
                Index index = ParserUtil.parseIndex(args);
                return new MarkTaskCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE), pe);
        }
    }

}
