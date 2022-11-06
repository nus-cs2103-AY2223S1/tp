package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.order.Order;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortPersonListDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_NAME);

        Order order;
        try {
            order = ParserUtil.parseOrder(argumentMultimap.getPreamble());
        } catch (IllegalArgumentException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
        SortPersonListDescriptor sortPersonListDescriptor = new SortPersonListDescriptor();
        if (argumentMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            sortPersonListDescriptor.setModuleCode(true);
        }
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            sortPersonListDescriptor.setName(true);
        }
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()
                && argumentMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MULTIPLE_SELECTED));
        }
        if (!sortPersonListDescriptor.isAnyFieldSelected()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_NOT_SELECTED));
        }
        return new SortCommand(order, sortPersonListDescriptor);
    }
}
