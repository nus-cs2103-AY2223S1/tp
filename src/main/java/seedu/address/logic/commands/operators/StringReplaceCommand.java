package seedu.address.logic.commands.operators;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Command to do string replacements
 */
public class StringReplaceCommand extends Command {

    public static final String COMMAND_WORD = "r";
    private static final String INVALID_INPUT = "Missing Strings";
    private static final String USE_MESSAGE = "replace $replacement txt\\txt %s to replace";

    private String txt = null;
    private List<String> replacers;

    public StringReplaceCommand(List<String> replacements) throws ParseException {
        this.replacers = replacements;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String target;
        if (replacers.size() == 2) {
            txt = replacers.get(0);
            target = replacers.get(1);
        } else {
            if (replacers.size() != 1 || txt == null) {
                System.out.println(replacers);
                throw new CommandException(INVALID_INPUT);
            }
            target = replacers.get(0);
        }
        target = target.replaceAll("%s", txt);

        return new CommandResult("result is: " + target, false, false, target);
    }

    /**
     * Returns a parser to parse user input for replace command.
     */
    public static Parser<StringReplaceCommand> parser() {
        return new Parser<StringReplaceCommand>() {
            @Override
            public StringReplaceCommand parse(String userInput) throws ParseException {

                if (userInput.trim().length() == 0) {
                    throw new ParseException(USE_MESSAGE);
                }
                List<String> val = Arrays.asList(userInput.trim().split("\\\\", 2));
                return new StringReplaceCommand(val);
            }
        };
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        System.out.print(additionalData);
        if (additionalData == null || additionalData.toString().trim() == "") {
            txt = null;
            return this;
        }
        txt = additionalData.toString();
        return this;
    }
}
