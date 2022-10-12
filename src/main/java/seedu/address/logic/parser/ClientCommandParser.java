package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;

import java.util.stream.Stream;

import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.client.ClientCommand;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.client.ListClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse any commands related to Client
 */
public class ClientCommandParser implements Parser<ClientCommand> {
    /**
     * Method to parse any commands that have to do with client (start with 'client)
     *
     * @param flag      the flag used in the command
     * @param arguments arguments used in the command
     * @return a ClientCommand
     * @throws ParseException
     */
    @Override
    public ClientCommand parse(String flag, String arguments) throws ParseException {
        // Strip is just there for good measure.
        switch (flag.strip()) {
        case AddClientCommand.COMMAND_FLAG:
            return parseAddClientCommand(arguments);
        case EditClientCommand.COMMAND_FLAG:
            return parseEditClientCommand(arguments);
        case DeleteClientCommand.COMMAND_FLAG:
            return parseDeleteClientCommand(arguments);
        case ListClientCommand.COMMAND_FLAG:
            return parseListClientCommand(arguments);
        default:
            throw new ParseException(FLAG_UNKNOWN_COMMAND);
        }
    }

    // TODO: revise syntax

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args string of arguments
     * @return an AddClientCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    private AddClientCommand parseAddClientCommand(String args) throws ParseException {
        return null;
    }

    // TODO: revise syntax

    /**
     * Parse a string of arguments for an edit client command
     * From original AB3 code
     *
     * @param args a string of arguments
     * @return an EditClientCommand object
     * @throws ParseException
     */
    private EditClientCommand parseEditClientCommand(String args) throws ParseException {
        return null;
    }

    // TODO: revise syntax

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * From original AB3 code
     *
     * @param args string of arguments
     * @return return statement
     * @throws ParseException if the user input does not conform the expected format
     */
    private DeleteClientCommand parseDeleteClientCommand(String args) throws ParseException {
        return null;
    }

    private ListClientCommand parseListClientCommand(String args) throws ParseException {
        return new ListClientCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
