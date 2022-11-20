package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code DeleteTaskCommand} object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteTaskCommand}
     * and returns a {@code DeleteTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_TASK);

        Name inputName;
        GroupName inputGroup;
        Assignment inputTask;

        String group;

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_TASK) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        }

        try {
            String name = argMultimap.getPreamble();
            group = argMultimap.getValue(PREFIX_GROUP).get();
            String task = argMultimap.getValue(PREFIX_TASK).get();

            inputName = ParserUtil.parseName(name);
            inputGroup = ParserUtil.parseGroupName(group);
            inputTask = ParserUtil.parseAssignment(task);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }

        return new DeleteTaskCommand(inputName, group, inputTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
