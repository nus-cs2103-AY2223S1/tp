package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import coydir.logic.commands.ViewDepartmentCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.Department;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewDepartmentCommandParser implements Parser<ViewDepartmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewDepartmentCommand
     * and returns a ViewDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewDepartmentCommand parse(String args) throws ParseException {
        try {
            Department department = ParserUtil.parseDepartment(args);
            return new ViewDepartmentCommand(department.toString());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDepartmentCommand.MESSAGE_USAGE), pe);
        }
    }

}
