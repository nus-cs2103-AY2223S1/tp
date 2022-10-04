package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.taassist.logic.commands.ClassCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Parses input arguments and creates a new ClassCommand object.
 */
public class ClassCommandParser implements Parser<ClassCommand> {

    @Override
    public ClassCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassCommand.MESSAGE_USAGE));
        }
        return new ClassCommand(new ModuleClass(trimmedArgs));
    }
}
