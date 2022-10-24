package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static coydir.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.util.stream.Stream;

import coydir.logic.commands.AddLeaveCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;

public class AddLeaveCommandParser implements Parser<AddLeaveCommand> {
    
    public AddLeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
        ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_STARTDATE, PREFIX_ENDDATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_STARTDATE, PREFIX_ENDDATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
        }
        String Id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Leave leave = ParserUtil.parseLeave(argMultimap.getValue(PREFIX_ID).get() + argMultimap.getValue(PREFIX_ID).get());
        return new AddLeaveCommand(new EmployeeId(Id), leave);
    }

    

        /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
