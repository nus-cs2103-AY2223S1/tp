package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.AlwaysTruePredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new FindCommand(new AlwaysTruePredicate());
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_NAME).get();
            String[] nameKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_PHONE).get();
            String[] phoneKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_ADDRESS).get();
            String[] addressKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_EMAIL).get();
            String[] emailKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String noPrefixArgs = argMultimap.getValue(PREFIX_TAG).get();
            String[] tagKeywords = noPrefixArgs.split("\\s+");
            return new FindCommand(
                    new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}

