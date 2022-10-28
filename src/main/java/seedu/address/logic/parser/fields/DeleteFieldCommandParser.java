package seedu.address.logic.parser.fields;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.fields.AddFieldCommand;
import seedu.address.logic.commands.fields.DeleteFieldCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author connlim
/**
 * Parses input arguments and creates a new TaskCommand object
 */
public class DeleteFieldCommandParser implements Parser<DeleteFieldCommand> {

    @Override
    public DeleteFieldCommand parse(String args) throws ParseException {
        args = args.trim();
        Pattern p = Pattern.compile("([gtu])/([0-9]+)\\s+([a-zA-Z][a-zA-Z0-9]*)");
        Matcher m = p.matcher(args.trim());
        if (m.matches()) {
            return new DeleteFieldCommand(ParserUtil.parseIndex(m.group(2)),m.group(1),m.group(3));
        } 
        return new DeleteFieldCommand(null, "0", args.trim());
    }

}
