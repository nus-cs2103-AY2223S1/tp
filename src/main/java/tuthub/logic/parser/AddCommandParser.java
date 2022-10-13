package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;
import java.util.stream.Stream;

import tuthub.logic.commands.AddCommand;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                    PREFIX_MODULE, PREFIX_YEAR, PREFIX_STUDENTID, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_STUDENTID,
            PREFIX_MODULE, PREFIX_YEAR, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Module module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
        Comment comment = new Comment("");
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Tutor tutor = new Tutor(name, phone, email, module, year, studentId, comment, tagList);

        return new AddCommand(tutor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
