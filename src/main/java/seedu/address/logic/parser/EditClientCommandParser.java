package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Birthday;
import seedu.address.model.product.Product;

/**
 * Parses input arguments and creates a new EditClientCommand object
 */
public class EditClientCommandParser implements Parser<EditClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditClientCommand
     * and returns an EditClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_BIRTHDAY, PREFIX_PRODUCT);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editClientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(Optional.of(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get())));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editClientDescriptor.setAddress(Optional.of(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get())));
        }

        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            LocalDate dateLocalDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_BIRTHDAY).get(), "birthday");
            Birthday birthday = new Birthday(dateLocalDate);
            editClientDescriptor.setBirthday(Optional.of(birthday));
        }

        parseProductsForEdit(argMultimap.getAllValues(PREFIX_PRODUCT)).ifPresent(editClientDescriptor::setProducts);

        return new EditClientCommand(index, editClientDescriptor);
    }

    /**
     * Parses {@code Collection<String> Products} into a {@code Set<Product>} if {@code Products} is non-empty.
     * If {@code Products} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Product>} containing zero Products.
     */
    private Optional<Set<Product>> parseProductsForEdit(Collection<String> products) throws ParseException {
        assert products != null;

        if (products.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> productSet = products.size() == 1 && products.contains("")
                ? Collections.emptySet()
                : products;
        return Optional.of(ParserUtil.parseProducts(productSet));
    }

}
