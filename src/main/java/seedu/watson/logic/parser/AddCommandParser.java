package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.watson.logic.commands.AddCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.Address;
import seedu.watson.model.student.Attendance;
import seedu.watson.model.student.Email;
import seedu.watson.model.student.Name;
import seedu.watson.model.student.Phone;
import seedu.watson.model.student.Remark;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.StudentClass;
import seedu.watson.model.student.subject.Subject;
import seedu.watson.model.student.subject.SubjectHandler;
import seedu.watson.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =

            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                                       PREFIX_EMAIL, PREFIX_TAG, PREFIX_STUDENTCLASS, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE,
                                PREFIX_EMAIL, PREFIX_STUDENTCLASS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        // New fields
        StudentClass studentClass = ParserUtil.parseStudentClass(argMultimap.getValue(PREFIX_STUDENTCLASS).get());
        Attendance attendance = new Attendance(); // Adding a student results in empty attendance initially
        Set<Remark> remarksList = ParserUtil.parseRemarks(argMultimap.getAllValues(PREFIX_REMARK));
        Set<Subject> subjectList = new HashSet<Subject>();
        subjectList.add(new Subject("MATH"));
        subjectList.add(new Subject("Science"));
        SubjectHandler subjectHandler = new SubjectHandler(subjectList);
        Student student =
            new Student(name, phone, email, address, studentClass, attendance, remarksList, subjectHandler,
                        tagList);

        return new AddCommand(student);
    }

}
