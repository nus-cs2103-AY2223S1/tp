package seedu.address.testutil;

import picocli.CommandLine;
import seedu.address.logic.commands.Command;
/**
 * Utility class that helps to retrieve fields in command objects.
 */
public class ParserHelper {

    public static <T> T getOption(Class<T> cls, String flag, Command command) {
        CommandLine.Model.CommandSpec commandSpec = CommandLine.Model.CommandSpec.forAnnotatedObject(command);
        T value = commandSpec.findOption(flag).getValue();
        return value;
    }

    public static <T> T getParameterPosition(Class<T> cls, int position, Command command) {
        CommandLine.Model.CommandSpec commandSpec = CommandLine.Model.CommandSpec.forAnnotatedObject(command);
        T value = commandSpec.positionalParameters().get(position).getValue();
        return value;
    }

}
