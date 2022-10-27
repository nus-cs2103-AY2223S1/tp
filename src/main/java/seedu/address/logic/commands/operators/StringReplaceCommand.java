package seedu.address.logic.commands.operators;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class StringReplaceCommand extends Command {

    public static final String COMMAND_WORD = "r";
    private static final String INVALID_INPUT = "Missing Strings";
    private static final String USE_MESSAGE = "replace $1-txt\\\\$2-txt\\\\...$1 ... $2...\\\\";

    private boolean replaceLast = false;
    private String txt = null;
    private List<String> replacers;

    public StringReplaceCommand(List<String> replacements) throws ParseException {
        this.replacers = replacements;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int end = replacers.size();
        if (replaceLast) {
            end--;
        } else {
            if (end == 0) {
                throw new CommandException(INVALID_INPUT);
            }
            txt = replacers.get(end - 1);
        }

        for (int i = 1; i <= end; i++) {
            txt = txt.replaceAll(String.format("$%d", i), replacers.get(i - 1));
        }

        return new CommandResult("result is: " + txt, false, false, txt);
    }

    public static Parser<StringReplaceCommand> parser() {
        return new Parser<StringReplaceCommand>() {
            @Override
            public StringReplaceCommand parse(String userInput) throws ParseException {

                if (userInput.trim().length() == 0) {
                    throw new ParseException(USE_MESSAGE);
                }
                List<String> val = Arrays.asList(userInput.trim().split("\\\\"));
                return new StringReplaceCommand(val);
            }
        };
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null || additionalData.toString().trim() == "") {
            txt = null;
            return;
        }
        replaceLast = true;
        txt = additionalData.toString();
    }
}
