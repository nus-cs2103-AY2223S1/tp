package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ViewScheduleCommand object
 */
public class ViewScheduleCommandParser implements Parser<ViewScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewScheduleCommand
     * and returns a ViewScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewScheduleCommand parse(String args) throws ParseException {


        if (args.trim().isEmpty()) {
            return new ViewScheduleCommand();
        }

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_OF_SCHEDULE, PREFIX_WEEKDAY);

        if (!argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewScheduleCommand.MESSAGE_USAGE));
        } else if (arePrefixesPresent(argumentMultimap, PREFIX_MODULE_OF_SCHEDULE)) {
            String module = ParserUtil.parseModule(argumentMultimap.getValue(PREFIX_MODULE_OF_SCHEDULE).get());
            return new ViewScheduleCommand();
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewScheduleCommand.MESSAGE_USAGE));
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
