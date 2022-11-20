package bookface.logic.parser.primary;

import bookface.logic.commands.Command;
import bookface.logic.commands.ExitCommand;
import bookface.logic.commands.HelpCommand;
import bookface.logic.parser.ClearCommandParser;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.LoanCommandParser;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.ReturnCommandParser;
import bookface.logic.parser.add.AddCommandParser;
import bookface.logic.parser.delete.DeleteCommandParser;
import bookface.logic.parser.edit.EditCommandParser;
import bookface.logic.parser.exceptions.ParseException;
import bookface.logic.parser.find.FindCommandParser;
import bookface.logic.parser.list.ListCommandParser;

/**
 * An enum class that contains all the valid user commands.
 */
public enum PrimaryCommand implements CommandReturnable {
    ADD((args) -> new AddCommandParser().parse(args)),
    EDIT((args) -> new EditCommandParser().parse(args)),
    DELETE((args) -> new DeleteCommandParser().parse(args)),
    CLEAR((args) -> new ClearCommandParser().parse(args)),
    FIND((args) -> new FindCommandParser().parse(args)),
    LIST((args) -> new ListCommandParser().parse(args)),
    EXIT((args) -> new ExitCommand()),
    HELP((args) -> new HelpCommand()),
    LOAN((args) -> new LoanCommandParser().parse(args)),
    RETURN((args) -> new ReturnCommandParser().parse(args));

    private final ParserFunction<? super String, ? extends Command> commandFunction;

    PrimaryCommand(ParserFunction<? super String, ? extends Command> commandString) {
        this.commandFunction = commandString;
    }

    /**
     * Returns the String representation of the enum
     *
     * @return Returns the String representation of the enum
     */
    @Override
    public Command runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
