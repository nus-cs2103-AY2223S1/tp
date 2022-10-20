package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.RandomlyAssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddTaskCommand object.
 */
public class RandomlyAssignTaskCommandParser implements Parser<RandomlyAssignTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RandomlyAssignTaskCommand
     * and returns a RandomlyAssignTaskCommand object for execution.
     *
     * @param args user input
     * @return RandomlyAssignTaskCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public RandomlyAssignTaskCommand parse(String args) throws ParseException {
        try {
            String[] indexes = args.split(" ", 2);
            if (indexes.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AssignTaskCommand.MESSAGE_USAGE));
            }
            int task = Integer.parseInt(indexes[1]) - 1;
            return new RandomlyAssignTaskCommand(task);
        } catch (NumberFormatException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
