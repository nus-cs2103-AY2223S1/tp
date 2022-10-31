package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tasks.SelectTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse user input for SelectTask Command
 */
public class SelectTaskCommandParser implements Parser<SelectTaskCommand> {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<index>[0-9]+)\\s+(?<commands>.*)");

    @Override
    public SelectTaskCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectTaskCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(matcher.group("index"));
        return new SelectTaskCommand(index, matcher.group("commands"));
    }
}
