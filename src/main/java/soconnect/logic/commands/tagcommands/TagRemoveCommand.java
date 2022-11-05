package soconnect.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import soconnect.commons.core.Messages;
import soconnect.commons.core.index.Index;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;
import soconnect.model.person.Address;
import soconnect.model.person.Email;
import soconnect.model.person.Name;
import soconnect.model.person.Person;
import soconnect.model.person.Phone;
import soconnect.model.tag.Tag;

/**
 * Removes a tag from a contact.
 */
public class TagRemoveCommand extends TagCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = TagCommand.COMMAND_WORD + " "
            + COMMAND_WORD + ": Removes a tag from the contact "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG...\n"
            + "Example: " + TagCommand.COMMAND_WORD + " "
            + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Tag removed: %1$s";
    public static final String MESSAGE_NO_SUCH_TAG = "Contact specified does not contain this tag";
    public static final String MESSAGE_NO_TAG = "Please specify a tag";

    private final Index index;
    private final Tag tag;

    /**
     * Constructs an {@code TagRemoveCommand} to remove the specified {@code Tag} from the
     * person identified using it's displayed {@code Index} from SoConnect.
     */
    public TagRemoveCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        assert model.hasTag(tag) : "The tag should exist in the list.";

        if (!personToEdit.contains(tag)) {
            throw new CommandException(MESSAGE_NO_SUCH_TAG);
        }

        Tag tagFromList = model.getTagFromList(tag);
        Person editedPerson = createEditedPerson(personToEdit, tagFromList);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_TAG_SUCCESS, tagFromList));
    }

    /**
     * Recreates the same person with updated tags.
     */
    private static Person createEditedPerson(Person personToEdit, Tag tag) {
        requireNonNull(personToEdit);

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();

        Set<Tag> oldTags = personToEdit.getTags();
        List<Tag> tagList = new ArrayList<>(oldTags);
        tagList.remove(tag);
        Set<Tag> updatedTags = new HashSet<>(tagList);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }
}
