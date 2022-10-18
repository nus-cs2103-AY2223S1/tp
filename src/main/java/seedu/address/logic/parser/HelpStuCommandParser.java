package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.HelpStuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class HelpStuCommandParser implements Parser<HelpStuCommand> {

    @Override
    public HelpStuCommand parse(String userInput) throws ParseException {
        try {
            Index index;
            index = ParserUtil.parseIndex(userInput);
            return new HelpStuCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpStuCommand.MESSAGE_USAGE), pe
            );
        }
    }
}
