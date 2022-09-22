package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 *  Creates a tag in the address book.
 */
public class CreateTagCommand extends TagCommandGroup {

    public static final String COMMAND_SPECIFIER = "create";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates tags with the names given. "
            + "Parameters: "
            + "[TAG1] [TAG2] ... \n"
            + "Example: " + COMMAND_WORD + " tag1 tag2 tag3";

    public static final String MESSAGE_SUCCESS = "New tags created: %1$s";
    public static final String MESSAGE_DUPLICATE_TAGS = "Tag(s) %1$s already exist in the address book";

    private final Set<Tag> tagsToAdd;

    /**
     * @param tagsToCreate tags to add to the address book
     */
    public CreateTagCommand(Set<Tag> tagsToCreate) {
        requireNonNull(tagsToCreate);
        this.tagsToAdd = tagsToCreate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tag> duplicateTags = new ArrayList<>();
        List<Tag> nonDuplicateTags = new ArrayList<>();

        for (Tag tag: tagsToAdd) {
            if (model.hasTag(tag)) {
                duplicateTags.add(tag);
            } else {
                nonDuplicateTags.add(tag);
                model.addTag(tag);
            }
        }

        if (duplicateTags.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToAdd)));
        } else {
            return new CommandResult(
                    String.format(MESSAGE_DUPLICATE_TAGS, Tag.toString(duplicateTags)) + "\n"
                            + String.format(MESSAGE_SUCCESS, Tag.toString(nonDuplicateTags)));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        CreateTagCommand e = (CreateTagCommand) other;
        return tagsToAdd.equals(e.tagsToAdd);
    }
}
