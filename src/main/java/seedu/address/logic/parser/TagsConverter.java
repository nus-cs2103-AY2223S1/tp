package seedu.address.logic.parser;

import java.util.Stack;

import picocli.CommandLine;
import seedu.address.model.tag.Tag;

public class TagsConverter implements CommandLine.IParameterConsumer {
    @Override
    public void consumeParameters(Stack<String> args, CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        while (!args.isEmpty()) {
            if (!Tag.isValidTagName(args.pop())) {
                throw new CommandLine.ParameterException(commandSpec.commandLine(), Tag.MESSAGE_CONSTRAINTS);
            }
        }
    }
}
