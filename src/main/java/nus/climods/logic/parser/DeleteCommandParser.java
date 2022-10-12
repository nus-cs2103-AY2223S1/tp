package nus.climods.logic.parser;

import nus.climods.logic.commands.DeleteCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.model.module.UserModule;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand and returns a DeleteCommand
     * object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ModuleCodeParameter mcp = new ModuleCodeParameter(args);
        String mc = mcp.getArgValue();
        return new DeleteCommand(mc);
    }
}
