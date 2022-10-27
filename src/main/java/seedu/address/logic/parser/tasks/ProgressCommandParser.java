package seedu.address.logic.parser.tasks;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.logic.commands.tasks.ProgressCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.Progress;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ProgressCommandParser implements Parser<ProgressCommand> {
    @Override
    public ProgressCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            Progress level = ParserUtil.parseProgress(args);
            return new ProgressCommand(index, level);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
