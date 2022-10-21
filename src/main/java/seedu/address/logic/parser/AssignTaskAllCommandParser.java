package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORKLOAD;

import java.util.stream.Stream;

import seedu.address.logic.commands.AssignTaskAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.Workload;

/**
 * Parses input arguments and creates a new {@code AssignTaskAllCommand} object
 */
public class AssignTaskAllCommandParser {
    /**
     * Parses the given {@code Sting} of arguments in the context of a {@code AssignTaskAllCommand}
     * and returns a {@code AssignTaskAllCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AssignTaskAllCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_TASK, PREFIX_WORKLOAD, PREFIX_DEADLINE);

        String inputGroup;
        Assignment inputTask;
        Workload inputWorkload;
        Deadline inputDeadline;

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_TASK) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskAllCommand.MESSAGE_USAGE));
        }

        try {
            inputGroup = argMultimap.getValue(PREFIX_GROUP).get();
            String task = argMultimap.getValue(PREFIX_TASK).get();
            String workload = argMultimap.getValue(PREFIX_WORKLOAD).get().toUpperCase();
            inputWorkload = ParserUtil.parseWorkload(workload);

            if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
                String deadline = argMultimap.getValue(PREFIX_DEADLINE).get();
                inputDeadline = ParserUtil.parseDeadline(deadline);
                inputTask = ParserUtil.parseAssignmentWithDeadline(task, inputWorkload, inputDeadline);
            } else {
                inputTask = ParserUtil.parseAssignmentWithWorkload(task, inputWorkload);
            }
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }

        return new AssignTaskAllCommand(inputGroup, inputTask);
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArguentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
