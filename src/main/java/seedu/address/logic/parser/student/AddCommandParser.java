package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;

import seedu.address.logic.commands.student.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL,
                        PREFIX_ATTENDANCE, PREFIX_PARTICIPATION, PREFIX_GRADE, PREFIX_TAG);

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MODULE, PREFIX_TUTORIAL);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ID id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        TutorialModule tutorialModule = ParserUtil.parseTutorialModule(argMultimap.getValue(PREFIX_MODULE).get());
        TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIAL).get());

        //optional fields
        Grade grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).orElse(""));
        Attendance attendance = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_GRADE).orElse("0"));
        Participation participation = ParserUtil.parseParticipation(argMultimap
                .getValue(PREFIX_GRADE).orElse("0"));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(name, id, phone, email, telegram,
                tutorialModule, tutorialName, attendance, participation, grade, tagList);

        return new AddCommand(student);
    }


}
