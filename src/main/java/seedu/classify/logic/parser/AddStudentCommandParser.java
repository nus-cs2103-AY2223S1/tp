package seedu.classify.logic.parser;

import static seedu.classify.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.AddStudentCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_ID, PREFIX_CLASS,
                        PREFIX_PARENT_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_EXAM);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_NAME, PREFIX_ID, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddStudentCommand.MESSAGE_USAGE));
        }

        Name studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_STUDENT_NAME).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Class className = ParserUtil.parseClass(argMultimap.getValue(PREFIX_CLASS).get());

        Phone phone = new Phone();
        Name parentName = new Name();
        Email email = new Email();
        if (!argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (!argMultimap.getValue(PREFIX_PARENT_NAME).isEmpty()) {
            parentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_PARENT_NAME).get());
        }
        if (!argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        Set<Exam> examSet = ParserUtil.parseExams(argMultimap.getAllValues(PREFIX_EXAM));

        Student person = new Student(studentName, id, className, parentName, phone, email, examSet);

        return new AddStudentCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
