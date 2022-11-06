package seedu.foodrem.logic.parser.tagcommandparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.tag.Tag;

/**
 * Parses input arguments and creates a new RenameTagCommand object
 */
public class RenameTagCommandParser implements Parser<RenameTagCommand> {
    /**
     * Used for separating the two different names.
     * Pattern is used instead of ArgumentTokenizer due to duplicated prefixes.
     * This pattern matches any "n/... n/...", where PREFIX_NAME can be followed
     * by any character. The validation checking of tag names is done within {@link Tag} itself.
     */
    private static final Pattern RENAME_TAG_COMMAND_FORMAT = Pattern.compile(
            String.format("%s(?<originalName>.+)\\s+%s(?<newName>.+)", CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_NAME));

    /**
     * Parses the given {@code String} of arguments in the context of the RenameTagCommand
     * and returns an RenameTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RenameTagCommand parse(String args) throws ParseException {
        final Matcher matcher = RENAME_TAG_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    RenameTagCommand.getUsage()));
        }

        final String originalName = matcher.group("originalName").trim();
        final String newName = matcher.group("newName").trim();

        if (checkExtraPrefixesInName(originalName) || checkExtraPrefixesInName(newName)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    RenameTagCommand.getUsage()));
        }

        Tag originalTag = new Tag(originalName);
        Tag renamedTag = new Tag(newName);

        return new RenameTagCommand(originalTag, renamedTag);
    }

    private boolean checkExtraPrefixesInName(String name) {
        Matcher matcher = CliSyntax.PREFIX_REGEX.matcher(name);
        return matcher.find();
    }
}
