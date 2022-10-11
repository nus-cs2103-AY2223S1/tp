package bookface.logic.parser.delete;

import bookface.logic.commands.delete.DeleteCommand;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid user commands.
 */
public enum DeleteSubcommand implements CommandReturnable {
    USER((args) -> new DeleteUserArgumentsParser().parse(args)),
    BOOK((args) -> new DeleteBookArgumentsParser().parse(args));

    private final ParserFunction<? super String, ? extends DeleteCommand> commandFunction;

    DeleteSubcommand(ParserFunction<? super String, ? extends DeleteCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public DeleteCommand runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
