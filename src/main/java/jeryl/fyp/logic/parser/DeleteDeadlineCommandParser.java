package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_DEADLINE_RANK;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.stream.Stream;

import jeryl.fyp.logic.commands.DeleteDeadlineCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.StudentId;

/**
 * Parses input arguments and creates a new DeleteDeadlineCommand object
 */
public class DeleteDeadlineCommandParser implements Parser<DeleteDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDeadlineCommand
     * and returns a DeleteDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDeadlineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_DEADLINE_RANK);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_DEADLINE_RANK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteDeadlineCommand.MESSAGE_USAGE));
        }
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        Integer rank = ParserUtil.parseRank(argMultimap.getValue(PREFIX_DEADLINE_RANK).get());
        return new DeleteDeadlineCommand(studentId, rank);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
