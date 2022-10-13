package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddTaskCommand object.
 */
public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns a AddTaskCommand object for execution.
     *
     * @param args user input
     * @return AssignTaskCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTaskCommand parse(String args) throws ParseException {
        try {
            String[] indexes = args.split(" ", 3);
            if (indexes.length < 3) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AssignTaskCommand.MESSAGE_USAGE));
            }
            int task = Integer.parseInt(indexes[1]) - 1;
            String member = indexes[2];
            return new AssignTaskCommand(task, member);
        } catch (NumberFormatException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
