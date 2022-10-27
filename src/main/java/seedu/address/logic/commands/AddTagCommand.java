package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Add tag for an existing person in the address book.
 */
public class AddTagCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, new tag: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tags for a person in the address book.\n"
        + "Parameters:\n"
        + "  INDEX (must be a positive integer or use \"all\" to add tags for everyone in the list) \n"
        + "  [" + PREFIX_TAG + "TAG]...\n"
        + "Example:\n" + COMMAND_WORD + " 1 "
        + PREFIX_TAG + "lab "
        + PREFIX_TAG + "goodProgress"
        + "\nor\n" + COMMAND_WORD + " all "
        + PREFIX_TAG + "tutorial "
        + PREFIX_TAG + "needRemedial";

    public static final String MESSAGE_SINGLE_ADD_TAGS_SUCCESS = "Added tags %1$s Person: %2$s";

    public static final String MESSAGE_MULTI_ADD_TAGS_SUCCESS = "Added tags %1$s for %2$d persons";

    public static final String MESSAGE_TAGS_NOT_ADDED = "At least one tag must be added";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    /**
     * Index of the person to add the tags for.
     * If index is null, the tags will be added for every person in the list.
     */
    private Index index;

    /** The tags to be added.*/
    private Set<Tag> tags;

    private String toBeAddedTagsStr;

    /** The tags to be added.*/
    private boolean isAddToAll;

    /**
     * Constructs AddTagCommand that will add specified tag to all persons in the
     * displayed list.
     *
     * @param tags of the person to be added
     */
    public AddTagCommand(Set<Tag> tags) {
        requireNonNull(tags);

        this.isAddToAll = true;
        this.tags = tags;
        this.toBeAddedTagsStr = tagSetToSting(tags);
    }

    /**
     * @param index of the person in the filtered person list to add the tag
     * @param tags of the person to be added
     */
    public AddTagCommand(Index index, Set<Tag> tags) {
        requireNonNull(tags);

        this.isAddToAll = false;
        this.index = index;
        this.tags = tags;
        this.toBeAddedTagsStr = tagSetToSting(tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (this.isAddToAll) {
            int numOfPersonUpdated = 0;
            for (int idx = 0; idx < lastShownList.size(); idx++) {
                Person personToEdit = lastShownList.get(idx);
                executeSingle(model, Index.fromZeroBased(idx), personToEdit);
                numOfPersonUpdated += 1;
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(
                    MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                    toBeAddedTagsStr,
                    numOfPersonUpdated));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        assert !this.isAddToAll && this.index != null
                : "[AddTagCommand] index should not be null if it is not all";
        Person personToEdit = lastShownList.get(index.getZeroBased());
        CommandResult res = executeSingle(model, this.index, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return res;
    }

    /**
     * Adds tags for a single person in the displayed list.
     * @param model {@code Model} which the command should operate on.
     * @param index of the person for whom the tags will be added.
     * @param personToEdit for whom the tags will be added.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeSingle(
                Model model, Index index, Person personToEdit) throws CommandException {
        Set<Tag> existingTags = personToEdit.getTags();
        Set<Tag> toBeAddedTags = new HashSet<Tag>();
        for (Tag tag : existingTags) {
            if (!this.tags.contains(tag)) {
                toBeAddedTags.add(tag);
            }
        }
        for (Tag tag : this.tags) {
            toBeAddedTags.add(tag);
        }
        System.out.println(toBeAddedTags);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setTags(toBeAddedTags);
        CommandResult editPersonResult = (new EditCommand(index, editPersonDescriptor)).executeNoRefresh(model);
        LOGGER.info(editPersonResult.getFeedbackToUser());

        assert index != null : "[AddTagCommand] Index in executeSingle should not be null";
        return new CommandResult(String.format(
                MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                toBeAddedTagsStr,
                personToEdit.getName()));
    }

    /**
     * Formats the tag set to be more user friendly string.
     * @param tags set to be formatted.
     * @return the fomatted list of tags/
     */
    public static String tagSetToSting(Set<Tag> tags) {
        String res = "";
        for (Tag tag : tags) {
            res += tag.toString() + ", ";
        }
        return res.substring(0, res.length() - 2);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return index.equals(e.index)
                && this.tags.equals(e.tags);
    }
}
