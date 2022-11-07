package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import nus.climods.logic.commands.ViewCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;

/**
 * Parses input arguments and returns a ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    private static final String DEFAULT_ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);
    @Override
    public ViewCommand parse(String userInput) throws ParseException {
        ModuleCodeParameter mcp = new ModuleCodeParameter(userInput);
        return new ViewCommand(mcp.getArgValue());
    }
}
