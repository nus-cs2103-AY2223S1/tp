package bookface.logic.parser.add;

import bookface.logic.commands.add.AddCommand;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid user commands.
 */
public enum AddCommands implements CommandReturnable {
    USER((arguments) -> new AddUserArgumentsParser().parse(arguments));

    private final ParserFunction<? super String, ? extends AddCommand> commandFunction;

    AddCommands(ParserFunction<? super String, ? extends AddCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public AddCommand runParseFunction(String arguments) throws ParseException {
        return this.commandFunction.apply(arguments);
    }
}
