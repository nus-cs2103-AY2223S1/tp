package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseTags;

import java.util.Arrays;
import java.util.Set;

import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.RemoveTagCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.TagCommandGroup;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

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
            String[] argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new CreateTagCommand(parseArgs(argsToPass));
        case DeleteTagCommand.COMMAND_SPECIFIER:
            argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new DeleteTagCommand(parseArgs(argsToPass));
        case RemoveTagCommand.COMMAND_SPECIFIER:
            argsToPass = Arrays.copyOfRange(argArray, 1, argArray.length);
            return new RemoveTagCommandParser().parse(String.join(" ", argsToPass));
        default:
            return new TagCommandParser().parse(trimmedArgs);
        }
    }

    /**
     * Parses the given {@code String} of tag string array arguments in the context of
     * a tag string array and returns a set of tags.
     *
     * @throws ParseException if the user input does not conform to the specified format
     */
    public Set<Tag> parseArgs(String[] args) throws ParseException {
        String trimmedArgs = String.join(" ", args).trim();
        String[] tagNames = trimmedArgs.split("\\s+");

        return parseTags(Arrays.asList(tagNames));
    }
}
