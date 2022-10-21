package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.DateUtil.dateIsEqualAndAfterToday;
import static seedu.address.commons.util.DateUtil.getLocalDate;
import static seedu.address.commons.util.DateUtil.isGenericLocalDateString;
import static seedu.address.commons.util.DateUtil.isLocalDateString;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns a AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();

        if (!isGenericLocalDateString(argMultimap.getValue(PREFIX_DEADLINE).get())) {
            throw new ParseException("Date provided is invalid. Try again with yyyy-mm-dd !");
        }

        if (!isLocalDateString(argMultimap.getValue(PREFIX_DEADLINE).get())) {
            throw new ParseException("Date provided is in the correct format, but has invalid values !");
        }

        LocalDate deadline = getLocalDate(argMultimap.getValue(PREFIX_DEADLINE).get());

        if (!dateIsEqualAndAfterToday(deadline)) {
            throw new ParseException("Please choose a deadline that is today or in the future !");
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Task newTask = new Task(description, deadline, tagList);

        return new AddTaskCommand(newTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
