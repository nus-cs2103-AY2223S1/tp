package seedu.address.logic.parser;

import seedu.address.logic.commands.ListMessageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListMessageCommandParser extends MessageCommandGroupParser {
    public ListMessageCommand parse(String args) throws ParseException {
        return new ListMessageCommand();
    }
}
