package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BuyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SellCommand;
import seedu.address.logic.commands.UserGuideCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a HelpCommand object initialized
 * with the correct help message.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    public static final String INVALID_ARGUMENT_MESSAGE = "Command you are trying to get help for is invalid!\n"
            + "Use 'help' to view valid commands";

    @Override
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        switch (trimmedArgs) {
        // Sorted lexicographically
        case "add":
            return new HelpCommand(AddCommand.MESSAGE_USAGE);
        case "buy":
            return new HelpCommand(BuyCommand.MESSAGE_USAGE);
        case "clear":
            return new HelpCommand(ClearCommand.MESSAGE_USAGE);
        case "create":
            return new HelpCommand(CreateCommand.MESSAGE_USAGE);
        case "delete":
            return new HelpCommand(DeleteCommand.MESSAGE_USAGE);
        case "edit":
            return new HelpCommand(EditCommand.MESSAGE_USAGE);
        case "exit":
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);
        case "find":
            return new HelpCommand(FindCommand.MESSAGE_USAGE);
        case "help":
            return new HelpCommand(HelpCommand.MESSAGE_USAGE);
        case "list":
            return new HelpCommand(ListCommand.MESSAGE_USAGE);
        case "sell":
            return new HelpCommand(SellCommand.MESSAGE_USAGE);
        case "user_guide":
            return new HelpCommand(UserGuideCommand.MESSAGE_USAGE);
        case "view":
            return new HelpCommand(ViewCommand.MESSAGE_USAGE);
        case "":
            return new HelpCommand();
        default:
            throw new ParseException(INVALID_ARGUMENT_MESSAGE);
        }
    }
}
