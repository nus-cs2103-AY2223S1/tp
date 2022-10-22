package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_DEADLINE_DATETIME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_DEADLINE_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import jeryl.fyp.logic.commands.AddDeadlineCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.DeadlineName;
import jeryl.fyp.model.student.StudentId;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddDeadlineCommandParser implements Parser<AddDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDeadlineCommand
     * and returns an AddDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeadlineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_DEADLINE_NAME, PREFIX_DEADLINE_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_DEADLINE_NAME, PREFIX_DEADLINE_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        DeadlineName deadlineName = ParserUtil.parseDeadlineName(argMultimap.getValue(PREFIX_DEADLINE_NAME).get());
        LocalDateTime deadlineDatetime = ParserUtil.parseDeadlineDatetime(argMultimap.getValue(
                PREFIX_DEADLINE_DATETIME).get());
        Deadline deadline = new Deadline(deadlineName, deadlineDatetime);
        return new AddDeadlineCommand(deadline, studentId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

