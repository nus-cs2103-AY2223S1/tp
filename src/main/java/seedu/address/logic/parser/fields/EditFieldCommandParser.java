package seedu.address.logic.parser.fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.fields.EditFieldCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

// @@author jasonchristopher21
/**
 * Parses input arguments and creates a new TaskCommand object
 */
public class EditFieldCommandParser implements Parser<EditFieldCommand> {

    @Override
    public EditFieldCommand parse(String args) throws ParseException {
        args = args.trim();
        Pattern p = Pattern.compile("([gtu])/([0-9]+)\\s+([a-zA-Z][a-zA-Z0-9]*)\\s+(.*)");
        Pattern p2 = Pattern.compile("([a-zA-Z][a-zA-Z0-9]*)\\s+(.*)");
        Matcher m = p.matcher(args);
        if (m.matches()) {
            return new EditFieldCommand(ParserUtil.parseIndex(m.group(2)), m.group(1), m.group(3), m.group(4));
        }
        m = p2.matcher(args);
        if (m.matches()) {
            return new EditFieldCommand(null, "0", m.group(1), m.group(2));
        }
        throw new ParseException(EditFieldCommand.MESSAGE_USAGE);
    }

}
