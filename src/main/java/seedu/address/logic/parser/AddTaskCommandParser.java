package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_CATEGORY,
                        PREFIX_DEADLINE, PREFIX_PERSON);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_CATEGORY,
                PREFIX_DEADLINE, PREFIX_PERSON) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Index personIndex;

        try {
            personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PERSON).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE), pe);
        }

        TaskName taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        TaskCategory category = ParserUtil.parseTaskCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        TaskDeadline deadline = ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        Task task = new Task(taskName, description, priority, category, deadline, null, false);
        return new AddTaskCommand(task, personIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
