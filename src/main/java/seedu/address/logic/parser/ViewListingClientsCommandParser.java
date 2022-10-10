package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddListingCommand;
import seedu.address.logic.commands.ViewListingClientsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class ViewListingClientsCommandParser implements Parser<ViewListingClientsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewListingClientsCommand
     * and returns an ViewListingClientsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewListingClientsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddListingCommand.MESSAGE_USAGE));
        }

        String id = argMultimap.getValue(PREFIX_ID).get();

        return new ViewListingClientsCommand(id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


