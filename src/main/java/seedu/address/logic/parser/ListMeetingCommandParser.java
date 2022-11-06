package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ListMeetingCommand} object.
 */
public class ListMeetingCommandParser implements Parser<ListMeetingCommand> {

    @Override
    public ListMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);
        if (!ParserUtil.arePrefixesPresent(argumentMultimap, PREFIX_DATE)) {
            return new ListMeetingCommand(DateKeyword.ALL_TIME);
        }

        DateKeyword dateKeyword = ParserUtil.parseDateKeyword(argumentMultimap.getValue(PREFIX_DATE).get());
        return new ListMeetingCommand(dateKeyword);
    }
}
