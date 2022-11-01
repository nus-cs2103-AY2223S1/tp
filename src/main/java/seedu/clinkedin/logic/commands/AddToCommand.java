package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_SKILLTAG;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.util.CollectionUtil;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.person.exceptions.RatingAlreadyExistsException;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.model.tag.UniqueTagList;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagException;
import seedu.clinkedin.model.tag.exceptions.TagNotFoundException;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;

/**
 * Adds a person to the address book.
 */
public class AddToCommand extends Command {

    public static final String COMMAND_WORD = "addto";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add additional attributes to an existing person in the address book.\n"
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_NOTE + "NOTE]"
            + "[" + PREFIX_RATING + "RATING]"
            + "[" + PREFIX_LINK + "LINK]...\n"
            + "Example: " + COMMAND_WORD + "2 "
            + PREFIX_SKILLTAG + "java "
            + PREFIX_SKILLTAG + "python "
            + PREFIX_NOTE + "Can do administrative work."
            + PREFIX_RATING + "4"
            + PREFIX_LINK + "https://github.com/unknown-person";

    public static final String MESSAGE_SUCCESS = "New Attributes added to person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_NO_ATTRIBUTE_TO_ADD = "Attributes to add should not be empty!";

    private final Index index;
    private final UpdatePersonDescriptor updatePersonDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddToCommand(Index index, UpdatePersonDescriptor updatePersonDescriptor) {
        requireNonNull(updatePersonDescriptor);
        requireNonNull(index);
        this.index = index;
        this.updatePersonDescriptor = updatePersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        try {
            Person personToBeUpdated = lastShownList.get(index.getZeroBased());
            Person updatedPerson = createUpdatedPerson(personToBeUpdated, updatePersonDescriptor);

            if (!personToBeUpdated.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToBeUpdated, updatedPerson);

            return new CommandResult(String.format(MESSAGE_SUCCESS, updatedPerson));
        } catch (TagTypeNotFoundException | TagNotFoundException | DuplicateTagException
                 | RatingAlreadyExistsException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddToCommand // instanceof handles nulls
                && index.equals(((AddToCommand) other).index)
                && updatePersonDescriptor.equals(((AddToCommand) other).updatePersonDescriptor));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createUpdatedPerson(Person personToUpdate,
                                              AddToCommand.UpdatePersonDescriptor updatePersonDescriptor)
            throws TagTypeNotFoundException, TagNotFoundException, DuplicateTagException, RatingAlreadyExistsException {
        assert personToUpdate != null;

        Map<TagType, UniqueTagList> personToEditTags = personToUpdate.getTags();
        UniqueTagTypeMap original = new UniqueTagTypeMap();
        original.setTagTypeMap(personToEditTags);
        UniqueTagTypeMap tagsToAdd = updatePersonDescriptor.getTagTypeMap().get();
        original.mergeTagTypeMap(tagsToAdd);
        UniqueTagTypeMap updatedTags = original;

        Optional<Note> notesToAdd = updatePersonDescriptor.getNote();
        Note updatedNote = personToUpdate.getNote();
        if (notesToAdd.isPresent()) {
            updatedNote = personToUpdate.mergeNote(notesToAdd.get());
        }

        Optional<Rating> ratingToAdd = updatePersonDescriptor.getRating();
        Rating updatedRating = personToUpdate.getRating();
        if (!updatedRating.toString().equals("0")) {
            throw new RatingAlreadyExistsException();
        } else if (ratingToAdd.isPresent()) {
            updatedRating = ratingToAdd.get();
        }

        Optional<Set<Link>> linksToAdd = updatePersonDescriptor.getLinks();
        Set<Link> updatedLinks = new HashSet<>();
        if (linksToAdd.isPresent()) {
            updatedLinks = personToUpdate.mergeLinks(linksToAdd.get());
        }

        return new Person(personToUpdate.getName(), personToUpdate.getPhone(), personToUpdate.getEmail(),
                personToUpdate.getAddress(), updatedTags, personToUpdate.getStatus(),
                updatedNote, updatedRating, updatedLinks);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class UpdatePersonDescriptor {
        private UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
        private Note note;

        private Rating rating;

        private Set<Link> links;

        public UpdatePersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdatePersonDescriptor(AddToCommand.UpdatePersonDescriptor toCopy) {
            setTagTypeMap(toCopy.tagTypeMap);
            setNote(toCopy.note);
            setRating(toCopy.rating);
            setLinks(toCopy.links);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            if (!tagTypeMap.isEmpty()) {
                return true;
            }
            return CollectionUtil.isAnyNonNull(note, rating, links);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        /**
         * Sets {@code newTagTypeMap} to this object's {@code newTagTypeMap}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTagTypeMap(UniqueTagTypeMap tagTypeMap) {
            this.tagTypeMap = tagTypeMap;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<UniqueTagTypeMap> getTagTypeMap() {
            return (tagTypeMap != null) ? Optional.of(tagTypeMap) : Optional.empty();
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public void setLinks(Set<Link> links) {
            this.links = links;
        }

        public Optional<Set<Link>> getLinks() {
            return Optional.ofNullable(links);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddToCommand.UpdatePersonDescriptor)) {
                return false;
            }

            // state check
            AddToCommand.UpdatePersonDescriptor u = (AddToCommand.UpdatePersonDescriptor) other;

            return getTagTypeMap().equals(u.getTagTypeMap())
                    && getNote().equals(u.getNote())
                    && getRating().equals(u.getRating())
                    && getLinks().equals(u.getLinks());
        }
    }
}
