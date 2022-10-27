package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.ParserUtil.parseModuleClass;

import seedu.taassist.logic.commands.FocusCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Parses input arguments and creates a new FocusCommand object.
 */
public class FocusCommandParser implements Parser<FocusCommand> {

    @Override
    public FocusCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FocusCommand.COMMAND_WORD,
                    FocusCommand.MESSAGE_USAGE));
        }
        ModuleClass moduleClass = parseModuleClass(trimmedArgs);
        return new FocusCommand(moduleClass);
    }
}
