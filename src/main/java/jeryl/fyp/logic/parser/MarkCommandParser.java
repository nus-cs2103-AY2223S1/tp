package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STATUS;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.stream.Stream;

import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.StudentId;

/**
 * Parses input arguments and creates a new {@code MarkCommand} object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        ProjectStatus projectStatus = ParserUtil.parseProjectStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new MarkCommand(studentId, projectStatus);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
