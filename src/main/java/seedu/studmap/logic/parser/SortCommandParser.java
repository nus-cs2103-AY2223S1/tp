package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;

import java.util.Comparator;

import seedu.studmap.logic.commands.SortCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.attribute.Attribute;
import seedu.studmap.model.attribute.AttributeType;
import seedu.studmap.model.order.Order;
import seedu.studmap.model.student.Student;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ATTRIBUTE);

        Order order;
        Comparator<Student> comparator;
        String attributeType;

        try {
            requireNonNull(argMultimap.getPreamble());
            order = ParserUtil.parseOrder(argMultimap.getPreamble());
            requireNonNull(PREFIX_ATTRIBUTE);
            if (argMultimap.getValue(PREFIX_ATTRIBUTE).isPresent()) {
                attributeType = argMultimap.getValue(PREFIX_ATTRIBUTE).get();
            } else {
                throw new ParseException("Caused by: No attribute specified\n");
            }
            if (Attribute.isValidAttributeType(attributeType)) {
                AttributeType attributeTypeEnum = AttributeType.valueOf(attributeType.toUpperCase());
                comparator = Attribute.getAttributeComparator(attributeTypeEnum, order);
            } else {
                throw new ParseException("Caused by: Invalid attribute\n");
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    pe.getMessage() + SortCommand.MESSAGE_USAGE), pe);
        }
        return new SortCommand(comparator, attributeType, order);
    }

}
