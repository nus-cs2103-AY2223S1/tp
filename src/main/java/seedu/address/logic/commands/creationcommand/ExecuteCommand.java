package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class ExecuteCommand extends Command {
    public static final String COMMAND_WORD = "e";
    private static final String INVALID_INPUT = "I don't understand the command";

    private String commands;
    private String bonus = "";

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
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null) {
            commands = "";
            return;
        }
        commands = additionalData.toString().trim();
    }

    public static Parser<ExecuteCommand> parser() {
        return new Parser<ExecuteCommand>() {
            @Override
            public ExecuteCommand parse(String userInput) throws ParseException {
                if (userInput == null) {
                    userInput = "";
                }
                userInput = userInput.trim();
                ParserUtil.Pair p = ParserUtil.splitPipe(userInput);
                if (p.second.trim().length() == 0) {
                    return new ExecuteCommand(null);
                }
                return new ExecuteCommand(p.second);
            }
        };
    }

}
