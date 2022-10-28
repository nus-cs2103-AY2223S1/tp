package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class DeleteCustomCommand extends PureCommand {

    public static final String COMMAND_WORD = "rmMacro";
    private static final String INVALID_INPUT = "Invalid syntax!\nrmCommand [command name]";
    private static final String COMMAND_DONT_EXIST = "%s don't exist!";

    private final String key;

    public DeleteCustomCommand(String key) {
        this.key = key;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (AddressBookParser.get().isKeyAvailable(key)) {
            throw new CommandException(String.format(COMMAND_DONT_EXIST, key));
        }
        AddressBookParser.get().deleteCommand(key);
        return new CommandResult(String.format("%s has been removed!", key), false, false);
    }

    public static Parser<DeleteCustomCommand> parser() {
        return new Parser<DeleteCustomCommand>() {
            @Override
            public DeleteCustomCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                if (userInput.length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                return new DeleteCustomCommand(userInput);
            }
        };
    }
}
