package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_INCOME, PREFIX_MEETING_DATE, PREFIX_TAG, PREFIX_RISK, PREFIX_PLAN);

        if (argMultimap.getOnlyPrefixPresent().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Prefix prefix = argMultimap.getOnlyPrefixPresent().get();

        List<String> keywords =
            argMultimap.getAllValues(prefix).stream().flatMap(keyword -> Arrays.stream(keyword.split("\\s+")))
                .collect(Collectors.toList());

        switch (prefix.toString()) {
        case "n/":
            return new FindCommand(new NameContainsKeywordsPredicate(keywords));
        case "t/":
            return new FindCommand(new TagsContainsKeywordsPredicate(keywords));
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
