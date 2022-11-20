package bookface.logic.parser.list;

import bookface.logic.commands.list.ListAllCommand;
import bookface.logic.commands.list.ListBooksCommand;
import bookface.logic.commands.list.ListCommand;
import bookface.logic.commands.list.ListLoansCommand;
import bookface.logic.commands.list.ListOverdueCommand;
import bookface.logic.commands.list.ListUsersCommand;
import bookface.logic.parser.CommandReturnable;
import bookface.logic.parser.ParserFunction;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid List commands.
 */
public enum ListSubcommand implements CommandReturnable {
    USERS((args) -> new ListUsersCommand()),
    BOOKS((args) -> new ListBooksCommand()),
    LOANS((args) -> new ListLoansCommand()),
    ALL((args) -> new ListAllCommand()),
    OVERDUE((args) -> new ListOverdueCommand());

    private final ParserFunction<? super String, ? extends ListCommand> commandFunction;

    ListSubcommand(ParserFunction<? super String, ? extends ListCommand> commandString) {
        this.commandFunction = commandString;
    }

    @Override
    public ListCommand runParseFunction(String args) throws ParseException {
        return this.commandFunction.apply(args);
    }
}
