package soconnect.logic.parser.customise;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.customise.CustomiseCommand.MESSAGE_DUPLICATE_ATTRIBUTE;
import static soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.logic.parser.customise.CustomiseCommandParser.convertToAttributeList;
import static soconnect.logic.parser.customise.CustomiseCommandParser.hasDuplicateAttributes;
import static soconnect.logic.parser.customise.CustomiseCommandParser.hasNonEmptyArgument;

import java.util.List;

import soconnect.logic.commands.customise.CustomiseOrderCommand;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomiseOrderCommand object.
 */
public class CustomiseOrderCommandParser implements Parser<CustomiseOrderCommand> {

    /**
     * Parses the given String of arguments in the context of the CustomiseOrderCommand
     * and returns a CustomiseOrderCommand object for execution.
     *
     * @param args Remaining user input string.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public CustomiseOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);

        List<PrefixArgument> argList =
                tokenizeToList(args, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_TAG);
        boolean hasArguments = argList.size() != 1;

        if (!hasArguments || hasNonEmptyArgument(argList)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CustomiseOrderCommand.MESSAGE_USAGE));
        }

        List<Attribute> attributeList = convertToAttributeList(argList.subList(1, argList.size()));

        if (hasDuplicateAttributes(attributeList)) {
            throw new ParseException(MESSAGE_DUPLICATE_ATTRIBUTE);
        }

        fillMissingAttributes(attributeList);

        return new CustomiseOrderCommand(attributeList);
    }

    /**
     * Fills the missing attributes in default order.
     *
     * @param attributeList The List of attributes.
     */
    private void fillMissingAttributes(List<Attribute> attributeList) {
        if (!attributeList.contains(TAGS)) {
            attributeList.add(TAGS);
        }

        if (!attributeList.contains(PHONE)) {
            attributeList.add(PHONE);
        }

        if (!attributeList.contains(EMAIL)) {
            attributeList.add(EMAIL);
        }

        if (!attributeList.contains(ADDRESS)) {
            attributeList.add(ADDRESS);
        }
    }
}
