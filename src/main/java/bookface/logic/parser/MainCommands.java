package bookface.logic.parser;

import bookface.logic.commands.ClearCommand;
import bookface.logic.commands.Command;
import bookface.logic.commands.ExitCommand;
import bookface.logic.commands.HelpCommand;
import bookface.logic.commands.add.AddCommand;
import bookface.logic.parser.add.AddCommandParser;
import bookface.logic.parser.exceptions.ParseException;

/**
 * An enum class that contains all the valid user commands.
 */
public enum MainCommands implements CommandReturnable {
    ADD((arguments) -> new AddCommandParser(AddCommand.MESSAGE_USAGE).parse(arguments)),
    EDIT((arguments) -> new EditCommandParser().parse(arguments)),
    DELETE((arguments) -> new DeleteCommandParser().parse(arguments)),
    CLEAR((arguments) -> new ClearCommand()),
    FIND((arguments) -> new FindCommandParser().parse(arguments)),
    LIST((arguments) -> new ListCommandParser().parse(arguments)),
    EXIT((arguments) -> new ExitCommand()),
    HELP((arguments) -> new HelpCommand());

    private final ParserFunction<? super String, ? extends Command> commandFunction;

    MainCommands(ParserFunction<? super String, ? extends Command> commandString) {
        this.commandFunction = commandString;
    }

    /**
     * Returns the String representation of the enum
     *
     * @return Returns the String representation of the enum
     */
    @Override
    public Command runParseFunction(String arguments) throws ParseException {
        return this.commandFunction.apply(arguments);
    }
}
