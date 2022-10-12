package nus.climods.logic.parser;


import nus.climods.logic.commands.LsCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.FacultyCodeParameter;
import nus.climods.logic.parser.parameters.UserFlagParameter;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<LsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand and returns a ListCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        return new LsCommand(new FacultyCodeParameter(trimmedArgs), new UserFlagParameter(trimmedArgs));
    }

}
