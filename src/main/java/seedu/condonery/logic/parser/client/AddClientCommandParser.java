package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_IMAGE_UPLOAD;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_INTERESTEDPROPERTIES;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.client.AddClientCommand;
import seedu.condonery.logic.parser.ArgumentMultimap;
import seedu.condonery.logic.parser.ArgumentTokenizer;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.Prefix;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddClientCommand object
 */
public class AddClientCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                    args,
                    PREFIX_NAME,
                    PREFIX_ADDRESS,
                    PREFIX_TAG,
                    PREFIX_IMAGE_UPLOAD,
                    PREFIX_INTERESTEDPROPERTIES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Property> interestedPropertyList = ParserUtil
                .parseProperties(argMultimap.getAllValues(PREFIX_INTERESTEDPROPERTIES));

        Client client = new Client(name, address, tagList, interestedPropertyList);

        if (argMultimap.getValue(PREFIX_IMAGE_UPLOAD).isPresent()) {
            return new AddClientCommand(client, true);
        }

        return new AddClientCommand(client);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
