package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;

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
        String attribute;

        try {
//            order = ParserUtil.parseIndex(argMultimap.getPreamble());
            order = ParserUtil.parseOrder(argMultimap.getPreamble());
            attribute = argMultimap.getValue(PREFIX_ATTRIBUTE).get();
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE), pe);
        }

//        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
//        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
//            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
//        }
//        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
//            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
//        }
//        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
//            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
//        }
//        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
//            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
//        }
//        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
//
//        if (!editPersonDescriptor.isAnyFieldEdited()) {
//            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
//        }

        return new SortCommand(attribute, order);
    }

}
