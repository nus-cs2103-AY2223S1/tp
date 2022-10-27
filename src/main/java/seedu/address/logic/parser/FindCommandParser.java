package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindAddressCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.FindPhoneCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.tag.TagIsSecuredPredicate;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG);

        if (!isOnlyOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = "";
        boolean isFindByName = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isFindByAddress = argMultimap.getValue(PREFIX_ADDRESS).isPresent();
        boolean isFindByPhone = argMultimap.getValue(PREFIX_PHONE).isPresent();
        boolean isFindByTag = argMultimap.getValue(PREFIX_TAG).isPresent();

        if (isFindByName) {
            trimmedArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
        } else if (isFindByAddress) {
            trimmedArgs = argMultimap.getValue(PREFIX_ADDRESS).get().trim();
        } else if (isFindByPhone) {
            trimmedArgs = argMultimap.getValue(PREFIX_PHONE).get().trim();
        } else if (isFindByTag) {
            trimmedArgs = argMultimap.getValue(PREFIX_TAG).get().trim();
        }

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        return isFindByName ? new FindNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)))
                : isFindByAddress
                ? new FindAddressCommand(new AddressContainsKeywordsPredicate(Arrays.asList(nameKeywords)))
                : isFindByPhone
                ? new FindPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(nameKeywords)))
                : new FindTagCommand(
                        new TagIsSecuredPredicate(ParserUtil.parseTag(nameKeywords[nameKeywords.length - 1])));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isOnlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() == 1;
    }

}
