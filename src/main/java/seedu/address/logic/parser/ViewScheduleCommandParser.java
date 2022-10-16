package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewModuleScheduleCommand;
import seedu.address.logic.commands.ViewScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.schedule.WeekdayContainsKeywordsPredicate;
import seedu.address.model.module.schedule.Weekdays;


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

//        if (trimmedArgs.isEmpty()) {
//            return new ViewScheduleCommand();
//        }
        String[] weekdayKeywords = trimmedArgs.split("\\s+");

        return new ViewModuleScheduleCommand(new WeekdayContainsKeywordsPredicate(Arrays.asList(weekdayKeywords)));
    }



}
