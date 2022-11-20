package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.module.ViewTargetModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewTargetModuleCommand object
 */
public class ViewTargetModuleCommandParser implements Parser<ViewTargetModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewTargetModuleCommand
     * and returns a ViewTargetModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTargetModuleCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewTargetModuleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTargetModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}

