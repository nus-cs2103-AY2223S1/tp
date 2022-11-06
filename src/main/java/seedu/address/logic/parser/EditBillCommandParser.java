package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBillCommand;
import seedu.address.logic.commands.EditBillCommand.EditBillDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditBillCommand object
 */
public class EditBillCommandParser implements Parser<EditBillCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditBillCommand
     * and returns an EditBillCommand object for execution.
     *
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     *                                                              the expected format
     */
    public EditBillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_BILL_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditBillCommand.MESSAGE_USAGE), pe);
        }

        EditBillDescriptor editBillDescriptor = createEditBillDescriptor(argMultimap);

        return new EditBillCommand(index, editBillDescriptor);
    }

    private EditBillDescriptor createEditBillDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditBillDescriptor editBillDescriptor = new EditBillCommand.EditBillDescriptor();
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editBillDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_BILL_DATE).isPresent()) {
            editBillDescriptor.setBillDate(ParserUtil.parseBillDate(argMultimap.getValue(PREFIX_BILL_DATE).get()));
        }
        if (!editBillDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBillCommand.MESSAGE_NOT_EDITED);
        }
        return editBillDescriptor;
    }
}
