package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.attributes.AddAttributeCommand;
import seedu.address.logic.commands.attributes.AddGroupAttributeCommand;
import seedu.address.logic.commands.attributes.AddPersonAttributeCommand;
import seedu.address.logic.commands.attributes.AddTaskAttributeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AddAttributeCommand} object
 */
public class AddAttributeCommandParser implements Parser<AddAttributeCommand> {

    /**
     * Regex for the basic format of AddAttributeCommand, should contain a type (user/group/task), its respective
     * ID, the name of the attribute and the content of the attribute.
     */
    private static final Pattern ADD_ATTRIBUTE_COMMAND_FORMAT =
            Pattern.compile("(?<type>[ugt])/(?<id>\\w+)\\s+(?<attributeName>\\w+)\\s+(?<attributeContent>.+)");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddAttributeCommand}
     * and returns a {@code AddFieldCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAttributeCommand parse(String args) throws ParseException {

        final Matcher matcher = ADD_ATTRIBUTE_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttributeCommand.MESSAGE_USAGE)
            );
        }

        Index index = null;

        try {
            index = ParserUtil.parseIndex(matcher.group("id").trim()); // TODO: change this to UUID implementation
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttributeCommand.MESSAGE_USAGE), pe
            );
        }

        String type = matcher.group("type");

        String attributeName = matcher.group("attributeName").trim();
        String attributeContent = matcher.group("attributeContent").trim();

        if (type.equals("u")) {
            return new AddPersonAttributeCommand(index, attributeName, attributeContent);
        } else if (type.equals("g")) {
            return new AddGroupAttributeCommand(index, attributeName, attributeContent);
        } else if (type.equals("t")) {
            return new AddTaskAttributeCommand(index, attributeName, attributeContent);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttributeCommand.MESSAGE_USAGE)
            );
        }

    }

}
