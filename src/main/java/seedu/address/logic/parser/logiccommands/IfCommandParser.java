package seedu.address.logic.parser.logiccommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.logicalcommand.IfCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Commands to represent a if else logical flow
 */
public class IfCommandParser implements Parser<IfCommand> {

    private static final Pattern NO_ELSE = Pattern
        .compile("\\[\\[\\s*(?<ifcheck>.*)\\s*\\]\\]\\s*;;\\s*\\[\\[\\s*(?<true>.*)\\s*\\]\\]");
    private static final Pattern WITH_ELSE = Pattern
        .compile(
            "\\[\\[\\s*(?<ifcheck>.*)\\s*\\]\\]\\s*;;\\s*\\[\\[\\s*(?<true>.*)"
                + "\\s*\\]\\]\\s*;;\\s*\\[\\[\\s*(?<else>.*)\\s*\\]\\]");

    @Override
    public IfCommand parse(String userInput) throws ParseException {
        // "[[logical commands]] ;; [[if true command]] ;; [[if else command]]"
        userInput = userInput.trim();
        System.out.println(userInput);
        String check;
        String ifTrue;
        String ifFalse = null;
        Matcher m = WITH_ELSE.matcher(userInput);
        Matcher mElse = NO_ELSE.matcher(userInput);
        System.out.println(m.matches());
        System.out.println(mElse.matches());
        if (m.matches()) {
            check = m.group("ifcheck");
            ifTrue = m.group("true");
            ifFalse = m.group("else");
        } else if (mElse.matches()) {
            check = mElse.group("ifcheck");
            ifTrue = mElse.group("true");
        } else {
            throw new ParseException(IfCommand.MESSAGE_USAGE);
        }

        return new IfCommand(check, ifTrue, ifFalse);
    }

}
