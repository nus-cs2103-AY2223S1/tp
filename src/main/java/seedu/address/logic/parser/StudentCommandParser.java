package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.StudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new StudentCommand object
 */
public class StudentCommandParser implements Parser<StudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StudentCommand
     * and returns an StudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GENDER, PREFIX_TAG,
                    PREFIX_LOCATION, PREFIX_GITHUBUSERNAME, PREFIX_YEAR, PREFIX_MODULE_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GENDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Set<ModuleCode> moduleCodes = ParserUtil.parseModuleCodes(argMultimap.getAllValues(PREFIX_MODULE_CODE));
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(DEFAULT_LOC_STRING));
        GithubUsername username = ParserUtil.parseGitHubUsername(argMultimap
                .getValue(PREFIX_GITHUBUSERNAME)
                .orElse(GithubUsername.DEFAULT_USERNAME), argMultimap.getValue(PREFIX_GITHUBUSERNAME).isPresent());
        Year year = ParserUtil.parseYear(argMultimap
                .getValue(PREFIX_YEAR)
                .orElse(Year.EMPTY_YEAR), argMultimap.getValue(PREFIX_YEAR).isPresent());

        Student person = new Student(name, phone, email, gender, tagList, location, username, moduleCodes, year);

        return new StudentCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
