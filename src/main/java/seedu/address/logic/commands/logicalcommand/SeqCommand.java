package seedu.address.logic.commands.logicalcommand;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Commands to allow the user to chain commands together
 */
public class SeqCommand extends Command {

    public static final String COMMAND_WORD = "seq";
    private static final String USE_MESSAGE = "seq command1 (; command2) (| command3) (; ...) (| ...)";

    private Object ctx;
    private List<String> replacers;

    public SeqCommand(List<String> replacements) throws ParseException {
        this.replacers = replacements;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        AddressBookParser compiler = AddressBookParser.get();
        Object prevResult = ctx;
        Object toApply = ctx;
        String tmp;
        CommandResult ret = new CommandResult("All commands failed or no commands were supplied");
        int count = 0;
        int fail = 0;
        for (int i = 0; i < replacers.size(); i++) {
            tmp = replacers.get(i).trim();
            if (tmp.equals(";")) {
                toApply = ctx;
                prevResult = ctx;
                continue;
            } else if (tmp.equals("|")) {
                toApply = prevResult;
                continue;
            }
            count++;
            try {
                Command c = compiler.parseCommand(tmp);
                c.setInput(toApply);
                ret = c.execute(model);
                prevResult = ret.getResult().orElse(null);
            } catch (ParseException pe) {
                fail++;
            }
        }
        return ret;
    }

    /**
     * Parser to parse user input for seq command
     *
     * @return
     */
    public static Parser<SeqCommand> parser() {
        return new Parser<SeqCommand>() {
            @Override
            public SeqCommand parse(String userInput) throws ParseException {
                if (userInput.trim().length() == 0) {
                    throw new ParseException(USE_MESSAGE);
                }
                List<String> res = Arrays.asList(userInput.trim().split("((?<=[;\\|])|(?=[;\\|]\\s*))"));
                return new SeqCommand(res);
            }
        };
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        this.ctx = additionalData;
        return this;
    }
}
