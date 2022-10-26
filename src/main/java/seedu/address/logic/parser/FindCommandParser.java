package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NormalTagContainsKeywordsPredicate;
import seedu.address.model.person.PlanTagContainsKeywordsPredicate;
import seedu.address.model.person.RiskTagContainsKeywordsPredicate;
import seedu.address.model.tag.RiskTag;

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
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // 2 because all except PREFIX_PLANTAG has a prefix of length 2
        String tokenizedArgs = trimmedArgs.substring(2);
        String[] keywords = tokenizedArgs.split("\\s+");

        if (trimmedArgs.startsWith(PREFIX_RISKTAG.getPrefix())) {
            checkIfRiskTag(keywords);
            return new FindCommand(new RiskTagContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else if (trimmedArgs.startsWith(PREFIX_TAG.getPrefix())) {
            return new FindCommand(new NormalTagContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else if (trimmedArgs.startsWith(PREFIX_NAME.getPrefix())) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else if (trimmedArgs.startsWith(PREFIX_PLANTAG.getPrefix())) {
            String planTag = trimmedArgs.substring(3);
            // since all planTag has a space and ends with Plan, we split the input every second space.
            // planTag - Savings Plan.
            String[] tags = planTag.split("(?<=\\s\\S{1,100})\\s");
            return new FindCommand(new PlanTagContainsKeywordsPredicate(Arrays.asList(tags)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Please enter a prefix before the KEYWORDs."));
        }
    }

    private void checkIfRiskTag(String[] tags) throws ParseException {
        for (String tag : tags) {
            // if tag is not a valid Risk tag
            if (!RiskTag.isRiskTag(tag)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RiskTag.MESSAGE_CONSTRAINTS));
            }
        }
    }


}
