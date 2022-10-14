package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEGREETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTYPETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLTAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueTagTypeMap;
import seedu.address.model.tag.exceptions.DuplicateTagException;


/**
 * Adds a tag of a specific tag type to a person.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag of a specific tag type to a person.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILLTAG + " TAG] "
            + "[" + PREFIX_DEGREETAG + " TAG] "
            + "[" + PREFIX_JOBTYPETAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_SKILLTAG + "Java "
            + PREFIX_DEGREETAG + "Undergraduate "
            + PREFIX_JOBTYPETAG + "Internship";

    public static final String MESSAGE_SUCCESS = "New tags added: : %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists";

    private final Index targetIndex;
    private final UniqueTagTypeMap toAdd;
    private EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag}
     */
    public AddTagCommand(Index targetIndex, UniqueTagTypeMap toAdd) {
        requireAllNonNull(targetIndex, toAdd);
        this.targetIndex = targetIndex;
        this.toAdd = toAdd;
        editPersonDescriptor = new EditCommand.EditPersonDescriptor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        editPersonDescriptor.setNewTagTypeMap(toAdd);

        try {
            Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson));
        } catch (DuplicateTagException d) {
            throw new CommandException(d.getMessage());
        }
    }

    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor)
            throws DuplicateTagException {
        assert personToEdit != null;
        UniqueTagTypeMap updatedTags = new UniqueTagTypeMap();
        updatedTags.setTagTypeMap(personToEdit.getTags());
        updatedTags.mergeTagTypeMap(editPersonDescriptor.getNewTagTypeMap().get());

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), updatedTags, personToEdit.getStatus(), personToEdit.getNote());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && targetIndex.equals(((AddTagCommand) other).targetIndex)
                && toAdd.equals(((AddTagCommand) other).toAdd));
    }
}
