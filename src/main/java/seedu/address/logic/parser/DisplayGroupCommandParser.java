package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DisplayGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.FullGroupNamePredicate;

public class DisplayGroupCommandParser implements Parser<DisplayGroupCommand>{
    public DisplayGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
        if (argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DisplayGroupCommand.MESSAGE_USAGE));
        }

        String name = argumentMultimap.getPreamble();
        return new DisplayGroupCommand(new FullGroupNamePredicate(name));



    }

}
