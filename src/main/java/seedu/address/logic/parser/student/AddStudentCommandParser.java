package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Set;

import seedu.address.logic.commands.student.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.tutorial.TutorialParserUtil;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL,
                        PREFIX_ATTENDANCE, PREFIX_PARTICIPATION, PREFIX_GRADE, PREFIX_TAG);

        ParserUtil.assertAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = StudentParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        StudentId id = StudentParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Phone phone = StudentParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = StudentParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        TelegramHandle telegramHandle = StudentParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        TutorialModule tutorialModule = TutorialParserUtil.parseTutorialModule(
                argMultimap.getValue(PREFIX_MODULE).get());
        TutorialName tutorialName = TutorialParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get());

        //optional fields
        Grade grade = StudentParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).orElse("PENDING..."));
        Attendance attendance = StudentParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).orElse("0"));
        Participation participation = StudentParserUtil.parseParticipation(argMultimap
                .getValue(PREFIX_PARTICIPATION).orElse("0"));
        Set<Tag> tagList = StudentParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(name, id, phone, email, telegramHandle,
                tutorialModule, tutorialName, attendance, participation, grade, tagList);

        return new AddCommand(student);
    }


}
