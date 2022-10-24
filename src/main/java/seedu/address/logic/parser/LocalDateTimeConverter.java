package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Stack;

import picocli.CommandLine;
import seedu.address.logic.commands.SetDeadlineCommand;

public class LocalDateTimeConverter implements CommandLine.IParameterConsumer {
    @Override
    public void consumeParameters(Stack<String> args, CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        if (args.size() != 2) {
            throw new CommandLine.ParameterException(commandSpec.commandLine(), "Requires date and time");
        }

        try {
            String datetimeString = args.pop() + " " + args.pop();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            argSpec.setValue(LocalDateTime.parse(datetimeString, formatter));
        } catch (DateTimeParseException | NumberFormatException pe) {
            // TODO: Use separate error message instead of command usage message
            throw new CommandLine.TypeConversionException(SetDeadlineCommand.MESSAGE_USAGE);
        }
    }
}
