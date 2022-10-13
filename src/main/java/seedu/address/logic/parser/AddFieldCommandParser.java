package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddFieldCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AddFieldCommand} object
 */
public class AddFieldCommandParser implements Parser<AddFieldCommand> {

    // Regex for the basic format of AddFieldCommand, should contain prefix and field name
    private static final Pattern ADD_FIELD_COMMAND_FORMAT =
            Pattern.compile("(?<prefix>\\S+)(?<fieldName>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddFieldCommand}
     * and returns a {@code AddFieldCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFieldCommand parse(String args) throws ParseException {

        final Matcher matcher = ADD_FIELD_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFieldCommand.MESSAGE_USAGE)
            );
        }

        String prefixString = matcher.group("prefix").trim();
        String fieldName = matcher.group("fieldName").trim();
        checkPrefix(prefixString);
        checkFieldName(fieldName);
        Prefix prefix = new Prefix(prefixString);
        return new AddFieldCommand(prefix, fieldName);
    }

    /**
     * Checks if a string is a valid prefix.
     *
     * @param prefixString a String representing a prefix to be checked.
     * @throws ParseException if the string does not fulfil the requirement as a Prefix.
     */
    private void checkPrefix(String prefixString) throws ParseException {
        if (!Prefix.isValidPrefix(prefixString)) {
            throw new ParseException("Invalid Prefix Format: Prefixes should end with a \"/\" character");
        }
    }

    /**
     * Checks if a string is a valid field name.
     *
     * @param fieldName a String representing the field name to be checked.
     * @throws ParseException if {@code fieldName} is an empty string.
     */
    private void checkFieldName(String fieldName) throws ParseException {
        requireNonNull(fieldName);
        if (fieldName.equals("")) {
            throw new ParseException("Invalid Field Name: Field name should not be empty");
        }
    }
}
