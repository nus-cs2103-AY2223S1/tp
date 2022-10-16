package soconnect.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.Model;
import soconnect.model.tag.Tag;

public class TagDeleteCommand extends TagCommand {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = TagCommand.COMMAND_WORD + " "
            + COMMAND_WORD + ": Deletes a tag from the SoConnect. "
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + TagCommand.COMMAND_WORD + " "
            + COMMAND_WORD + " "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "Tag deleted: %1$s";
    public static final String MESSAGE_NO_SUCH_TAG = "This tag does not exist";

    private final Tag tag;

    /**
     * Constructs an {@code TagCreateCommand} to create the specified {@code Tag} in SoConnect.
     */
    public TagDeleteCommand(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);

        if (!model.hasTag(tag)) {
            throw new CommandException(MESSAGE_NO_SUCH_TAG);
        }

        model.deleteTag(tag);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tag));
    }
}
