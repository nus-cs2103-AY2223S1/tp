package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;

import seedu.studmap.logic.commands.SortCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.attribute.Attribute;
import seedu.studmap.model.order.Order;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ATTRIBUTE);

        Order order;
        Attribute attribute;

        try {
            order = ParserUtil.parseOrder(argMultimap.getPreamble());
            attribute = new Attribute(argMultimap.getValue(PREFIX_ATTRIBUTE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }
        return new SortCommand(attribute, order);
    }

}
