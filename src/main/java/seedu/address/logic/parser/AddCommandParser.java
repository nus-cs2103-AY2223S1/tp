package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_DAY, PREFIX_TIME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION, PREFIX_RELATIONSHIP);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.FEEDBACK_MESSAGE));
        }

        if (areAnyPrefixesPresent(argMultimap, PREFIX_RELATIONSHIP)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.FEEDBACK_MESSAGE));
        }

        AddCommand.Entity entity = ParserUtil.parseEntity(argMultimap.getPreamble());

        switch (entity) {
        case STUDENT:
            Student student = extractFromMapForStudent(argMultimap);
            return AddCommand.of(student);

        case TUTOR:
            Tutor tutor = extractFromMapForTutor(argMultimap);
            return AddCommand.of(tutor);

        case CLASS:
            TuitionClass tuitionClass = extractFromMapForClass(argMultimap);
            return AddCommand.of(tuitionClass);

        default:
            return null;
        }
    }

    private Student extractFromMapForStudent(ArgumentMultimap argMultimap) throws ParseException {
        if ((!areAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL))
                || (areAnyPrefixesPresent(argMultimap, PREFIX_QUALIFICATION, PREFIX_INSTITUTION,
                PREFIX_DAY, PREFIX_TIME))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.FEEDBACK_MESSAGE));
        }
        seedu.address.model.person.Name name = ParserUtil.parsePersonName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        School school = ParserUtil.parseSchool(argMultimap.getValue(PREFIX_SUBJECT_OR_SCHOOL).get());
        Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Student(name, phone, email, address, tagList, school, level, null);
    }

    private Tutor extractFromMapForTutor(ArgumentMultimap argMultimap) throws ParseException {
        if ((!areAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_QUALIFICATION, PREFIX_INSTITUTION))
                || (areAnyPrefixesPresent(argMultimap, PREFIX_LEVEL, PREFIX_SUBJECT_OR_SCHOOL,
                PREFIX_DAY, PREFIX_TIME))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.FEEDBACK_MESSAGE));
        }
        seedu.address.model.person.Name name = ParserUtil.parsePersonName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Qualification qualification = ParserUtil.parseQualification(argMultimap.getValue(PREFIX_QUALIFICATION).get());
        Institution institution = ParserUtil.parseInstitution(argMultimap.getValue(PREFIX_INSTITUTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Tutor(name, phone, email, address, tagList, qualification, institution);
    }

    private TuitionClass extractFromMapForClass(ArgumentMultimap argMultimap) throws ParseException {
        if ((!areAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL, PREFIX_DAY,
                PREFIX_TIME))
                || (areAnyPrefixesPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_QUALIFICATION, PREFIX_INSTITUTION))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.FEEDBACK_MESSAGE));
        }
        seedu.address.model.tuitionclass.Name name = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT_OR_SCHOOL).get());
        Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        List<Student> students = new ArrayList<>();
        List<Tutor> tutors = new ArrayList<>();

        return new TuitionClass(name, subject, level, day, time, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
