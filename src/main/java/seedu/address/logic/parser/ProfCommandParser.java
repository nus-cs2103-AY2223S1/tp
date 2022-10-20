package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICEHOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ProfCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ProfCommand object
 */
public class ProfCommandParser implements Parser<ProfCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ProfCommand
     * and returns an ProfCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_GENDER, PREFIX_TAG, PREFIX_LOCATION, PREFIX_GITHUBUSERNAME, PREFIX_RATING,
                        PREFIX_SPECIALISATION, PREFIX_OFFICEHOUR);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_GENDER, PREFIX_OFFICEHOUR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(DEFAULT_LOC_STRING));
        GithubUsername username = ParserUtil.parseGitHubUsername(argMultimap
                .getValue(PREFIX_GITHUBUSERNAME)
                .orElse(GithubUsername.DEFAULT_USERNAME), argMultimap.getValue(PREFIX_GITHUBUSERNAME).isPresent());

        Specialisation field = ParserUtil.parseSpecialisation(argMultimap
                .getValue(PREFIX_SPECIALISATION)
                .orElse(Specialisation.EMPTY_SPECIALISATION), argMultimap.getValue(PREFIX_SPECIALISATION).isPresent());

        Rating rating = ParserUtil.parseRating(argMultimap
                .getValue(PREFIX_RATING)
                .orElse(Rating.EMPTY_RATING), argMultimap.getValue(PREFIX_RATING).isPresent());

        OfficeHour officeHour = ParserUtil.parseOfficeHour(argMultimap.getValue(PREFIX_OFFICEHOUR)
                .orElse(OfficeHour.EMPTY_OFFICE_HOUR), argMultimap.getValue(PREFIX_OFFICEHOUR).isPresent());

        Person person = new Professor(name, moduleCode, phone, email, gender, tagList, location, username, rating,
                field, officeHour);

        return new ProfCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
