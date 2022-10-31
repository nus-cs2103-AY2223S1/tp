package seedu.address.logic.commands.logicalcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Represent a command to do if else
 */
public class IfCommand extends Command {

    public static final String COMMAND_WORD = "if";
    public static final String MESSAGE_USAGE = "if [[logical commands]] ;; [[if true command]] ;; [[if else command]]";

    private static final String NOT_BOOLEAN_COMMAND = "The command did not return a boolean conditional!";

    private Object details = null;
    private final Command ifC;
    private final Command trueC;
    private final Command elseC;

    /**
     * Constructor to create a if else command
     *
     * @param ifString conditional command
     * @param trueString command if true
     * @param elseString command if false
     * @throws ParseException thrown when if else cannot parse this command
     */
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

    /**
     * Constructor for when else statement is optional
     *
     * @param ifString
     * @param trueString
     * @throws ParseException
     */
    public IfCommand(String ifString, String trueString) throws ParseException {
        this(ifString, trueString, null);
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        details = additionalData;
        return this;
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
