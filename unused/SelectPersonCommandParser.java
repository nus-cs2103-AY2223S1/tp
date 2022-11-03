package seedu.address.logic.parser.persons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.persons.SelectPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse user input for SelectPerson Command
 */
public class SelectPersonCommandParser implements Parser<SelectPersonCommand> {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<index>[0-9]+)\\s+(?<commands>.*)");

    @Override
    public SelectPersonCommand parse(String args) throws ParseException {
        System.out.println(args);
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        System.out.println(matcher.matches());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectPersonCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(matcher.group("index"));
        return new SelectPersonCommand(index, matcher.group("commands"));
    }
}
