package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewModuleScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.schedule.ScheduleContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new ViewScheduleCommand object
 */
public class ViewScheduleCommandParser implements Parser<ViewModuleScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewScheduleCommand
     * and returns a ViewScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewModuleScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new ViewModuleScheduleCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_WEEKDAY, PREFIX_MODULE_OF_SCHEDULE);
//        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_OF_SCHEDULE, PREFIX_WEEKDAY) || !argMultimap.getPreamble().isEmpty()) {
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewModuleScheduleCommand.MESSAGE_USAGE));
        }

        Set<String> weekdaysList = ParserUtil.parseWeekdays(argMultimap.getAllValues(PREFIX_WEEKDAY));
        Set<String> modulesList = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_MODULE_OF_SCHEDULE));

        ArrayList<String> keywords = new ArrayList<>();
        keywords.addAll(weekdaysList);
        keywords.addAll(modulesList);

        return new ViewModuleScheduleCommand(new ScheduleContainsKeywordsPredicate(keywords));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }



}
