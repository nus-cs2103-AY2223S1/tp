package seedu.classify.logic.parser;

import seedu.classify.logic.commands.Command;
import seedu.classify.logic.commands.ViewStatsCommand;
import seedu.classify.logic.parser.exceptions.ParseException;

public class ViewStatsCommandParser implements Parser<ViewStatsCommand> {

    @Override
    public ViewStatsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        return null;
    }
}
