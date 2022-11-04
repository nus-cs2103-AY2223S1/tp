package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.TaskEditCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Name;

/**
 * Parses input arguments and creates a new TaskEditCommand object
 */
public class TaskEditCommandParser implements Parser<TaskEditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskEditCommand
     * and returns a TaskEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskEditCommand parse(String args) throws ParseException {

        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEAM_INDEX, PREFIX_TASK_INDEX,
                        PREFIX_TASK_NAME, PREFIX_TASK_DEADLINE);

        Index taskIndex;
        Index teamIndex;
        Name newName = null;
        LocalDate newDeadline = null;

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAM_INDEX, PREFIX_TASK_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE));
        }

        try {
            teamIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());
            taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TaskEditCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_TASK_NAME).isEmpty()) {
            newName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get());
        }

        newDeadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).orElse(null))
                .orElse(null);


        if (newName == null && newDeadline == null) {
            throw new ParseException(String.format(MESSAGE_NOT_EDITED, TaskEditCommand.MESSAGE_USAGE));
        }

        return new TaskEditCommand(teamIndex, taskIndex, newName, newDeadline);
    }

}
