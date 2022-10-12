package jeryl.fyp.logic.parser;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STATUS;

import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.commands.MarkCommand.Status;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.StudentId;

/**
 * Parses input arguments and creates a new {@code MarkCommand} object
 */
public class MarkCommandParser implements Parser<MarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code ,arkCommand}
     * and returns a {@code MarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        StudentId studentId;
        try {
            studentId = ParserUtil.parseStudentId(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), ive);
        }

        Status status = argMultimap.getStatus(PREFIX_STATUS);

        return new MarkCommand(studentId, status);
    }
}
