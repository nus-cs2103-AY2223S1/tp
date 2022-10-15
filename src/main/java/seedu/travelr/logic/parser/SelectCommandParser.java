package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.SelectCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.event.EventInItineraryPredicate;

public class SelectCommandParser implements Parser<SelectCommand> {
    public SelectCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectCommand(new EventInItineraryPredicate(index));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE), pe);
        }
    }
}
