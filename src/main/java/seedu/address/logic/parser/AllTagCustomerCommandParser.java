package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.AllTagCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.CustomerContainsAllTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AllTagCustomerCommand object
 */
public class AllTagCustomerCommandParser implements Parser<AllTagCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AllTagCustomerCommand
     * and returns a AllTagCustomerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AllTagCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Set<Tag> tagSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        List<Tag> tags = new ArrayList<>();
        for (Tag tag : tagSet) {
            tags.add(tag);
        }
        return new AllTagCustomerCommand(new CustomerContainsAllTagPredicate(tags));
    }
}
