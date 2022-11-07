package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CustomCommandBuilder;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Command to allow the user to create their custom commands
 */
public class CreateCommand extends PureCommand {

    public static final String COMMAND_WORD = "macro";
    private static final String INVALID_INPUT = "Invalid syntax!";
    private static final String WRONG_CODE = "The code you entered cannot be compiled!";
    private static final String INVALID_NAME = "The command name you chose is not available!\n"
        + "Name should be unique with no space, start with a letter, can contain only numbers and letters";
    private static final String USE_EXAMPLE = "create [name] [code]\ne.g. markAllTask task foreach mark";

    private final CustomCommandBuilder builder;

    public CreateCommand(CustomCommandBuilder builder) {
        this.builder = builder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        AddressBookParser.get().addCommand(builder);
        return new CommandResult(String.format("%s has been added!", builder.getRepr()), false, false);
    }

    /**
     * Creates a parser to parse user input for create command
     */
    public static Parser<CreateCommand> parser() {
        return new Parser<CreateCommand>() {
            @Override
            public CreateCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                String[] tokens = userInput.split("\\s+", 2);
                if (tokens.length < 2 || tokens[1].trim().length() == 0) {
                    throw new ParseException(INVALID_INPUT + "\n" + USE_EXAMPLE);
                }
                String key = tokens[0].trim();
                String value = tokens[1].trim();
                if (!AddressBookParser.isValidCommand(value)) {
                    throw new ParseException(WRONG_CODE);
                }
                if (AddressBookParser.isValidName(key) && AddressBookParser.get().isKeyAvailable(key)) {
                    return new CreateCommand(new CustomCommandBuilder(key, value));
                }
                throw new ParseException(INVALID_NAME);
            }
        };
    }
}
