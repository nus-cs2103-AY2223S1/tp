package seedu.address.logic.parser.fields;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.fields.AddFieldCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author connlim
/**
 * Parses input arguments and creates a new TaskCommand object
 */
public class AddFieldCommandParser implements Parser<AddFieldCommand> {

    @Override
    public AddFieldCommand parse(String args) throws ParseException {
        args = args.trim();
        Pattern p = Pattern.compile("([gtu])/([0-9]+)\\s+([a-zA-Z][a-zA-Z0-9]*)\\s+(.*)");
        Matcher m = p.matcher(args.trim());
        if (m.matches()) {
            return new AddFieldCommand(ParserUtil.parseIndex(m.group(2)),m.group(1),m.group(3), m.group(4));
        } 
        String[] arg2 = args.split("\\s+", 2);
        return new AddFieldCommand(null, "0", arg2[0], arg2[1]);
    }

}
