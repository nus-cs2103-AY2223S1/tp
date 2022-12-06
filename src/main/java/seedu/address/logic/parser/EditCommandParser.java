package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMARK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditRemarkCommand;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditClientCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODE, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_GOODS, PREFIX_QUANTITY, PREFIX_PRICE, PREFIX_DATE);

        String mode = argMultimap.getValue(PREFIX_MODE).orElse("").split(" ", 2)[0];

        switch (mode) {
        case "client":
            return parseEditClientCommand(argMultimap);
        case "transaction":
            return parseEditTransactionCommand(argMultimap);
        case "remark":
            return parseEditRemarkCommand(args);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditClientCommand
     * and returns an EditClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    protected EditClientCommand parseEditClientCommand(ArgumentMultimap argMultimap)
            throws ParseException {

        StringBuilder warningMessage = new StringBuilder();
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX, pe);
        }

        EditClientCommand.EditClientDescriptor editClientDescriptor = new EditClientCommand.EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editClientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            ClientPhone phone = ParserUtil.parseClientPhone(argMultimap.getValue(PREFIX_PHONE).get());
            editClientDescriptor.setPhone(phone);
            if (phone.hasWarning()) {
                warningMessage.append(ClientPhone.WARNING + "\n");
            }
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            ClientEmail email = ParserUtil.parseClientEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            editClientDescriptor.setEmail(email);
            if (email.hasWarning()) {
                warningMessage.append(ClientEmail.WARNING + "\n");
            }
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editClientDescriptor::setTags);

        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditClientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditClientCommand(index, editClientDescriptor, warningMessage.toString());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditTransactionCommand
     * and returns an EditTransactionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    protected EditTransactionCommand parseEditTransactionCommand(ArgumentMultimap argMultimap)
            throws ParseException {

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX, pe);
        }

        EditTransactionCommand.EditTransactionDescriptor editTransactionDescriptor =
                new EditTransactionCommand.EditTransactionDescriptor();
        if (argMultimap.getValue(PREFIX_GOODS).isPresent()) {
            editTransactionDescriptor.setGoods(ParserUtil.parseGoods(argMultimap.getValue(PREFIX_GOODS).get()));
        }
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editTransactionDescriptor.setQuantity(ParserUtil.parseQuantity(
                    argMultimap.getValue(PREFIX_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editTransactionDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editTransactionDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (!editTransactionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTransactionCommand.MESSAGE_NOT_EDITED);
        }
        return new EditTransactionCommand(index, editTransactionDescriptor);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditRemarkCommand
     * and returns an EditRemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditRemarkCommand parseEditRemarkCommand(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODE, PREFIX_TAG);

        String [] arguments = args.split(" m/remark ");

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_REMARK_DISPLAYED_INDEX, pe);
        }

        if (arguments.length < 2 || arguments[1].isEmpty()) {
            throw new ParseException(EditRemarkCommand.MESSAGE_NOT_EDITED);
        }

        Text text = ParserUtil.parseText(arguments[1]);

        Remark remark = new Remark(text);

        return new EditRemarkCommand(index, remark);
    }

}
