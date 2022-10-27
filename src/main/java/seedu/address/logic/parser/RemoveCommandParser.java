package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns the RemoveCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_HOMEWORK,
                        PREFIX_GRADE_PROGRESS, PREFIX_ATTENDANCE, PREFIX_SESSION);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        RemovePersonDescriptor removePersonDescriptor = new RemovePersonDescriptor();
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            Index homeworkIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_HOMEWORK).get());
            removePersonDescriptor.setHomeworkIndex(homeworkIndex);
        }
        if (argMultimap.getValue(PREFIX_GRADE_PROGRESS).isPresent()) {
            Index gradeProgressIndex = ParserUtil
                    .parseIndex(argMultimap.getValue(PREFIX_GRADE_PROGRESS).get());
            removePersonDescriptor.setGradeProgressIndex(gradeProgressIndex);
        }
        if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            Index attendanceIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ATTENDANCE).get());
            removePersonDescriptor.setAttendanceIndex(attendanceIndex);
        }
        if (argMultimap.getValue(PREFIX_SESSION).isPresent()) {
            Index sessionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SESSION).get());
            removePersonDescriptor.setSessionIndex(sessionIndex);
        }

        return new RemoveCommand(removePersonDescriptor);
    }
}
