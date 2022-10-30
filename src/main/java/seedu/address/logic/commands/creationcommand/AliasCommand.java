package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class AliasCommand extends PureCommand {

    public static final String COMMAND_WORD = "alias";
    private static final String INVALID_INPUT = "Invalid syntax!";
    private static final String INVALID_REPLACEMENT = "%s don't exist right now!";
    private static final String ALIAS_ALR_EXIST = "%s already exist right now!";
    private static final String INVALID_NAME = "The command name you chose is not available!\n"
            + "Name should be unique with no space, start with a letter, can contain only numbers and letters";
    private static final String USE_EXAMPLE = "create [name] [code]\ne.g. markAllTask task foreach mark";

    private final String alias;
    private final String key;

    public AliasCommand(String alias, String key) {
        this.alias = alias;
        this.key = key;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        AddressBookParser.get().addAlias(alias, key);
        return new CommandResult(String.format("%s -> %s has been added!", alias, key), false, false);
    }

    public static Parser<AliasCommand> parser() {
        return new Parser<AliasCommand>() {
            @Override
            public AliasCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                String[] tokens = userInput.split("\\s+", 2);
                String alias = tokens[0].trim();
                String key = tokens[1].trim();
                if (tokens.length != 2 || tokens[1].trim().length() == 0) {
                    throw new ParseException(INVALID_INPUT + "\n" + USE_EXAMPLE);
                }

                if (AddressBookParser.get().isKeyAvailable(key)) {
                    throw new ParseException(String.format(INVALID_REPLACEMENT, key));
                }

                if (!AddressBookParser.get().isKeyAvailable(alias)) {
                    throw new ParseException(String.format(ALIAS_ALR_EXIST, key));
                }

                if (AddressBookParser.isValidName(alias)) {
                    return new AliasCommand(alias, key);
                }

                throw new ParseException(INVALID_NAME);
            }
        };
    }
}
