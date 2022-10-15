package soconnect.logic.parser.customise;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute;
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

import soconnect.logic.commands.customise.CustomiseShowCommand;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomiseShowCommand object.
 */
public class CustomiseShowCommandParser implements Parser<CustomiseShowCommand> {

    /**
     * Parses the given String of arguments in the context of the CustomiseShowCommand
     * and returns a CustomiseShowCommand object for execution.
     *
     * @param args Remaining user input string.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public CustomiseShowCommand parse(String args) throws ParseException {
        requireNonNull(args);

        List<PrefixArgument> argList =
                tokenizeToList(args, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_TAG);
        boolean hasArguments = argList.size() != 1;

        if (!hasArguments || hasNonEmptyArgument(argList)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseShowCommand.MESSAGE_USAGE));
        }

        List<Attribute> attributeList = convertToAttributeList(argList.subList(1, argList.size()));

        if (hasDuplicateAttributes(attributeList)) {
            throw new ParseException(MESSAGE_DUPLICATE_ATTRIBUTE);
        }

        return new CustomiseShowCommand(attributeList);
    }
}
