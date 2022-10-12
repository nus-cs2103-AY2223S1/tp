package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_PHONE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_PROJECT_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.client.ClientCommand;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.client.ListClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.project.Project;

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
     * @param arguments string of arguments
     * @return an AddClientCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    private AddClientCommand parseAddClientCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE,
                        PREFIX_CLIENT_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddClientCommand.MESSAGE_ADD_CLIENT_USAGE));
        }

        List<Project> projects = new ArrayList<>();

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_ID)) {
            //projects already empty
        } else {
            Project project = ParserUtil.parseProject(argMultimap.getValue(PREFIX_PROJECT_ID).get());
            projects.add(project);
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).get());
        ClientPhone phone;
        ClientEmail email;


        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_PHONE)) {
            phone = ClientPhone.EmptyClientPhone.EMPTY_PHONE;
        } else {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_PHONE).get());
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_EMAIL)) {
            email = ClientEmail.EmptyEmail.EMPTY_EMAIL;
        } else {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get());
        }

        //todo: replace with actual implementation
        ClientId clientId = new ClientId(-1);

        Client client = new Client(name, phone, email, projects, clientId);

        return new AddClientCommand(client);
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
