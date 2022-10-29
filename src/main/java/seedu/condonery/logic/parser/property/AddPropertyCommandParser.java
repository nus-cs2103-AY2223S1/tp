package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_IMAGE_UPLOAD;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_INTERESTEDCLIENTS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.property.AddPropertyCommand;
import seedu.condonery.logic.parser.ArgumentMultimap;
import seedu.condonery.logic.parser.ArgumentTokenizer;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.Prefix;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPropertyCommand object
 */
public class AddPropertyCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PRICE, PREFIX_TAG,
                        PREFIX_IMAGE_UPLOAD, PREFIX_INTERESTEDCLIENTS, PREFIX_PROPERTY_TYPE, PREFIX_PROPERTY_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PRICE, PREFIX_PROPERTY_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Client> interestedClientList = ParserUtil.parseClients(argMultimap.getAllValues(PREFIX_INTERESTEDCLIENTS));
        PropertyTypeEnum propertyTypeEnum =
                ParserUtil.parsePropertyType(argMultimap.getValue(PREFIX_PROPERTY_TYPE).get());
        PropertyStatusEnum propertyStatusEnum =
                ParserUtil.parsePropertyStatus(argMultimap.getValue(PREFIX_PROPERTY_STATUS)
                        .orElse(PropertyStatusEnum.AVAILABLE.name()));

        Property property = new Property(name, address, price, tagList, interestedClientList,
                propertyTypeEnum, propertyStatusEnum);


        if (argMultimap.getValue(PREFIX_IMAGE_UPLOAD).isPresent()) {
            return new AddPropertyCommand(property, true);
        }

        return new AddPropertyCommand(property);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
