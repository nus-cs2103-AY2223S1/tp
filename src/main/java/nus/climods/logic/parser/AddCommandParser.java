package nus.climods.logic.parser;

import java.util.stream.Stream;

import nus.climods.logic.commands.AddCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ModuleCodeParameter mcp = new ModuleCodeParameter(args);
        String mc = mcp.getArgValue();
        return new AddCommand(mc);
    }

}
