package seedu.codeconnect.logic.parser;

import static seedu.codeconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;
import java.util.stream.Stream;

import seedu.codeconnect.logic.commands.AddContactCommand;
import seedu.codeconnect.logic.parser.exceptions.ParseException;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.person.Address;
import seedu.codeconnect.model.person.Email;
import seedu.codeconnect.model.person.Github;
import seedu.codeconnect.model.person.Name;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.person.Phone;
import seedu.codeconnect.model.person.Telegram;
import seedu.codeconnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddContactCommandParser implements Parser<AddContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_MODULE, PREFIX_GITHUB, PREFIX_TELEGRAM);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValueOptional(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValueOptional(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Module> moduleList = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_MODULE));
        Github gitHubProfile = ParserUtil.parseGithub(argMultimap.getValueOptional(PREFIX_GITHUB).get());
        Telegram telegramUsername = ParserUtil.parseTelegram(argMultimap.getValueOptional(PREFIX_TELEGRAM).get());

        Person person = new Person(name, phone, email, address, tagList, moduleList, gitHubProfile, telegramUsername);

        return new AddContactCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
