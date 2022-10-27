package seedu.address.logic.commands.operators;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class PrintCommand extends Command {

    public static final String COMMAND_WORD = "print";

    private String toPrint = "No values were supplied!";

    public PrintCommand() throws ParseException {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(toPrint);
    }

    public static Parser<PrintCommand> parser() {
        return new Parser<PrintCommand>() {
            @Override
            public PrintCommand parse(String userInput) throws ParseException {
                return new PrintCommand();
            }

        };
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null) {
            return;
        }
        toPrint = additionalData.toString();
    }
}
