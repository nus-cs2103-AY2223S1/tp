package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.AllTagCommissionCommand;
import seedu.address.logic.commands.AnyTagCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commission.CommissionContainsAllTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AllTagCommissionCommand object
 */
public class AllTagCommissionCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AnyTagCommissionCommand
     * and returns a AnyTagCommissionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AllTagCommissionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        if (tagSet.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnyTagCustomerCommand.MESSAGE_USAGE));
        }

        List<Tag> tags = new ArrayList<>();
        tags.addAll(tagSet);
        return new AllTagCommissionCommand(new CommissionContainsAllTagPredicate(tags));
    }
}
