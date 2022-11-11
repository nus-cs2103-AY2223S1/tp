package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * A command to execute some string input as commands
 */
public class ExecuteCommand extends Command {
    public static final String COMMAND_WORD = "e";
    private static final String INVALID_INPUT = "I don't understand the command";

    private String commands;
    private String bonus = "";

    /**
     * Constructor for execute command
     *
     * @param bonus command to run after the parsed command finishes execution
     */
    public ExecuteCommand(String bonus) {
        if (bonus == null || bonus.trim().length() == 0) {
            this.bonus = "";
        } else {
            this.bonus = bonus;
        }
    }

    public ExecuteCommand() {
        this.bonus = "";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            Command c = AddressBookParser.get().parseCommand(commands);
            CommandResult res = c.execute(model);
            if (bonus.equals("")) {
                return new CommandResult(res.getFeedbackToUser(), false, false,
                    res.getResult().orElse(null));
            }
            return AddressBookParser.quickCommand(bonus, res.getResult().orElse(null)).execute(model);
        } catch (ParseException e) {
            throw new CommandException(INVALID_INPUT);
        }
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null) {
            commands = "";
            return this;
        }
        commands = additionalData.toString().trim();
        return this;
    }

    /**
     * Creates a parser to parse user input for execute command
     */
    public static Parser<ExecuteCommand> parser() {
        return new Parser<ExecuteCommand>() {
            @Override
            public ExecuteCommand parse(String userInput) throws ParseException {
                if (userInput == null) {
                    userInput = "";
                }
                userInput = userInput.trim();
                ParserUtil.Pair p = ParserUtil.splitPipe(userInput);
                if (p.getSecond().trim().length() == 0) {
                    return new ExecuteCommand(null);
                }
                return new ExecuteCommand(p.getSecond());
            }
        };
    }

}
