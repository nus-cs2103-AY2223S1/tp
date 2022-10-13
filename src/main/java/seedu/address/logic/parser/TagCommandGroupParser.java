package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.commands.RemoveTagCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.TagCommandGroup;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for the TagCommandGroup, and returns the desired command
 * in the TagCommandGroup, as indicated by the commandSpecifier.
 */
public class TagCommandGroupParser implements Parser<TagCommandGroup> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * and returns a command in the TagCommandGroup for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommandGroup parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            String errorMessage = String.format("%s\n\n%s\n\n%s",
                    CreateTagCommand.MESSAGE_USAGE,
                    TagCommand.MESSAGE_USAGE,
                    RemoveTagCommand.MESSAGE_USAGE);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        String[] argArray = trimmedArgs.split("\\s+");
        String commandSpecifier = argArray[0];

        switch (commandSpecifier) {
        case CreateTagCommand.COMMAND_SPECIFIER:
        case CreateTagCommand.COMMAND_SPECIFIER_ALIAS:
            String[] argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new CreateTagCommandParser().parse(String.join(" ", argsToPass));
        case RemoveTagCommand.COMMAND_SPECIFIER:
        case RemoveTagCommand.COMMAND_SPECIFIER_ALIAS:
            argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new RemoveTagCommandParser().parse(String.join(" ", argsToPass));
        default:
            return new TagCommandParser().parse(trimmedArgs);
        }
    }
}
