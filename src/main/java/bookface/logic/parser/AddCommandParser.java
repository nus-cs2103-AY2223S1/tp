package bookface.logic.parser;

import static bookface.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookface.logic.parser.CliSyntax.PREFIX_NAME;
import static bookface.logic.parser.CliSyntax.PREFIX_PHONE;
import static bookface.logic.parser.CliSyntax.PREFIX_TAG;
import static bookface.logic.parser.CliSyntax.PREFIX_USER;

import java.util.Set;
import java.util.stream.Stream;

import bookface.commons.core.Messages;
import bookface.logic.commands.AddUserCommand;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.person.Email;
import bookface.model.person.Name;
import bookface.model.person.Person;
import bookface.model.person.Phone;
import bookface.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddUserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddUserCommand parse(String args) throws ParseException {

        String trimmedString = args.trim();
        String[] stringArray = trimmedString.split("\\s+");
        String keyWord = stringArray[0];

        if (stringArray.length < 2) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddUserCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_USER, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_USER, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddUserCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, tagList);

        if (keyWord.equals("user")) {
            return new AddUserCommand(person);
        }
        throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddUserCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
