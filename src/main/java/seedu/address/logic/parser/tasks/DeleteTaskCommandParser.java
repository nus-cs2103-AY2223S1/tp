package seedu.address.logic.parser.tasks;

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

    @Override
    public DeleteTaskCommand parse(String args) throws ParseException {
        if (args.trim().equals("")) {
            return new DeleteTaskCommand(null);
        } else {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTaskCommand(index);
        }
    }
}
