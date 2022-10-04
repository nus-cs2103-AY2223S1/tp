package gim.logic.commands;

import static java.util.Objects.requireNonNull;
import static gim.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static gim.logic.parser.CliSyntax.PREFIX_EMAIL;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_PHONE;
import static gim.logic.parser.CliSyntax.PREFIX_TAG;
import static gim.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import gim.commons.core.Messages;
import gim.commons.core.index.Index;
import gim.commons.util.CollectionUtil;
import gim.logic.commands.exceptions.CommandException;
import gim.model.Model;
import gim.model.exercise.Address;
import gim.model.exercise.Email;
import gim.model.exercise.Name;
import gim.model.exercise.Exercise;
import gim.model.exercise.Phone;
import gim.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Exercise: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditExerciseDescriptor editExerciseDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editExerciseDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditExerciseDescriptor editExerciseDescriptor) {
        requireNonNull(index);
        requireNonNull(editExerciseDescriptor);

        this.index = index;
        this.editExerciseDescriptor = new EditExerciseDescriptor(editExerciseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Exercise personToEdit = lastShownList.get(index.getZeroBased());
        Exercise editedExercise = createEditedExercise(personToEdit, editExerciseDescriptor);

        if (!personToEdit.isSameExercise(editedExercise) && model.hasExercise(editedExercise)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setExercise(personToEdit, editedExercise);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedExercise));
    }

    /**
     * Creates and returns a {@code Exercise} with the details of {@code personToEdit}
     * edited with {@code editExerciseDescriptor}.
     */
    private static Exercise createEditedExercise(Exercise personToEdit, EditExerciseDescriptor editExerciseDescriptor) {
        assert personToEdit != null;

        Name updatedName = editExerciseDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editExerciseDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editExerciseDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editExerciseDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editExerciseDescriptor.getTags().orElse(personToEdit.getTags());

        return new Exercise(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editExerciseDescriptor.equals(e.editExerciseDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditExerciseDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditExerciseDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExerciseDescriptor(EditExerciseDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExerciseDescriptor)) {
                return false;
            }

            // state check
            EditExerciseDescriptor e = (EditExerciseDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
