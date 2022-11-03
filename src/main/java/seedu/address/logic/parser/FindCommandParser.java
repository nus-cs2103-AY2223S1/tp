package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES_PER_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.student.predicate.ClassContainsDatePredicate;
import seedu.address.model.student.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.student.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicate.NokPhoneContainsNumberPredicate;
import seedu.address.model.student.predicate.PhoneContainsNumberPredicate;
import seedu.address.model.student.predicate.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_NOK_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_CLASS_DATE_TIME, PREFIX_MONEY_OWED, PREFIX_MONEY_PAID,
                PREFIX_RATES_PER_CLASS, PREFIX_ADDITIONAL_NOTES, PREFIX_TAG);

        if (argMultimap.containsMultiplePrefix()) {
            throw new ParseException(FindCommand.ONLY_ONE_PREFIX_MESSAGE);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameToFind = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.trim();
            String[] nameKeywords = nameToFind.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneToFind = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).toString();
            return new FindCommand(new PhoneContainsNumberPredicate(phoneToFind));

        } else if (argMultimap.getValue(PREFIX_NOK_PHONE).isPresent()) {
            String nokPhoneToFind = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_NOK_PHONE).get()).toString();
            return new FindCommand(new NokPhoneContainsNumberPredicate(nokPhoneToFind));

        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String emailToFind = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()).value.trim();
            String[] emailKeywords = emailToFind.split("\\s+");
            return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String addressToFind = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).value.trim();
            String[] addressKeywords = addressToFind.split("\\s+");
            return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        } else if (argMultimap.getValue(PREFIX_CLASS_DATE_TIME).isPresent()) {
            String dateToFind =
                    ParserUtil.parseDateToFind(argMultimap.getValue(PREFIX_CLASS_DATE_TIME).get()).toString();
            return new FindCommand(new ClassContainsDatePredicate(dateToFind));

        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
//            String tagToFind = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()).tagName.trim();
//            String[] tagKeywords = tagToFind.split("\\s+");
            return new FindCommand(new TagContainsKeywordsPredicate(tags));
        } else {
            // Other prefixes that are not supported by the search system, or no prefix found.
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
