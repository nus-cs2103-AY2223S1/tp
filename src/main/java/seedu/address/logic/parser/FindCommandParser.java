package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DetailsContainKeywordsPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_STATUS, PREFIX_NOTE);

        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_STATUS, PREFIX_NOTE) && argMultimap.getPreamble().isEmpty()) {
            Set<Name> nameList = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
            Set<Phone> phoneList = ParserUtil.parsePhones(argMultimap.getAllValues(PREFIX_PHONE));
            Set<Email> emailList = ParserUtil.parseEmails(argMultimap.getAllValues(PREFIX_EMAIL));
            Set<Address> addressList = ParserUtil.parseAddresses(argMultimap.getAllValues(PREFIX_ADDRESS));
            Set<Status> statusList = ParserUtil.parseStatuses(argMultimap.getAllValues(PREFIX_STATUS));
            Set<Note> noteList = ParserUtil.parseNotes(argMultimap.getAllValues(PREFIX_NOTE));
            return new FindCommand(new DetailsContainKeywordsPredicate(nameList,
                    phoneList, emailList, addressList, tagList, statusList, noteList));

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
