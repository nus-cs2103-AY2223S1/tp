package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedLowerCaseArgs = args.trim().toLowerCase();
        switch (trimmedLowerCaseArgs) {
        case "":
            return new HelpCommand();
        case "add":
            return new HelpCommand(CommandResult.CommandType.ADD);
        case "edit":
            return new HelpCommand(CommandResult.CommandType.EDIT);
        case "delete":
            return new HelpCommand(CommandResult.CommandType.DELETE);
        case "sort":
            return new HelpCommand(CommandResult.CommandType.SORT);
        case "clear":
            return new HelpCommand(CommandResult.CommandType.CLEAR);
        case "find":
            return new HelpCommand(CommandResult.CommandType.FIND);
        case "list":
            return new HelpCommand(CommandResult.CommandType.LIST);
        case "exit":
            return new HelpCommand(CommandResult.CommandType.EXIT);
        case "help":
            return new HelpCommand(CommandResult.CommandType.HELP);
        case "show":
            return new HelpCommand(CommandResult.CommandType.SHOW);
        case "assign":
            return new HelpCommand(CommandResult.CommandType.ASSIGN);
        case "unassign":
            return new HelpCommand(CommandResult.CommandType.UNASSIGN);
        case "nok":
            return new HelpCommand(CommandResult.CommandType.NOK);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

}
