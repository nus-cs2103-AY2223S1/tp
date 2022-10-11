package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.SetDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class SetDeadlineCommandParser implements Parser<SetDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns a AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetDeadlineCommand parse(String args) throws ParseException {
        try {
            String[] indexes = args.split(" ", 3);
            if (indexes.length < 3) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        SetDeadlineCommand.MESSAGE_USAGE));
            }
            int task = Integer.parseInt(indexes[1]) - 1;
            LocalDate deadline = LocalDate.parse(indexes[2]);
            return new SetDeadlineCommand(task, deadline);
        } catch (DateTimeParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetDeadlineCommand.MESSAGE_USAGE), pe);
        }
    }
}
