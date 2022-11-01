package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import coydir.logic.commands.DeleteLeaveCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.EmployeeId;

/**
 * Parses user input to return a DeleteLeaveCommand object.
 */
public class DeleteLeaveCommandParser implements Parser<DeleteLeaveCommand> {

    /**
     * Parses user input to return a DeleteLeaveCommand object.
     */
    public DeleteLeaveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
        }
        String id = ParserUtil.parseNumber(argMultimap.getValue(PREFIX_ID).get());
        int index = Integer.parseInt(ParserUtil.parseNumber(argMultimap.getValue(PREFIX_INDEX).get()));
        return new DeleteLeaveCommand(new EmployeeId(id), index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
