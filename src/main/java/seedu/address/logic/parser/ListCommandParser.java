package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.List;

import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListDeadlineCommand;
import seedu.address.logic.commands.list.ListMarkedCommand;
import seedu.address.logic.commands.list.ListModuleCommand;
import seedu.address.logic.commands.list.ListTagCommand;
import seedu.address.logic.commands.list.ListUnmarkedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskIsDonePredicate;

/**
 * Parses input arguments and creates a matching new List command
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String[] listTypes = trimmedArgs.split(" ", 2);

        switch (listTypes[0]) {
        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();
        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommandParser().parse(trimmedArgs);
        case ListUnmarkedCommand.COMMAND_WORD:
            return new ListUnmarkedCommand(new TaskIsDonePredicate(List.of("false")));
        case ListMarkedCommand.COMMAND_WORD:
            return new ListMarkedCommand(new TaskIsDonePredicate(List.of("true")));
        case ListDeadlineCommand.COMMAND_WORD:
            return new ListDeadlineCommandParser().parse(trimmedArgs);
        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommandParser().parse(trimmedArgs);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }
}
