package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STATUS;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.DeleteCommand;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.StudentId;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        return new DeleteCommand(studentId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
