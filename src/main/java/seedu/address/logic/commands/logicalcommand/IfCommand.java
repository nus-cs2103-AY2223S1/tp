package seedu.address.logic.commands.logicalcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class IfCommand extends Command {

    public static final String COMMAND_WORD = "if";
    public static final String MESSAGE_USAGE = "if [[logical commands]] ;; [[if true command]] ;; [[if else command]]";

    private static final String NOT_BOOLEAN_COMMAND = "The command did not return a boolean conditional!";

    Object details = null;
    private final Command ifC, trueC;
    private final Command elseC;

    public IfCommand(String ifString, String trueString, String elseString) throws ParseException {
        requireNonNull(ifString);
        requireNonNull(trueString);
        AddressBookParser p = AddressBookParser.get();
        try {

            ifC = p.parseCommand(ifString);
            trueC = p.parseCommand(trueString);
            if (elseString == null || elseString.equals("")) {
                elseC = null;
            } else {
                elseC = p.parseCommand(elseString);
            }
        } catch (Exception e) {
            throw new ParseException("Syntax error parsing if");
        }
    }

    public IfCommand(String ifString, String trueString) throws ParseException {
        this(ifString, trueString, null);
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        details = additionalData;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ifC.setInput(details);
        CommandResult res = ifC.execute(model);
        Boolean result = (Boolean) res.getResult().filter(v -> v instanceof Boolean)
                .orElseThrow(() -> new CommandException(NOT_BOOLEAN_COMMAND));

        if (result) {
            trueC.setInput(details);
            trueC.execute(model);
        } else if (elseC != null) {
            elseC.setInput(details);
            elseC.execute(model);
        }

        return new CommandResult("if command has been executed!");
    }

}
