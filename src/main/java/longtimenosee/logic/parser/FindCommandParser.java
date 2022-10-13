package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import longtimenosee.logic.commands.FindCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.person.predicate.AddressMatchesInputPredicate;
import longtimenosee.model.person.predicate.EmailMatchesInputPredicate;
import longtimenosee.model.person.predicate.NameContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.PhoneMatchesNumberPredicate;
import longtimenosee.model.person.predicate.TagContainsKeywordsPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL,
                        PREFIX_TAG);

        if (!isAtLeastOnePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL,
                PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()
                || moreThanOnePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL,
                PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).value;
            return new FindCommand(new PhoneMatchesNumberPredicate(phoneNumber));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).value;
            return new FindCommand(new AddressMatchesInputPredicate(address));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()).value;
            return new FindCommand(new EmailMatchesInputPredicate(email));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String trimmedArgs = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()).tagName.trim();
            String[] tagKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Checks if there is at least one of the specified prefixes is present in the argument multimap.
     *
     * @param argumentMultimap contains the tokenized arguments
     * @param prefixes to be checked
     * @return boolean to indicate if any prefix is present
     */
    boolean isAtLeastOnePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is more than one of the specified prefixes present in the argument multimap.
     *
     * @param argumentMultimap contains the tokenized arguments
     * @param prefixes to be checked
     * @return boolean to indicate if more than one prefix is present
     */
    boolean moreThanOnePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        int count = 0;
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                count += 1;
            }
        }
        return count > 1;
    }

}
