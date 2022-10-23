package seedu.address.logic.parser;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTSTOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINIMUMSTOCK;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class AddItemCommandParser implements Parser<AddItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CURRENTSTOCK, PREFIX_MINIMUMSTOCK);
        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_CURRENTSTOCK, PREFIX_MINIMUMSTOCK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE), e);
        }

        int currentStock = parseInt(argMultimap.getValue(PREFIX_CURRENTSTOCK).get());
        int minimumstock = parseInt(argMultimap.getValue(PREFIX_MINIMUMSTOCK).get());

        if (currentStock <= 0 || minimumstock <= 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        return new AddItemCommand(index, currentStock, minimumstock);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
