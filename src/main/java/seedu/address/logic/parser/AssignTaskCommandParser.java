package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORKLOAD;

import java.util.stream.Stream;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.Workload;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code AssignTaskCommand} object
 */
public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AssignTaskCommand}
     * and returns a {@code AssignTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_TASK, PREFIX_WORKLOAD, PREFIX_DEADLINE);

        Name inputName;
        GroupName inputGroup;
        Assignment inputTask;
        Workload inputWorkload;
        Deadline inputDeadline;

        String group;

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_TASK, PREFIX_WORKLOAD)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignTaskCommand.MESSAGE_USAGE));
        }

        try {
            String name = argMultimap.getPreamble();
            group = argMultimap.getValue(PREFIX_GROUP).get();
            String task = argMultimap.getValue(PREFIX_TASK).get();
            String workload = argMultimap.getValue(PREFIX_WORKLOAD).get().toUpperCase();

            inputName = ParserUtil.parseName(name);
            inputGroup = ParserUtil.parseGroupName(group);
            inputWorkload = ParserUtil.parseWorkload(workload);
            //Deadline field is present
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

        return new AssignTaskCommand(inputName, group, inputTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
