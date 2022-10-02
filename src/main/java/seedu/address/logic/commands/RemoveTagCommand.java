package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Removes a tag from a person.
 */
public class RemoveTagCommand extends TagCommandGroup {

    public static final String COMMAND_SPECIFIER = "remove";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove tags like this. "
            + "Parameters: "
            + "[TAG1] [TAG2] ... \n"
            + "Example: " + COMMAND_WORD + " 1 friend";

    public static final String MESSAGE_SUCCESS = "Tags removed from user: %1$s";
    public static final String MESSAGE_TAGS_NOT_FOUND = "Tags do not exist: %1$s";
    public static final String MESSAGE_TAGS_NOT_BELONG_TO_USER = "Tags do not belong to user: %1$s";

    private final Index index;
    private final Set<Tag> tagsToRemove;

    /**
     * @param tagsToRemove to tag the person with
     */
    public RemoveTagCommand(Index index, Set<Tag> tagsToRemove) {
        requireNonNull(index);
        requireNonNull(tagsToRemove);
        this.index = index;
        this.tagsToRemove = tagsToRemove;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person person = lastShownList.get(index.getZeroBased());

        Set<Tag> tagsNotFound = tagsToRemove.stream().filter(tag -> !model.hasTag(tag)).collect(Collectors.toSet());
        if (!tagsNotFound.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_TAGS_NOT_FOUND, Tag.toString(tagsNotFound)));
        }

        Set<Tag> tagsNotBelongingToUser = tagsToRemove.stream()
                .filter(tag -> !person.hasTag(tag)).collect(Collectors.toSet());
        if (!tagsNotBelongingToUser.isEmpty()) {
            return new CommandResult(
                    String.format(MESSAGE_TAGS_NOT_BELONG_TO_USER,
                    Tag.toString(tagsNotBelongingToUser)));
        }

        model.removeTags(person, tagsToRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToRemove)));
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
        RemoveTagCommand e = (RemoveTagCommand) other;
        return index.equals(e.index) && tagsToRemove.equals(e.tagsToRemove);
    }
}
