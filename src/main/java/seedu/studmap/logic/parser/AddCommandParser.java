package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_GIT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.studmap.logic.commands.AddCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ID, PREFIX_GIT, PREFIX_HANDLE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ID, PREFIX_GIT, PREFIX_HANDLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        StudentData studentData = new StudentData();

        studentData.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        studentData.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        studentData.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        studentData.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        studentData.setGitUser(ParserUtil.parseGitName(argMultimap.getValue(PREFIX_GIT).get()));
        studentData.setTeleHandle(ParserUtil.parseHandle(argMultimap.getValue(PREFIX_HANDLE).get()));
        studentData.setTags(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)));

        Student student = new Student(studentData);

        return new AddCommand(student);
    }

}
