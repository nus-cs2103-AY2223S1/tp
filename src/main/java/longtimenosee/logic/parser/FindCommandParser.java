package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import longtimenosee.logic.commands.FindCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.person.Person;
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
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Person>> predicates = new ArrayList<Predicate<Person>>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).value;
            predicates.add(new PhoneMatchesNumberPredicate(phoneNumber));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).value;
            predicates.add(new AddressMatchesInputPredicate(address));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()).value;
            predicates.add(new EmailMatchesInputPredicate(email));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String trimmedArgs = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()).tagName.trim();
            String[] tagKeywords = trimmedArgs.split("\\s+");
            predicates.add(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }

        return new FindCommand(predicates);
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
}
