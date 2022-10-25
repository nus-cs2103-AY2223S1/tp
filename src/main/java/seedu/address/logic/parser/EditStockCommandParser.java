package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.StringUtil.getIntFromString;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTSTOCK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStockCommand;
import seedu.address.logic.commands.EditStockCommand.EditStockDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditStockCommand object
 */
public class EditStockCommandParser implements Parser<EditStockCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditStockCommand
     * and returns an EditStockCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStockCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CURRENTSTOCK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditStockCommand.MESSAGE_USAGE), pe);
        }

        EditStockDescriptor editStockDescriptor = new EditStockDescriptor();
        Integer newCurrentStock = -1;
        if (argMultimap.getValue(PREFIX_CURRENTSTOCK).isPresent()) {

            try {
                newCurrentStock = getIntFromString(argMultimap.getValue(PREFIX_CURRENTSTOCK).get());
            } catch (NumberFormatException nfe) {
                throw new ParseException(EditStockCommand.MESSAGE_NOT_EDITED_PREFIX_DETECTED);
            }
        }

        if (newCurrentStock == -1) {
            throw new ParseException(EditStockCommand.MESSAGE_NOT_EDITED);
        }

        if (newCurrentStock < 0) {
            throw new ParseException(EditStockCommand.MESSAGE_COUNT_NEGATIVE);
        }

        editStockDescriptor.setCurrentStock(newCurrentStock);
        if (!editStockDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStockCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStockCommand(index, editStockDescriptor);
    }
}
