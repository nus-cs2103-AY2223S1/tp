package soconnect.logic.parser.customise;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.customise.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.customise.CustomiseCommand.MESSAGE_UNKNOWN_CUSTOMISE_COMMAND;
import static soconnect.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static soconnect.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static soconnect.logic.parser.CliSyntax.INDICATOR_PHONE;
import static soconnect.logic.parser.CliSyntax.INDICATOR_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soconnect.logic.commands.HelpCommand;
import soconnect.logic.commands.customise.CustomiseCommand;
import soconnect.logic.commands.customise.CustomiseHideCommand;
import soconnect.logic.commands.customise.CustomiseOrderCommand;
import soconnect.logic.commands.customise.CustomiseShowCommand;
import soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.Prefix;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CustomiseCommand object.
 */
public class CustomiseCommandParser implements Parser<CustomiseCommand> {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given String of arguments in the context of the CustomiseCommand
     * and returns a CustomiseCommand object for execution.
     *
     * @param input Remaining user input string.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public CustomiseCommand parse(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case CustomiseHideCommand.COMMAND_WORD:
            return new CustomiseHideCommandParser().parse(arguments);
        case CustomiseShowCommand.COMMAND_WORD:
            return new CustomiseShowCommandParser().parse(arguments);
        case CustomiseOrderCommand.COMMAND_WORD:
            return new CustomiseOrderCommandParser().parse(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_UNKNOWN_CUSTOMISE_COMMAND, CustomiseCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Converts a given List of PrefixArgument objects into a List of Attributes.
     *
     * @param argList The List of PrefixArgument objects.
     * @return A List of Attributes.
     * @throws ParseException If the user input does not conform the expected format.
     */
    protected static List<Attribute> convertToAttributeList(List<PrefixArgument> argList) throws ParseException {
        List<Attribute> attributeList = new ArrayList<>();

        for (PrefixArgument prefixArgument : argList) {
            attributeList.add(convertToAttribute(prefixArgument.getPrefix()));
        }

        return attributeList;
    }

    /**
     * Converts a given Prefix into an Attribute.
     *
     * @param prefix The input prefix.
     * @return The Attribute.
     * @throws ParseException If the user input does not conform the expected format.
     */
    protected static Attribute convertToAttribute(Prefix prefix) throws ParseException {
        switch (prefix.toString()) {
        case INDICATOR_ADDRESS:
            return ADDRESS;
        case INDICATOR_EMAIL:
            return EMAIL;
        case INDICATOR_PHONE:
            return PHONE;
        case INDICATOR_TAG:
            return TAGS;
        case "":
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CustomiseOrderCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if there is a non-empty argument. Returns false otherwise.
     *
     * @param argList The List of PrefixArgument objects.
     * @return True if there is a non-empty argument. False otherwise.
     */
    protected static boolean hasNonEmptyArgument(List<PrefixArgument> argList) {
        for (PrefixArgument prefixArgument : argList) {
            if (!prefixArgument.getArgument().equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are duplicate attributes. Returns false otherwise.
     *
     * @param argList The List of PrefixArgument objects.
     * @return True if there is a non-empty argument. False otherwise.
     */
    protected static boolean hasDuplicateAttributes(List<Attribute> argList) {
        List<Attribute> uniqueList = new ArrayList<>();

        for (Attribute attribute : argList) {
            if (uniqueList.contains(attribute)) {
                return true;
            }
            uniqueList.add(attribute);
        }

        return false;
    }
}
