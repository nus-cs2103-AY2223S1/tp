package bookface.logic.parser.add;

import bookface.logic.commands.add.AddCommand;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid user commands.
 */
public enum AddSubcommand implements CommandReturnable {
    USER((args) -> new AddUserArgumentsParser().parse(args)),
    BOOK((args) -> new AddBookArgumentsParser().parse(args));

    private final ParserFunction<? super String, ? extends AddCommand> commandFunction;

    AddSubcommand(ParserFunction<? super String, ? extends AddCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public AddCommand runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
