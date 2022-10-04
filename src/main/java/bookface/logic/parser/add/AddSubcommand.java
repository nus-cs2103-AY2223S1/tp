package bookface.logic.parser.add;

import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid user commands.
 */
public enum AddSubcommand implements CommandReturnable {
    USER((args) -> new AddUserArgumentsParser().parse(args));

    private final ParserFunction<? super String, ? extends bookface.logic.commands.add.AddCommand> commandFunction;

    AddSubcommand(ParserFunction<? super String, ? extends bookface.logic.commands.add.AddCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public bookface.logic.commands.add.AddCommand runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
