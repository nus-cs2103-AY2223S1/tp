package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import seedu.clinkedin.logic.commands.FindCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.DetailsContainKeywordsPredicate;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Status;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());

        if (arePrefixesPresent(argMultimap, CliSyntax.getPrefixes()) && argMultimap.getPreamble().isEmpty()) {
            Set<Name> nameList = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
            Set<Phone> phoneList = ParserUtil.parsePhones(argMultimap.getAllValues(PREFIX_PHONE));
            Set<Email> emailList = ParserUtil.parseEmails(argMultimap.getAllValues(PREFIX_EMAIL));
            Set<Address> addressList = ParserUtil.parseAddresses(argMultimap.getAllValues(PREFIX_ADDRESS));
            Set<Status> statusList = ParserUtil.parseStatuses(argMultimap.getAllValues(PREFIX_STATUS));
            Set<Note> noteList = ParserUtil.parseNotes(argMultimap.getAllValues(PREFIX_NOTE));
            Map<Prefix, List<String>> prefToStrings = new HashMap<>();
            CliSyntax.getPrefixTags().stream().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));
            return new FindCommand(new DetailsContainKeywordsPredicate(nameList,
                    phoneList, emailList, addressList, statusList, noteList, prefToStrings));

        }

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] detailsKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new DetailsContainKeywordsPredicate(Arrays.asList(detailsKeywords)));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
