package bookface.logic.parser.find;

import bookface.logic.commands.find.FindCommand;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid user commands.
 */
public enum FindSubcommand implements CommandReturnable {
    USER((args) -> new FindUserArgumentsParser().parse(args)),
    BOOK((args) -> new FindBookArgumentsParser().parse(args));

    private final ParserFunction<? super String, ? extends FindCommand> commandFunction;

    FindSubcommand(ParserFunction<? super String, ? extends FindCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public FindCommand runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
