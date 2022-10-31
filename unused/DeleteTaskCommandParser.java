package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.DeleteTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

// @@author mohamedsaf1

/**
 * Parses input arguments and creates a new RmTaskCommand object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    private static final String NUMBER = "\\s*[\\-+]?[0-9]+\\s*";

    @Override
    public DeleteTaskCommand parse(String args) throws ParseException {
        if (args.trim().equals("")) {
            return new DeleteTaskCommand(null);
        } else if (!args.matches(NUMBER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(args);
        return new DeleteTaskCommand(index);
    }
}
