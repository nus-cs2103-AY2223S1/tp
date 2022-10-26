package seedu.address.logic.parser;

import java.util.Stack;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Converter from {@code String} arguments to {@code LocalDateTime}.
 */
public class LocalDateTimeConverter implements CommandLine.IParameterConsumer {

    @Override
    public void consumeParameters(Stack<String> args, CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        if (args.size() != 2) {
            throw new CommandLine.ParameterException(commandSpec.commandLine(), ParserUtil.MESSAGE_INVALID_DATETIME);
        }

        try {
            String datetimeString = args.pop() + " " + args.pop();
            argSpec.setValue(ParserUtil.parseLocalDateTime(datetimeString));
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
