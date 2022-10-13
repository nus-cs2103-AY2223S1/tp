package jeryl.fyp.logic.parser;

import jeryl.fyp.logic.commands.AddCommand;
import jeryl.fyp.logic.commands.DeleteCommand;
import jeryl.fyp.logic.commands.FindCommand;
import jeryl.fyp.logic.commands.HelpCommand;
import jeryl.fyp.logic.commands.HelpAddCommand;
import jeryl.fyp.logic.commands.HelpDeleteCommand;
import jeryl.fyp.logic.commands.HelpFindCommand;
import jeryl.fyp.logic.commands.HelpMarkCommand;
import jeryl.fyp.logic.commands.HelpListCommand;
import jeryl.fyp.logic.commands.ListCommand;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) {
        String trimmedArgs = args.trim();

        switch (trimmedArgs) {

        case AddCommand.COMMAND_WORD:
            return new HelpAddCommand();

        case DeleteCommand.COMMAND_WORD:
            return new HelpDeleteCommand();

        case FindCommand.COMMAND_WORD:
            return new HelpFindCommand();

        case ListCommand.COMMAND_WORD:
            return new HelpListCommand();

        case MarkCommand.COMMAND_WORD:
            return new HelpMarkCommand();

        default:
            return new HelpCommand();
        }

    }

}
