package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.RENAME_TAG_COMMAND;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.tag.Tag;

/**
 * Renames an existing tag in FoodRem.
 */
public class RenameTagCommand extends Command {
    private final Tag originalTag;
    private final Tag renamedTag;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public RenameTagCommand(Tag originalTag, Tag renamedTag) {
        requireNonNull(originalTag);
        requireNonNull(renamedTag);
        this.originalTag = originalTag;
        this.renamedTag = renamedTag;
    }

    @Override
    public CommandResult<String> execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(originalTag)) {
            throw new CommandException("This tag does not exist in the FoodRem.");
        }

        if (model.hasTag(renamedTag)) {
            throw new CommandException("This tag name already exists in the FoodRem.");
        }

        model.setTag(originalTag, renamedTag);
        return CommandResult.from(String.format("Original tag: %s\nRenamed tag: %s\n", originalTag, renamedTag));
    }

    public static String getUsage() {
        return RENAME_TAG_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RenameTagCommand
                && originalTag.equals(((RenameTagCommand) other).originalTag)
                && renamedTag.equals(((RenameTagCommand) other).renamedTag));
    }
}
