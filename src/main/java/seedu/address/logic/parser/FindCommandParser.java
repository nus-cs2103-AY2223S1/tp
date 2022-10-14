package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_NOK_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_CLASS_DATE_TIME, PREFIX_MONEY_OWED, PREFIX_MONEY_PAID,
                PREFIX_RATES_PER_CLASS, PREFIX_ADDITIONAL_NOTES);

        if (!argMultimap.containsExactlyOnePrefix()) {
            throw new ParseException(FindCommand.ONLY_ONE_PREFIX_MESSAGE);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameToFind = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.trim();
            String[] nameKeywords = nameToFind.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {

            // TODO: Implement phone search here, remove the exception below
            throw new ParseException("p/ search not implemented yet.");

        } else if (argMultimap.getValue(PREFIX_NOK_PHONE).isPresent()) {

            // TODO: Implement NOK phone search here, remove the exception below
            throw new ParseException("np/ search not implemented yet.");

        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {

            // TODO: Implement email search here, remove the exception below
            throw new ParseException("e/ search not implemented yet.");

        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {

            // TODO: Implement address search here, remove the exception below
            throw new ParseException("a/ search not implemented yet.");

        } else {
            // Other prefixes that are not supported by the search system
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
