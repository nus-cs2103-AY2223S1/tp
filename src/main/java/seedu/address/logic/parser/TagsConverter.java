package seedu.address.logic.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import picocli.CommandLine;
import seedu.address.model.tag.Tag;

public class TagsConverter implements CommandLine.IParameterConsumer {
    @Override
    public void consumeParameters(Stack<String> args, CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        Set<Tag> tags = new HashSet<>();
        while (!args.isEmpty()) {
            String tag = args.pop();
            if (!Tag.isValidTagName(tag)) {
                throw new CommandLine.ParameterException(commandSpec.commandLine(), Tag.MESSAGE_CONSTRAINTS);
            }
            tags.add(new Tag(tag));
        }
        argSpec.setValue(tags);
    }
}
