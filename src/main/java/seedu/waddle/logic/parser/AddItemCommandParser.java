package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.stream.Stream;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.item.Item;

/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class AddItemCommandParser implements Parser<AddItemCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }

        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Item item = new Item(description);

        return new AddItemCommand(item);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
