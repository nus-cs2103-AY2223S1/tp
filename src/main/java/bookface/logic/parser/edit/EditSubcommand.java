package bookface.logic.parser.edit;

import bookface.logic.commands.edit.EditCommand;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid Edit commands.
 */
public enum EditSubcommand implements CommandReturnable {
    USER((args) -> new EditUserArgumentsParser().parse(args)),
    BOOK((args) -> new EditBookArgumentsParser().parse(args));

    private final ParserFunction<? super String, ? extends EditCommand> commandFunction;

    EditSubcommand(ParserFunction<? super String, ? extends EditCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public EditCommand runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
