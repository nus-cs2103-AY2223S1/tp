package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import seedu.clinkedin.logic.commands.AddCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Status;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (!CliSyntax.getUniquePrefixes().stream().allMatch(pref -> argMultimap.getAllValues(pref).size() == 1)) {
            throw new ParseException("Name, Phone, Email, Address can't have multiple values.");
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Map<Prefix, List<String>> prefToStrings = new HashMap<>();
        CliSyntax.getPrefixTags().stream().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));
        UniqueTagTypeMap tagMap = ParserUtil.parseTags(prefToStrings);
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).orElse(""));

        Person person = new Person(name, phone, email, address, tagMap, status, note);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
