package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStuCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;

/**
 * Parses input arguments and creates a new AddStuCommand object
 */
public class AddStuCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStuCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStuCommand.MESSAGE_USAGE));
        }

        StuName name = ParserUtil.parseStuName(argMultimap.getValue(PREFIX_NAME).get());
        Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        StuEmail email = ParserUtil.parseStuEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Response response = new Response("0");
        Attendance attendance = new Attendance("0");

        Student student = new Student(name, telegram, email, response, attendance);

        return new AddStuCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
