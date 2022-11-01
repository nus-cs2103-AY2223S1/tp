package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_DEGREETAG;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_JOBTYPETAG;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_SKILLTAG;
import static seedu.clinkedin.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;
import seedu.clinkedin.model.tag.exceptions.TagNotFoundException;


/**
 * Deletes a specified tag of a person.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deletetag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag of the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILLTAG + "TAG] "
            + "[" + PREFIX_DEGREETAG + "TAG] "
            + "[" + PREFIX_JOBTYPETAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_SKILLTAG + "Java ";

    public static final String MESSAGE_DELETE_TAGS_SUCCESS = "Deleted tags of the person: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists";

    private final Index targetIndex;
    private final UniqueTagTypeMap toDelete;
    private EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates an DeleteTagCommand to delete the specified {@code Tag} of a person at the specified {@code Index}
     */
    public DeleteTagCommand(Index targetIndex, UniqueTagTypeMap toDelete) {
        requireNonNull(targetIndex);
        requireNonNull(toDelete);
        this.targetIndex = targetIndex;
        this.toDelete = toDelete;
        editPersonDescriptor = new EditCommand.EditPersonDescriptor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        editPersonDescriptor.setNewTagTypeMap(toDelete);
        UniqueTagTypeMap tagsToBeDeleted = editPersonDescriptor.getNewTagTypeMap().get();

        try {
            Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_DELETE_TAGS_SUCCESS, tagsToBeDeleted));
        } catch (TagTypeNotFoundException | TagNotFoundException t) {
            throw new CommandException(t.getMessage());
        }

    }

    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor)
            throws TagTypeNotFoundException, TagNotFoundException {
        assert personToEdit != null;
        UniqueTagTypeMap updatedTags = new UniqueTagTypeMap();
        updatedTags.setTagTypeMap(personToEdit.getTags());
        updatedTags.removeTags(editPersonDescriptor.getNewTagTypeMap().get());

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), updatedTags, personToEdit.getStatus(), personToEdit.getNote(),
                personToEdit.getRating(), personToEdit.getLinks());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTagCommand) other).targetIndex)
                && toDelete.equals(((DeleteTagCommand) other).toDelete));
    }
}
