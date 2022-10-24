package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEPROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

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
                        PREFIX_GRADEPROGRESS, PREFIX_ATTENDANCE, PREFIX_SESSION);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        RemovePersonDescriptor removePersonDescriptor = new RemovePersonDescriptor();
        if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            String[] homeworkArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_HOMEWORK).get());
            removePersonDescriptor.setHomeworkIndex(ParserUtil.parseIndex(homeworkArgs[0]));
        }
        if (argMultimap.getValue(PREFIX_GRADEPROGRESS).isPresent()) {
            String[] gradeProgressArgs = ParserUtil
                    .parseIndexedRemove(argMultimap.getValue(PREFIX_GRADEPROGRESS).get());
            removePersonDescriptor.setGradeProgressIndex(ParserUtil.parseIndex(gradeProgressArgs[0]));
        }
        if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            String[] attendanceArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_ATTENDANCE).get());
            removePersonDescriptor.setAttendanceIndex(ParserUtil.parseIndex(attendanceArgs[0]));
        }
        if (argMultimap.getValue(PREFIX_SESSION).isPresent()) {
            String[] sessionArgs = ParserUtil.parseIndexedRemove(argMultimap.getValue(PREFIX_SESSION).get());
            removePersonDescriptor.setSessionIndex(ParserUtil.parseIndex(sessionArgs[0]));
        }

        return new RemoveCommand(removePersonDescriptor);
    }
}
