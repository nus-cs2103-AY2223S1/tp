package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.addcommands.AddOrderCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;

/**
 * Parses the argument to create an AddOrderCommand object.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput The string argument.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public AddOrderCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddOrderCommand.MESSAGE_USAGE_EXISTING_BUYER));
        }

        String indexStr = argMultimap.getValue(PREFIX_INDEX).orElse("").split(" ")[0];
        Index index = ParserUtil.parseIndex(indexStr);
        Order order = ParserUtil.parseOrder(userInput, true);

        return new AddOrderCommand(order, index);
    }
}
