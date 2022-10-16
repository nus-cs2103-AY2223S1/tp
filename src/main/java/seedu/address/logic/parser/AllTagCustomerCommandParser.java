package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.AllTagCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.CustomerContainsAllTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class AllTagCustomerCommandParser implements Parser<AllTagCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AllTagCustomerCommand
     * and returns a AllTagCustomerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AllTagCustomerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllTagCustomerCommand.MESSAGE_USAGE));
        }

        String[] tagsAsString = trimmedArgs.split("\\s+");
        Tag[] tags = new Tag[tagsAsString.length];
        for (int i = 0; i < tags.length; i++) {
            tags[i] = new Tag(tagsAsString[i]);
        }
        return new AllTagCustomerCommand(new CustomerContainsAllTagPredicate(Arrays.asList(tags)));
    }

}
