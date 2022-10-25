package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.MarkPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns the MarkCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_HOMEWORK, PREFIX_ATTENDANCE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }
        MarkPersonDescriptor markPersonDescriptor = new MarkPersonDescriptor();
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            Index homeworkArg = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_HOMEWORK).get());
            markPersonDescriptor.setHomeworkIndex(homeworkArg);
        } else if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            Index attendanceArg = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ATTENDANCE).get());
            markPersonDescriptor.setAttendanceIndex(attendanceArg);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        return new MarkCommand(markPersonDescriptor);

    }
}
