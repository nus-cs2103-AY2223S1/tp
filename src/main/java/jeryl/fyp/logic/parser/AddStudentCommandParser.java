package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_EMAIL;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_STUDENT_NAME, PREFIX_PROJECT_NAME,
                        PREFIX_EMAIL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_STUDENT_NAME, PREFIX_PROJECT_NAME,
                PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        StudentName studentName = ParserUtil.parseStudentName(argMultimap.getValue(PREFIX_STUDENT_NAME).get());
        ProjectName projectName = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        ProjectStatus projectStatus = new ProjectStatus(); // add command does not allow adding status right away
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(studentName, studentId, email, projectName, projectStatus, tagList);

        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
