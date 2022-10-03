package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Tags a user with one or more tags.
 */
public class TagCommand extends TagCommandGroup {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags the person with the given tags. "
            + "Parameters: "
            + "[TAG1] [TAG2] ... \n"
            + "Example: " + COMMAND_WORD + " 1 friend colleague";

    public static final String MESSAGE_SUCCESS = "Tagged the person with: %1$s";

    public static final String MESSAGE_TAGS_NOT_FOUND = "Tags do not exist: %1$s";

    public static final String MESSAGE_DUPLICATE_TAGS = "Person already has the tags: %1$s";

    public static final String MESSAGE_NO_TAGS_ADDED = "No tags were added to the person";

    private final Index index;
    private final Set<Tag> tagsToAdd;

    /**
     * @param index of the person in the filtered person list to tag
     * @param tagsToAdd to tag the person with
     */
    public TagCommand(Index index, Set<Tag> tagsToAdd) {
        requireNonNull(index);
        requireNonNull(tagsToAdd);
        this.index = index;
        this.tagsToAdd = tagsToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToTag = lastShownList.get(index.getZeroBased());

        Set<Tag> notFoundTags = tagsToAdd.stream().filter(tag -> !model.hasTag(tag)).collect(Collectors.toSet());
        if (!notFoundTags.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_TAGS_NOT_FOUND, Tag.toString(notFoundTags)));
        }

        Set<Tag> duplicateTags = tagsToAdd.stream().filter(personToTag::hasTag).collect(Collectors.toSet());
        Set<Tag> nonDuplicateTags = tagsToAdd.stream()
                .filter(tag -> !personToTag.hasTag(tag)).collect(Collectors.toSet());

        Set<Tag> tags = new HashSet<>();
        tags.addAll(personToTag.getTags());
        tags.addAll(nonDuplicateTags);
        Person taggedPerson = new Person(personToTag.getName(), personToTag.getPhone(),
                personToTag.getEmail(), personToTag.getAddress(), personToTag.getRemark(), tags);

        model.setPerson(personToTag, taggedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        StringBuilder resultString = new StringBuilder();

        if (!duplicateTags.isEmpty()) {
            resultString.append(String.format(MESSAGE_DUPLICATE_TAGS, Tag.toString(duplicateTags)) + "\n");
        }

        if (nonDuplicateTags.isEmpty()) {
            resultString.append(MESSAGE_NO_TAGS_ADDED);
        } else {
            resultString.append(String.format(MESSAGE_SUCCESS, Tag.toString(nonDuplicateTags)));
        }

        return new CommandResult(resultString.toString());
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
        TagCommand e = (TagCommand) other;
        return index.equals(e.index) && tagsToAdd.equals(e.tagsToAdd);
    }
}
