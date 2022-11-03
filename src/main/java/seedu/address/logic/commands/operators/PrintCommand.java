package seedu.address.logic.commands.operators;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Command to print the value of an object
 */
public class PrintCommand extends Command {

    public static final String COMMAND_WORD = "print";

    private String toPrint = "No values were supplied!";

    public PrintCommand() throws ParseException {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(toPrint);
    }

    /**
     * Returns a parser to parse user input for print command
     */
    public static Parser<PrintCommand> parser() {
        return new Parser<PrintCommand>() {
            @Override
            public PrintCommand parse(String userInput) throws ParseException {
                return new PrintCommand();
            }

        };
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null) {
            return this;
        }
        toPrint = additionalData.toString();
        return this;
    }
}
