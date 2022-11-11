package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RenameCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to rename a item for a displayitem
 */
public class RenameCommandParser implements Parser<RenameCommand> {
    private static final Pattern ADD_ATTRIBUTE_COMMAND_FORMAT = Pattern
            .compile("(?<type>[ugt])/(?<id>[0-9]+)\\s+(?<newname>.+)");

    @Override
    public RenameCommand parse(String args) throws ParseException {
        final Matcher matcher = ADD_ATTRIBUTE_COMMAND_FORMAT.matcher(args.trim());

        if (args.trim().length() == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE));
        }

        if (!matcher.matches()) {
            return new RenameCommand(args.trim());
        }

        Index index = null;

        try {
            index = ParserUtil.parseIndex(matcher.group("id").trim());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE), pe);
        }

        String newName = matcher.group("newname");
        String type = matcher.group("type");
        ParserUtil.parseName(newName);

        if (type.equals("u")) {
            return new RenameCommand(index, 2, newName);
        }
        if (type.equals("g")) {
            return new RenameCommand(index, 1, newName);
        }

        if (type.equals("t")) {
            return new RenameCommand(index, 3, newName);
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE));
    }
}
