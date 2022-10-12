package bookface.logic.parser.add;

import static bookface.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookface.logic.parser.CliSyntax.PREFIX_NAME;
import static bookface.logic.parser.CliSyntax.PREFIX_PHONE;
import static bookface.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import bookface.commons.core.Messages;
import bookface.logic.commands.add.AddUserCommand;
import bookface.logic.parser.ArgumentMultimap;
import bookface.logic.parser.ArgumentTokenizer;
import bookface.logic.parser.ArgumentsParsable;
import bookface.logic.parser.ParserUtil;
import bookface.logic.parser.Prefix;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.book.Title;
import bookface.model.person.Email;
import bookface.model.person.Name;
import bookface.model.person.Person;
import bookface.model.person.Phone;
import bookface.model.tag.Tag;

/**
 * Parses input arguments and creates the relevant new AddCommand object for the relevant entity to be added
 */
public class AddUserArgumentsParser implements ArgumentsParsable<AddUserCommand> {

    @Override
    public AddUserCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        //todo might be better to rewrite these checks using Optional.isPresent and Optional.orElseThrow
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddUserCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, new HashSet<Title>(), tagList);
        return new AddUserCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
