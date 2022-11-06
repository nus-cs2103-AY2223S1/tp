package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_MOBILE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.ParserUtil.parseEmail;
import static seedu.address.logic.parser.ParserUtil.parseEmailValidity;
import static seedu.address.logic.parser.ParserUtil.parseIndexValidity;
import static seedu.address.logic.parser.ParserUtil.parseMobile;
import static seedu.address.logic.parser.ParserUtil.parseMobileValidity;
import static seedu.address.logic.parser.ParserUtil.parseName;
import static seedu.address.logic.parser.ParserUtil.parseNameValidity;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.client.ClientCommand;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.client.FindClientCommand;
import seedu.address.logic.commands.client.ListClientCommand;
import seedu.address.logic.commands.client.PinClientCommand;
import seedu.address.logic.commands.client.SetClientDefaultViewCommand;
import seedu.address.logic.commands.client.SortClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.predicates.ClientContainsKeywordsPredicate;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.client.ClientWithoutModel;
import seedu.address.model.project.ProjectId;

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
        case SetClientDefaultViewCommand.COMMAND_FLAG:
            return parseSetClientDefaultViewCommand(arguments);
        case SortClientCommand.COMMAND_FLAG:
            return parseSortClientCommand(arguments);
        case FindClientCommand.COMMAND_FLAG:
            return parseFindClientCommand(arguments);
        case PinClientCommand.COMMAND_FLAG:
            return parsePinClientCommand(arguments);
        default:
            throw new ParseException(FLAG_UNKNOWN_COMMAND);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Verifies only one valid user input argument
     * Length of a valid command for sort key for issue by name e.g.n/1
     *
     * @param arguments user input for key for sort
     * @return true if there is only one valid input
     */
    private boolean hasOneArgumentOfLengthThree(String arguments) {
        return arguments.trim().length() == 3;
    }

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
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_MOBILE,
                        PREFIX_EMAIL, PREFIX_PROJECT_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PROJECT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddClientCommand.MESSAGE_ADD_CLIENT_USAGE));
        }

        Name name = parseName(argMultimap.getValue(PREFIX_NAME).get());


        ClientMobile mobile = ClientMobile.EmptyClientMobile.EMPTY_MOBILE;
        if (arePrefixesPresent(argMultimap, PREFIX_MOBILE)) {
            mobile = ParserUtil.parseMobile(argMultimap.getValue(PREFIX_MOBILE).get());
        }

        ClientEmail email = ClientEmail.EmptyEmail.EMPTY_EMAIL;
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            email = parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(name, mobile, email,
                new ArrayList<>(), new Pin(false));

        //
        ProjectId projectId =
                ParserUtil.parseProjectId(argMultimap.getFirstWordValue(PREFIX_PROJECT_ID).get());

        return new AddClientCommand(clientWithoutModel, projectId);
    }

    /**
     * Parse a string of arguments for an edit client command
     * From original AB3 code
     *
     * @param arguments a string of arguments
     * @return an EditClientCommand object
     * @throws ParseException
     */
    private EditClientCommand parseEditClientCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_CLIENT_ID, PREFIX_NAME,
                        PREFIX_EMAIL, PREFIX_MOBILE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLIENT_ID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditClientCommand.MESSAGE_USAGE));
        }
        Name newName = null;
        ClientEmail newEmail = null;
        ClientMobile newMobile = null;
        ClientId newClientId = ParserUtil.parseClientId(argMultimap.getFirstWordValue(PREFIX_CLIENT_ID).get());

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_MOBILE)) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS,
                    EditClientCommand.MESSAGE_USAGE));
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_NAME)) {
            newName = parseName(argMultimap.getValue(PREFIX_NAME).get());
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_MOBILE)) {
            newMobile = parseMobile(argMultimap.getValue(PREFIX_MOBILE).get());
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            newEmail = parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        return new EditClientCommand(newClientId, newName, newEmail, newMobile);
    }



    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * From original AB3 code
     *
     * @param arguments string of arguments
     * @return return statement
     * @throws ParseException if the user input does not conform the expected format
     */
    private DeleteClientCommand parseDeleteClientCommand(String arguments) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(arguments);
            return new DeleteClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE), pe);
        }
    }

    private ListClientCommand parseListClientCommand(String args) throws ParseException {
        return new ListClientCommand();
    }

    private ClientCommand parseSetClientDefaultViewCommand(String arguments) {
        return new SetClientDefaultViewCommand();
    }
    private FindClientCommand parseFindClientCommand(String arguments) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_EMAIL, PREFIX_MOBILE, PREFIX_CLIENT_ID);

        if (noPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_MOBILE, PREFIX_CLIENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindClientCommand.MESSAGE_FIND_CLIENT_USAGE));
        }

        //check for validity of arguments

        if (anyPrefixesPresent(argMultimap, PREFIX_NAME)) {
            parseNameValidity(argMultimap.getValue(PREFIX_NAME).get());
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_MOBILE)) {
            parseMobileValidity(argMultimap.getValue(PREFIX_MOBILE).get());
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            parseEmailValidity(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_CLIENT_ID)) {
            parseIndexValidity(argMultimap.getFirstWordValue(PREFIX_CLIENT_ID).get());
        }

        ClientContainsKeywordsPredicate predicate =
                new ClientContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_NAME),
                        argMultimap.getAllValues(PREFIX_EMAIL),
                        argMultimap.getAllValues(PREFIX_MOBILE),
                        argMultimap.getAllFirstWordValues(PREFIX_CLIENT_ID));

        return new FindClientCommand(predicate);
    }

    public FindClientCommand parseFindClientCommands(String flag, String arguments) throws ParseException {
        return parseFindClientCommand(arguments);
    }

    private SortClientCommand parseSortClientCommand(String arguments) throws ParseException {

        Prefix sortPrefix = null;
        int key = -1;

        if (!hasOneArgumentOfLengthThree(arguments)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortClientCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_CLIENT_ID, PREFIX_NAME);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CLIENT_ID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortClientCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_CLIENT_ID)) {
            sortPrefix = PREFIX_CLIENT_ID;
            key = ParserUtil.parseClientNameSort(argMultimap.getFirstWordValue(PREFIX_CLIENT_ID).get());
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            sortPrefix = PREFIX_NAME;
            key = ParserUtil.parseClientIdSort(argMultimap.getValue(PREFIX_NAME).get());
        }

        return new SortClientCommand(sortPrefix, key);
    }

    private PinClientCommand parsePinClientCommand(String arguments) throws ParseException {
        try {
            ClientId pinnedClientId = ParserUtil.parseClientId(arguments);
            return new PinClientCommand(pinnedClientId);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinClientCommand.MESSAGE_USAGE), e);
        }

    }

    /**
     * Returns true if there are no prefixes present in the given {@code ArgumentMultimap}.
     */
    private static boolean noPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
