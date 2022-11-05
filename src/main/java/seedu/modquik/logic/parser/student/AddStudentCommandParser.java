package seedu.modquik.logic.parser.student;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.Set;

import seedu.modquik.logic.commands.student.AddStudentCommand;
import seedu.modquik.logic.parser.ArgumentMultimap;
import seedu.modquik.logic.parser.ArgumentTokenizer;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.logic.parser.tutorial.TutorialParserUtil;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.student.Attendance;
import seedu.modquik.model.student.Email;
import seedu.modquik.model.student.Grade;
import seedu.modquik.model.student.Name;
import seedu.modquik.model.student.Participation;
import seedu.modquik.model.student.Phone;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.StudentId;
import seedu.modquik.model.student.TelegramHandle;
import seedu.modquik.model.tag.Tag;
import seedu.modquik.model.tutorial.TutorialName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL,
                        PREFIX_ATTENDANCE, PREFIX_PARTICIPATION, PREFIX_GRADE, PREFIX_TAG);

        ParserUtil.assertAllPrefixesPresent(argMultimap, AddStudentCommand.MESSAGE_USAGE,
                PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        Name name = StudentParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        StudentId id = StudentParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Phone phone = StudentParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = StudentParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        TelegramHandle telegramHandle = StudentParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        ModuleCode moduleCode = ParserUtil.parseModuleCode(
                argMultimap.getValue(PREFIX_MODULE).get());
        TutorialName tutorialName = TutorialParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get());

        //optional fields
        Grade grade = StudentParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).orElse("PENDING..."));
        Attendance attendance = StudentParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).orElse("0"));
        Participation participation = StudentParserUtil.parseParticipation(argMultimap
                .getValue(PREFIX_PARTICIPATION).orElse("0"));
        Set<Tag> tagList = StudentParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(name, id, phone, email, telegramHandle,
                moduleCode, tutorialName, attendance, participation, grade, tagList);

        return new AddStudentCommand(student);
    }


}
