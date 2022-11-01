package taskbook.logic.parser.tasks;

import java.util.function.Predicate;
import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskFindCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;

/**
 * Parses input arguments and creates a new TaskFindCommand
 */
public class TaskFindCommandParser implements Parser<TaskFindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskFindCommand
     * and returns an TaskSortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskFindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_QUERY,
                CliSyntax.PREFIX_ASSIGNMENT, CliSyntax.PREFIX_DONE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_QUERY)
                && !arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ASSIGNMENT)
                && !arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DONE)
                && !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskFindCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DONE)) {
            return checkDone(argMultimap);
        } else {
            return afterCheckDone(argMultimap, null);
        }
    }

    private boolean isDone(String query) throws ParseException {
        if (query.equals("X")) {
            return true;
        } else if (query.equals("O")) {
            return false;
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    "Use X or O for done or not done task query respectively"));
        }
    }

    private TaskFindCommand checkDone(ArgumentMultimap argMultimap) throws ParseException {
        boolean isDone = getIsDone(argMultimap);
        return afterCheckDone(argMultimap, (t) -> t.isDone() == isDone);
    }

    private TaskFindCommand afterCheckDone(ArgumentMultimap argMultimap,
                                           Predicate<Task> predicate) throws ParseException {
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ASSIGNMENT)) {
            return checkAssignment(argMultimap, predicate);
        } else {
            return afterCheckAssignment(argMultimap, predicate);
        }
    }

    private TaskFindCommand checkAssignment(ArgumentMultimap argMultimap,
                                            Predicate<Task> predicate) throws ParseException {
        Assignment assignment = getAssignment(argMultimap);
        if (predicate == null) {
            return afterCheckAssignment(argMultimap, (t) -> t.isAssignmentCorrect(assignment));
        } else {
            return afterCheckAssignment(argMultimap, predicate.and((t) -> t.isAssignmentCorrect(assignment)));
        }
    }

    private TaskFindCommand afterCheckAssignment(ArgumentMultimap argMultimap,
                                                 Predicate<Task> predicate) throws ParseException {
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_QUERY)) {
            return checkQuery(argMultimap, predicate);
        } else {
            return afterCheckQuery(argMultimap, predicate);
        }
    }

    private TaskFindCommand checkQuery(ArgumentMultimap argMultimap,
                                       Predicate<Task> predicate) throws ParseException {
        String query = getQuery(argMultimap);
        if (predicate == null) {
            return afterCheckQuery(argMultimap, (t) -> t.isQueryInTask(query));
        } else {
            return afterCheckQuery(argMultimap, predicate.and((t) -> t.isQueryInTask(query)));
        }
    }

    private TaskFindCommand afterCheckQuery(ArgumentMultimap argMultimap,
                                            Predicate<Task> predicate) throws ParseException {
        if (predicate == null) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    "Find arguments are empty.\n",
                    TaskFindCommand.MESSAGE_USAGE));
        }
        return new TaskFindCommand(predicate,
                argMultimap.getValue(CliSyntax.PREFIX_QUERY).orElseGet(() -> null),
                argMultimap.getValue(CliSyntax.PREFIX_ASSIGNMENT).orElseGet(() -> null),
                argMultimap.getValue(CliSyntax.PREFIX_DONE).orElseGet(() -> null));
    }

    private boolean getIsDone(ArgumentMultimap argMultimap) throws ParseException {
        return isDone(argMultimap.getValue(CliSyntax.PREFIX_DONE)
                .orElseThrow());
    }

    private String getQuery(ArgumentMultimap argMultimap) throws ParseException {
        return argMultimap.getValue(CliSyntax.PREFIX_QUERY)
                .orElseThrow(() -> new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskFindCommand.MESSAGE_USAGE)));
    }

    private Assignment getAssignment(ArgumentMultimap argMultimap) throws ParseException {
        return Assignment.parseAssignment(argMultimap.getValue(CliSyntax.PREFIX_ASSIGNMENT)
                .orElseThrow(() -> new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        TaskFindCommand.MESSAGE_USAGE))));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
